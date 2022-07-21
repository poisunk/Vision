package com.module.home.network

import com.module.home.bean.HomeData
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface HomeApiService{

    @GET("v7/index/tab/discovery?udid=fa53872206ed42e3857755c2756ab683fc22d64a&vc=591&vn=6.2.1&size=720X1280&deviceModel=Che1-CL20&first_channel=eyepetizer_zhihuiyun_market&last_channel=eyepetizer_zhihuiyun_market&system_version_code=19")
    fun getDiscoverData(): Observable<HomeData>

    @GET("v5/index/tab/allRec")
    fun getRecommendData(): Observable<HomeData>

    @GET("v5/index/tab/feed")
    fun getNewsData(): Observable<HomeData>
}