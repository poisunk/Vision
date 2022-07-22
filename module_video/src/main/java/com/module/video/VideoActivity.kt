package com.module.video

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.core.view.ViewCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.config.ARouterTable
import com.lib.common.service.IVideoService
import com.lib.common.ui.BaseActivity
import com.lib.common.ui.BaseViewModel
import com.module.video.databinding.ActivityVideoBinding
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.VIDEO)
class VideoActivity : BaseActivity<ActivityVideoBinding, BaseViewModel>(){

    companion object {
        fun startActivity(context: Context, data: IVideoService.VideoData){
            context.startActivity(Intent(context, VideoActivity::class.java).apply {
                putExtra("VideoData", data)
            })
        }
    }

    private val mData: IVideoService.VideoData by lazy { intent.extras?.get("VideoData") as IVideoService.VideoData }

    override fun getLayoutId(): Int = R.layout.activity_video

    override fun init() {
        window.statusBarColor = Color.BLACK
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightStatusBars = false
        PlayerFactory.setPlayManager(SystemPlayerManager::class.java)
        mBinding.videoPlayer.setUp(mData.playUrl, true, mData.title)
    }

    override fun onResume() {
        super.onResume()
        mBinding.videoPlayer.startPlayLogic()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.videoPlayer.release()
    }

}