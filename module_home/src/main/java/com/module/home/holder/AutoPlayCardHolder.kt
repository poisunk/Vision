package com.module.home.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.Data
import com.module.home.utils.onClickStartVideo

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class AutoPlayCardHolder(private val v: View) : BaseHomeViewHolder(v) {
    private val cover: ImageView = v.findViewById(R.id.video_cover)
    private val avatar: ImageView= v.findViewById(R.id.owner_avatar)
    private val nickname: TextView = v.findViewById(R.id.owner_nickname)
    private val description: TextView = v.findViewById(R.id.video_description)

    override fun onBindView(context: Context, data: Data) {
        Glide.with(context).load(data.content.data.cover.feed).into(cover)
        Glide.with(context).load(data.content.data.owner.avatar).into(avatar)
        nickname.text = data.content.data.owner.nickname
        description.text = data.content.data.description
    }
}