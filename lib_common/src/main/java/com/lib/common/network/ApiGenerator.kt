package com.lib.common.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object ApiGenerator {

    private const val BASE_URL="http://baobab.kaiyanapp.com/api/"

    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getHttpConfig())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun getHttpConfig(): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(10000, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()

    fun <T> create(serviceClass:Class<T>):T= retrofit.create(serviceClass)

}