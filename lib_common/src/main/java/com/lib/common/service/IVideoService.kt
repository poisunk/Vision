package com.lib.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import java.io.Serializable

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface IVideoService: IProvider {

    fun startActivity(context: Context, data: VideoData)

    data class VideoData(
        val playUrl: String,
        val id: Long,
        val title: String,
        val description: String,
        val date: Long,
        val category: String,
        val collectionCount: Int,
        val shareCount: Int,
        val replyCount: Int,
        val avatar: String,
        val nickname: String,
        val authorDescription: String,
        val cover: String
    ): Serializable
}