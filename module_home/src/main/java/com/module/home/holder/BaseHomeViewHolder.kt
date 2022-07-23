package com.module.home.holder

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseHomeViewHolder(private val v: View): RecyclerView.ViewHolder(v) {

    abstract fun onBindView(context: Activity, data: Data)
}