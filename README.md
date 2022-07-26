# Vision
红岩网校暑假考核

# 页面展示

![](https://github.com/poisunk/Vision/blob/main/image/D342744E4F669C4B098A9681CB1CB88E.gif?raw=true)

# 技术亮点

这次的项目我使用了多模块开发，从 `groovy` 迁移到了 `kts` ，将配置文件统一放到了 `build_logic` 中去管理：

![](E:\笔记\images\屏幕截图 2022-07-26 023027.png)

使用了自定义插件，减少了模块配置文件中的代码：

~~~kotlin
import dep.dependenciesGlide
import dep.dependenciesRetrofit
import dep.dependenciesRxJava
import dep.dependenciesVideoPlayer

plugins {
    id("com.plugins.lib")
}

dependenciesRetrofit()
dependenciesRxJava()
dependenciesGlide()
dependenciesVideoPlayer()
~~~

自定义了一个 `BannerView` ，其实就是简单封装了一个 `RecyclerView` ：

```kotlin
class BannerView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int = 0,
    defStyleRes:Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes){
    
    // 省略部分代码
    
    private val mRecyclerView:RecyclerView
    
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action){
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker.addMovement(ev)
                mVelocityTracker.computeCurrentVelocity(1000)
            }
            MotionEvent.ACTION_UP -> {
                // 当手指移开时，自动固定位置
                return onTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                // 当事件结束时，根据当前位置和速度判断划向哪一个位置
                val position = (mInterval/2 + getScrolledX())/mInterval
                val velocityX = mVelocityTracker.xVelocity
                if (abs(velocityX) > SNAP_VELOCITY) {
                    scrollToPosition(
                        if (velocityX < 0) {
                            mCurrentPosition + 1
                        }else {
                            mCurrentPosition - 1
                        }
                    )
                }else {
                    scrollToPosition(position)
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }
    
    /**
     * 在ViewPager的onInterceptTouchEvent中，如果水平方向上的滑动距离大于竖直方向的2倍，则认为是有效的切换页面的滑动，
     * 这个事件就会被ViewPager拦截。所以在这里设置了parent.requestDisallowInterceptTouchEvent(true)不允许它拦截。
     * 那还有一个问题就是既然parent要拦截事件了，这个事件为什么还会被它子view的InterceptTouchEvent接收到呢？
     * 这个是因为在RecyclerView的onInterceptTouchEvent方法中，只有在满足条件Math.abs(dx) > mTouchSlop时，事件才会被拦截，
     * 所以事件是可以在Math.abs(dx) <= mTouchSlop的时候被分发到子View的。
     */
    private fun handleInterceptTouchEvent(ev: MotionEvent) {
        if(!mRecyclerView.canScrollHorizontally(1) && mRecyclerView.canScrollHorizontally(-1)) {
            return
        }
        if(ev.action == MotionEvent.ACTION_DOWN) {
            // 这里如果只对parent进行一次disallow，还是会有滑动冲突，可能是嵌套太多的原因
            var parents = parent
            while (parents != null) {
                parents.requestDisallowInterceptTouchEvent(true)
                parents = parents.parent
            }
        }
    }
}
```

然后还有简单封装了一个可以根据数据动态加载布局的RecyclerAdapter：

~~~kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeViewHolder {
    return when (viewType) {
        HomeItemType.HORIZONTAL_SCROLL_CARD.ordinal -> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_scroll_card, parent, false)
            HorizontalScrollCardHolder(view)
        }
        HomeItemType.TEXT_CARD.ordinal -> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false)
            TextCardHolder(view)
        }
        ......
    }
}

override fun getItemViewType(position: Int): Int {
    return when(itemList[position].type) {
        "horizontalScrollCard" -> {
            HomeItemType.HORIZONTAL_SCROLL_CARD.ordinal
        }
        "textCard" -> {
            HomeItemType.TEXT_CARD.ordinal
        }
        ......
    }
}
~~~

网络请求使用了 `retrofit` + `RxJava`：

~~~kotlin
mHomeApiService.getDiscoverData()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(object : Observer<HomeData> {
        override fun onSubscribe(d: Disposable) {
            startLoading()
        }

        override fun onNext(t: HomeData) {
            _discoverLiveData.value = t
        }

        override fun onError(e: Throwable) {
            showToast("网络不可用")
            showFailedPage()
            dismissLoading()
        }

        override fun onComplete() {
            dismissLoading()
        }
    })
~~~

还有基于 `Channel` 的处理事件：

~~~kotlin
// 当 Channel 没有订阅者时，向其发送的数据会挂起，保证订阅者出现时第一时间接收到这个数据
private val actionLiveData = Channel<BaseActionEvent>()
val mActionLiveData: Flow<BaseActionEvent>
	get() = actionLiveData.receiveAsFlow()
~~~

# 有待提升的地方

- 自定义View写的还是太简单了，我感觉还可以写一个自定义ViewGroup来提升自己的理解。
- 网络请求方面，要去再补充一下协程方面的知识。
- 还需要再去熟悉一下Jetpack，我倒后面才知道官方有一个PageListAdapter。
