package com.module.home.utils

import android.content.Context
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.lib.common.config.ARouterTable
import com.lib.common.ext.toast
import com.lib.common.service.IVideoService
import com.lib.common.service.ServiceManager
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
fun getMinute(seconds: Int): String {
    val m = seconds/60
    val s = seconds%60
    if (s < 10){
        return "${m}:0${s}"
    }
    return "${m}:${s}"
}
fun convertVideoData(data: Data) = IVideoService.VideoData(
    playUrl = data.playUrl,
    id = data.id,
    title = data.title,
    description = data.description,
    date = data.date,
    category = data.category,
    collectionCount = data.consumption.collectionCount,
    shareCount = data.consumption.shareCount,
    replyCount = data.consumption.replyCount,
    avatar = data.author.icon,
    nickname = data.author.name,
    authorDescription = data.author.description
)

fun View.onClickStartVideo(context: Context, data: Data) {
    val mVideoData = convertVideoData(data)
    setOnClickListener {
        val mVideoService = ServiceManager.getService(IVideoService::class)
        mVideoService.startActivity(context, mVideoData)
    }
}