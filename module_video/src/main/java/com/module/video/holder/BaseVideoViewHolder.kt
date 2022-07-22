package com.module.video.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.module.video.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseVideoViewHolder(private val v: View): RecyclerView.ViewHolder(v) {
    abstract fun onBindView(context: Context, data: Data)
}