package com.module.home.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class TextCardHolder(private val v: View) : RecyclerView.ViewHolder(v) {

    val text: TextView = v.findViewById(R.id.item_text_card)

}