package com.module.video.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
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

    override fun init(context: Context?) {
    }

}