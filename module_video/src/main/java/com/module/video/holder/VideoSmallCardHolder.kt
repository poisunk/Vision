package com.module.video.holder

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.module.video.R
import com.module.video.bean.Data
import com.module.video.utils.getMinute
import com.module.video.utils.onClickStartVideo

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class VideoSmallCardHolder(private val v: View): BaseVideoViewHolder(v) {
    private val cover: ImageView = v.findViewById(R.id.video_small_cover)
    private val title: TextView = v.findViewById(R.id.video_small_title)
    private val tag: TextView = v.findViewById(R.id.video_small_tags)
    private val duration: TextView = v.findViewById(R.id.video_small_duration)

    @SuppressLint("SetTextI18n")
    override fun onBindView(context: Context, data: Data) {
        title.text = data.title
        duration.text =getMinute(data.duration)
        tag.text = "#${data.tags[0].name}"
        Glide.with(context).load(data.cover.feed).into(cover)
        cover.onClickStartVideo(context, data)
    }

}