package com.module.home.holder

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.bean.Data
import com.module.home.bean.ItemList

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class TextCardHolder(private val v: View) :BaseHomeViewHolder(v) {

    private val text: TextView = v.findViewById(R.id.item_text_card)

    override fun onBindView(context: Activity, data: Data) {
        text.text = data.text
    }

}