package com.module.video.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import com.module.video.R
import com.module.video.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class TextCardHolder(private val v: View) :BaseVideoViewHolder(v) {

    private val text: TextView = v.findViewById(R.id.item_text_card)

    override fun onBindView(context: Context, data: Data) {
        text.text = data.text
    }

}