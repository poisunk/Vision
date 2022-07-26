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

    private var startX = 0f
    private var startY = 0f
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = abs(ev.x - startX)
                val dy = abs(ev.y - startY)
                // 当竖直滑动时，将不允许父view拦截事件
                if (dx < dy){
                    parent.requestDisallowInterceptTouchEvent(true)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}