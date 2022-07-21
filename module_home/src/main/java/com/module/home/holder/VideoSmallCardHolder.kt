package com.module.home.holder

import android.annotation.SuppressLint
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
class VideoSmallCardHolder(private val v: View): BaseHomeViewHolder(v) {
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
    }

    private fun getMinute(seconds: Int): String {
        val m = seconds/60
        val s = seconds%60
        if (s < 10){
            return "${m}:0${s}"
        }
        return "${m}:${s}"
    }
}