package com.module.video

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.lib.common.config.ARouterTable
import com.lib.common.service.IVideoService
import com.lib.common.ui.BaseActivity
import com.module.video.adapter.VideoRecyclerAdapter
import com.module.video.bean.VideoData
import com.module.video.databinding.ActivityVideoBinding
import com.module.video.utils.BlurTransform
import com.module.video.utils.convertVideoData
import com.module.video.viewmodel.VideoViewModel
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
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

    override fun init() {
        window.statusBarColor = Color.BLACK
        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.isAppearanceLightStatusBars = false

        mBinding.videoRecommendRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mViewModel.mVideoData.observe(this, this::handleVideoRecommendData)

        initVideoPlayer()
        initVideoDetail()
        initMotion()
        initFailPage()
    }

    private lateinit var orientationUtils: OrientationUtils

    /**
     * 初始化视频播放器
     */
    private fun initVideoPlayer() {
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        mBinding.videoPlayer.apply {
            setUp(mData.playUrl, true, mData.title)
            orientationUtils = OrientationUtils(this@VideoActivity, this)
            orientationUtils.isEnable = true
            fullscreenButton.setOnClickListener {
                orientationUtils.resolveByClick()
                mViewModel.isFullScreen = !mViewModel.isFullScreen
            }
            backButton.setOnClickListener {
                onBackPressed()
            }
            isShowFullAnimation = !mViewModel.isFullScreen
            if(mViewModel.isFullScreen){
                startWindowFullscreen(this@VideoActivity, true, true)
                fullWindowPlayer.fullscreenButton.setOnClickListener {
                    orientationUtils.resolveByClick()
                    mViewModel.isFullScreen = !mViewModel.isFullScreen
                }
                fullWindowPlayer.backButton.setOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    /**
     * 视频详情的标题是否展开
     */
    private var mMotionStateIsStart = true

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun initVideoDetail(){
        mBinding.videoDetailTitle.text = mData.title
        mBinding.videoDetailCategory.text = mData.category + SimpleDateFormat("yyyy-M-dd HH:mm").format(Date(mData.date))
        mBinding.videoDetailDescription.text = mData.description
        mBinding.videoAuthorNickname.text = mData.nickname
        Glide.with(this).load(mData.avatar).into(mBinding.videoAuthorAvatar)
        Glide.with(this).load(mData.cover).transform(BlurTransform(100)).into(mBinding.videoDetailBackground)
    }

    private fun initMotion() {
        mBinding.videoDetailMotionButton.setOnClickListener{
            if(mMotionStateIsStart){
                mBinding.videoDetailMotion.transitionToEnd()
                mBinding.videoDetailMotionButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                mMotionStateIsStart = false
            }else {
                mBinding.videoDetailMotion.transitionToStart()
                mBinding.videoDetailMotionButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                mMotionStateIsStart = true
            }
        }
    }

    override fun showFailedPage() {
        mBinding.videoRecommendRecycler.visibility = View.GONE
        mBinding.failedPage.visibility = View.VISIBLE
        mBinding.failedImage.apply {
            setAnimation("failed.json")
            loop(false)
            playAnimation()
        }
    }

    private fun initFailPage() {
        mBinding.failedButton.setOnClickListener{
            mBinding.failedImage.apply {
                setAnimation("loading.json")
                loop(true)
                playAnimation()
            }
            mViewModel.getVideoRecommend(mData.id)
        }
    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            mViewModel.isFullScreen = false
            return
        }
        super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        mViewModel.getVideoRecommend(mData.id)
    }

    override fun onResume() {
        mBinding.videoPlayer.currentPlayer.startPlayLogic()
        super.onResume()
    }

    override fun onPause() {
        mBinding.videoPlayer.currentPlayer.onVideoPause()
        super.onPause()
    }

    override fun onDestroy() {
        mBinding.videoPlayer.release()
        orientationUtils.releaseListener()
        super.onDestroy()
    }

    private fun handleVideoRecommendData(data: VideoData) {
        mBinding.videoRecommendRecycler.visibility = View.VISIBLE
        mBinding.failedPage.visibility = View.GONE
        mBinding.videoRecommendRecycler.adapter = VideoRecyclerAdapter(this, data.itemList).apply {
            videoSmallCardClickListener = { view, data ->
                startActivity(
                    context = this@VideoActivity,
                    data = convertVideoData(data)
                )
            }
        }
    }


}