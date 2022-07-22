package com.module.video.widget

import android.content.Context
import androidx.core.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class MyNestedScrollLayout @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {


    private var contentView: ViewGroup? = null
    private var topView: View? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        // 获取第一个孩子的第一个孩子
        topView = (getChildAt(0) as ViewGroup).getChildAt(0)
        // 获取第一个孩子的第二个孩子
        contentView = (getChildAt(0) as ViewGroup).getChildAt(1) as ViewGroup
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 调整contentView的高度为父容器高度，使之填充布局，避免父容器滚动后出现空白
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val lp = contentView!!.layoutParams
        lp.height = measuredHeight
        contentView!!.layoutParams = lp
    }

    // 让父亲先滑动，至父亲完全滑动不可见，自己再滑动
    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val hideTop = dy > 0 && scrollY < topView!!.measuredHeight
        if (hideTop) {
            scrollBy(0, dy)
            consumed[1] = dy // 消费了多少，如果没有，就会重复滑动
        }
    }
}