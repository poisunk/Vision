package com.module.video.service

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lib.common.config.ARouterTable
import com.lib.common.service.IVideoService
import com.module.video.VideoActivity

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.VIDEO_SERVICE)
class VideoServiceImpl: IVideoService {

    override fun startActivity(context: Context, data: IVideoService.VideoData) {
        VideoActivity.startActivity(context, data)
    }

    override fun starActivity(context: Context, data: IVideoService.VideoData, bundle: Bundle) {
        VideoActivity.startActivity(context, data, bundle)
    }

    override fun init(context: Context?) {
    }

}