package com.lib.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.lib.common.adapter.BannerViewAdapter
import kotlin.math.abs

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BannerView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet? = null,
    defStyleAttr:Int = 0,
    defStyleRes:Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes){

    private val SNAP_VELOCITY = 500

    private val mRecyclerView:RecyclerView

    private val mLayoutManager: LinearLayoutManager

    private val mVelocityTracker: VelocityTracker

    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2) {
                v = v.parent as? View
            }
            return v as? ViewPager2
        }

    private var mInterval = 0

    private var mCurrentPosition = 1

    private var mBannerSize = 1


    init {
        mRecyclerView = RecyclerView(context)
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mVelocityTracker = VelocityTracker.obtain()
        mRecyclerView.layoutManager = mLayoutManager
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        addView(mRecyclerView, layoutParams)
    }

    fun setAdapter(adapter: BannerViewAdapter) {
        mRecyclerView.adapter = adapter
        mBannerSize = adapter.itemCount
        mRecyclerView.scrollToPosition(1)
        mRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    if(mCurrentPosition == 0){
                        mCurrentPosition = mBannerSize - 2
                        recyclerView.scrollToPosition(mCurrentPosition)
                    }else if(mCurrentPosition == mBannerSize - 1) {
                        mCurrentPosition = 1
                        recyclerView.scrollToPosition(mCurrentPosition)
                    }
                }
            }
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 因为recyclerview中的子item设置的都是match_parent，所以这里直接获取整个banner的width
        mInterval = MeasureSpec.getSize(widthMeasureSpec)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action){
            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker.addMovement(ev)
                mVelocityTracker.computeCurrentVelocity(1000)
            }
            MotionEvent.ACTION_UP -> {
                return onTouchEvent(ev)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_UP -> {
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

    private fun getScrolledX(): Int{
        val position = mLayoutManager.findFirstVisibleItemPosition()
        val firstView = mLayoutManager.findViewByPosition(position)
        firstView?.apply {
            val itemWidth = this.width
            return position * (itemWidth + marginEnd + marginStart) - left + marginStart
        }
        return 0
    }

    private fun scrollToPosition(position: Int) {
        mCurrentPosition = position
        mRecyclerView.smoothScrollToPosition(position)
    }

    private fun handleInterceptTouchEvent(ev: MotionEvent) {
        if(!mRecyclerView.canScrollHorizontally(1) && mRecyclerView.canScrollHorizontally(-1)) {
            return
        }
        if(ev.action == MotionEvent.ACTION_DOWN) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
    }
}