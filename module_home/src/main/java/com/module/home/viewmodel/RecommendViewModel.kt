package com.module.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lib.common.network.ApiGenerator
import com.lib.common.ui.BaseViewModel
import com.module.home.bean.HomeData
import com.module.home.network.HomeApiService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class RecommendViewModel: BaseViewModel() {

    private val _recommendLiveData = MutableLiveData<HomeData>()
    val mRecommendData: LiveData<HomeData>
        get() = _recommendLiveData

    private val mHomeApiService = ApiGenerator.create(HomeApiService::class.java)

    init {
        viewModelScope.launch {
            getRecommendData()
        }
    }

    private fun getRecommendData(){
        mHomeApiService.getRecommendData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeData> {
                override fun onSubscribe(d: Disposable) {
                    startLoading()
                }

                override fun onNext(t: HomeData) {
                    _recommendLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    showToast("网络不可用")
                    showFailedPage()
                    dismissLoading()
                }

                override fun onComplete() {
                    dismissLoading()
                }
            })
    }
}