package com.module.home.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class RefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {



    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        handleInterceptTouchEvent(ev)
        return false
    }

    private var startX = 0f
    private var startY = 0f

    private fun handleInterceptTouchEvent(ev: MotionEvent) {
        if(!getChildAt(0).canScrollVertically(1) && getChildAt(0).canScrollVertically(-1)) {
            return
        }
        if(ev.action == MotionEvent.ACTION_DOWN) {
            parent.requestDisallowInterceptTouchEvent(true)
            startX = ev.x
            startY = ev.y
        }else if(ev.action == MotionEvent.ACTION_MOVE) {
            val dx = ev.x - startX
            val dy = ev.y - startY
            if(dx > 2*dy) {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
    }
}