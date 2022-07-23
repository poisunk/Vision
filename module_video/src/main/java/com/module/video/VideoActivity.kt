package com.module.video

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.RangeValueIterator
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Allocation.createFromBitmap
import android.renderscript.Element.U8_4
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.lib.common.config.ARouterTable
import com.lib.common.service.IVideoService
import com.lib.common.ui.BaseActivity
import com.module.video.adapter.VideoRecyclerAdapter
import com.module.video.bean.VideoData
import com.module.video.databinding.ActivityVideoBinding
import com.module.video.utils.BlurTransform
import com.module.video.viewmodel.VideoViewModel
import com.shuyu.gsyvideoplayer.player.PlayerFactory
import com.shuyu.gsyvideoplayer.player.SystemPlayerManager
import java.text.SimpleDateFormat
import java.util.*


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.VIDEO)
class VideoActivity : BaseActivity<ActivityVideoBinding, VideoViewModel>(){

    companion object {
        fun startActivity(context: Context, data: IVideoService.VideoData){
            context.startActivity(Intent(context, VideoActivity::class.java).apply {
                putExtra("VideoData", data)
            })
        }

        fun startActivity(context: Context, data: IVideoService.VideoData, bundle: Bundle){
            context.startActivity(Intent(context, VideoActivity::class.java).apply {
                putExtra("VideoData", data)
            }, bundle)
        }
    }

    private val mData: IVideoService.VideoData by lazy { intent.extras?.get("VideoData") as IVideoService.VideoData }

    override fun getLayoutId(): Int = R.layout.activity_video

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun init() {
        window.statusBarColor = Color.BLACK
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightStatusBars = false
        mBinding.videoPlayer.setUp(mData.playUrl, true, mData.title)
        mBinding.videoRecommendRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mViewModel.mVideoData.observe(this, this::handleVideoRecommendData)

        mBinding.videoDetailTitle.text = mData.title
        mBinding.videoDetailCategory.text = mData.category + SimpleDateFormat("yyyy-M-dd HH:mm").format(Date(mData.date))
        mBinding.videoDetailDescription.text = mData.description
        mBinding.videoAuthorNickname.text = mData.nickname
        Glide.with(this).load(mData.avatar).into(mBinding.videoAuthorAvatar)
        Glide.with(this).load(mData.cover).transform(BlurTransform(100)).into(mBinding.videoDetailBackground)
    }

    override fun onResume() {
        super.onResume()
        mBinding.videoPlayer.startPlayLogic()
        mViewModel.getVideoRecommend(mData.id)
    }

    override fun onPause() {
        super.onPause()
        mBinding.videoPlayer.onVideoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.videoPlayer.release()
    }

    private fun handleVideoRecommendData(data: VideoData) {
        mBinding.videoRecommendRecycler.adapter = VideoRecyclerAdapter(this, data.itemList)
    }


}