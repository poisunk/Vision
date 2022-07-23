package com.module.home.holder

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.Data
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class AutoPlayCardHolder(private val v: View) : BaseHomeViewHolder(v) {
    private val avatar: ImageView= v.findViewById(R.id.owner_avatar)
    private val nickname: TextView = v.findViewById(R.id.owner_nickname)
    private val description: TextView = v.findViewById(R.id.video_description)
    val videoPlayer: StandardGSYVideoPlayer = v.findViewById(R.id.video_auto_player)

    override fun onBindView(context: Context, data: Data) {
        Glide.with(context).load(data.content.data.owner.avatar).into(avatar)
        nickname.text = data.content.data.owner.nickname
        description.text = data.content.data.description
        videoPlayer.setUp(data.content.data.playUrl, false, data.content.data.title)
        videoPlayer.titleTextView.visibility = View.GONE
        val cover = ImageView(context).apply { scaleType = ImageView.ScaleType.CENTER_CROP }
        Glide.with(context).load(data.content.data.cover.feed).into(cover)
        videoPlayer.thumbImageView = cover
    }


}