package com.module.home.holder

import android.content.Context
import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class FollowCardHolder(private val v: View): BaseHomeViewHolder(v) {

    private val cover: ImageView = v.findViewById(R.id.follow_video_cover)
    private val duration: TextView = v.findViewById(R.id.follow_video_duration)
    private val avatar: ImageView = v.findViewById(R.id.follow_video_owner_avatar)
    private val title: TextView = v.findViewById(R.id.follow_video_title)
    private val nickname: TextView = v.findViewById(R.id.follow_video_owner_nickname)
    private val category: TextView = v.findViewById(R.id.follow_video_category)

    override fun onBindView(context: Context, data: Data) {
        duration.text = getMinute(data.content.data.duration)
        title.text = data.header.title
        nickname.text = data.content.data.author.name
        category.text = data.content.data.category
        Glide.with(context).load(data.content.data.cover.feed).into(cover)
        Glide.with(context).load(data.content.data.author.icon).into(avatar)
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