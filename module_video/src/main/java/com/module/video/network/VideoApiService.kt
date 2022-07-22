package com.module.video.network

import com.module.video.bean.VideoData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface VideoApiService {

    @GET("v4/video/related")
    fun getVideoRecommend(@Query("id") id: Long): Observable<VideoData>
}