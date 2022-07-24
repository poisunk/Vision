package com.module.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lib.common.ui.BaseViewModel
import com.lib.common.network.ApiGenerator
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
class NewsViewModel : BaseViewModel() {

    private val _newsLiveData = MutableLiveData<HomeData>()
    val mNewsData: LiveData<HomeData>
        get() = _newsLiveData

    private val mHomeApiService = ApiGenerator.create(HomeApiService::class.java)

    init {
        viewModelScope.launch {
            getNewsData()
        }
    }

    fun getNewsData() {
        mHomeApiService.getNewsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HomeData> {
                override fun onSubscribe(d: Disposable) {
                    startLoading()
                }

                override fun onNext(t: HomeData) {
                    _newsLiveData.value = t
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