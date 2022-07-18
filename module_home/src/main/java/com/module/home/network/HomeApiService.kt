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

    @GET("v7/index/tab/discovery")
    fun getDiscover(): Observable<HomeData>
}