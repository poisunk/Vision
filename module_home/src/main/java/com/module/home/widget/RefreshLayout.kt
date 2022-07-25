package com.module.home.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class RefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet
): SwipeRefreshLayout(context, attrs) {

    private var isScrolling = false //是否已经开始滑动
    private var startX = 0f
    private var startY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val endX = ev.x
                val endY = ev.y
                val disX = abs(endX - startX)
                val disY = abs(endY - startY)
                if (disX > disY && !isScrolling) {
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    isScrolling = true
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
                isScrolling=false
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}