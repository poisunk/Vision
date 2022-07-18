package com.module.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lib.common.base.BaseViewModel
import com.lib.common.network.ApiGenerator
import com.module.home.bean.HomeData
import com.module.home.network.HomeApiService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class HomeDiscoverViewModel : BaseViewModel() {

    private val _discoverLiveData = MutableLiveData<HomeData>()
    val mDiscoverData: LiveData<HomeData>
        get() = _discoverLiveData

    private val mHomeApiService = ApiGenerator.create(HomeApiService::class.java)

    fun getHomeDiscoverData() {
        mHomeApiService.getDiscover()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeData> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: HomeData) {
                    _discoverLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("Discover", e.toString())
                }

                override fun onComplete() {

                }
            })
    }

}