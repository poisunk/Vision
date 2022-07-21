package com.module.home.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BriefCardHolder(private val v: View): BaseHomeViewHolder(v) {
    private val cover: ImageView = v.findViewById(R.id.brief_cover)
    private val title: TextView = v.findViewById(R.id.brief_title)
    private val description: TextView = v.findViewById(R.id.brief_description)

    override fun onBindView(context: Context, data: Data) {
        title.text = data.title
        description.text = data.description
        Glide.with(context).load(data.icon).into(cover)
    }
}