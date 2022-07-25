package com.module.video.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lib.common.network.ApiGenerator
import com.lib.common.ui.BaseViewModel
import com.module.video.bean.VideoData
import com.module.video.network.VideoApiService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class VideoViewModel: BaseViewModel() {
    private val _videoLiveData = MutableLiveData<VideoData>()
    val mVideoData: LiveData<VideoData>
        get() = _videoLiveData

    var isFullScreen = false

    private val mVideoApiService = ApiGenerator.create(VideoApiService::class.java)

    fun getVideoRecommend(id: Long) {
        mVideoApiService.getVideoRecommend(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<VideoData> {
                override fun onSubscribe(d: Disposable) {
                    startLoading()
                }

                override fun onNext(t: VideoData) {
                    _videoLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    dismissLoading()
                    showFailedPage()
                }

                override fun onComplete() {
                    dismissLoading()
                }

            })
    }
}