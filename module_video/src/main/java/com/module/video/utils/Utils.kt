package com.module.video.utils

import android.content.Context
import android.view.View
import com.lib.common.service.IVideoService
import com.module.video.bean.Data

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

fun View.onClickStartVideo(context: Context, data: Data) {
    setOnClickListener {

    }
}