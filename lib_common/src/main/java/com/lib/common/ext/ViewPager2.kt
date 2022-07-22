package com.lib.common.ext

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.lang.reflect.Field

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
fun ViewPager2.forceSetOverScrollMode(overScrollMode: Int) = try {
    val mRecyclerView: Field = this.javaClass.getDeclaredField("mRecyclerView")
    mRecyclerView.isAccessible = true
    (mRecyclerView.get(this) as RecyclerView).overScrollMode = overScrollMode
}catch (e: Exception){

}