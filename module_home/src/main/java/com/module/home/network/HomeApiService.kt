package com.module.home.network

import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface HomeApiService{

    @GET()
    fun getDiscover():Observable<Objects>
}