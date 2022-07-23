package com.module.home.holder

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.Data
import com.module.home.utils.getMinute
import com.module.home.utils.onClickStartVideo

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class VideoSmallCardHolder(private val v: View): BaseHomeViewHolder(v) {
    private val cover: ImageView = v.findViewById(R.id.video_small_cover)
    private val title: TextView = v.findViewById(R.id.video_small_title)
    private val tag: TextView = v.findViewById(R.id.video_small_tags)
    private val duration: TextView = v.findViewById(R.id.video_small_duration)

    @SuppressLint("SetTextI18n")
    override fun onBindView(context: Activity, data: Data) {
        title.text = data.title
        duration.text =getMinute(data.duration)
        tag.text = "#${data.tags[0].name}"
        Glide.with(context).load(data.cover.feed).into(cover)
        cover.onClickStartVideo(context, data)
    }

}