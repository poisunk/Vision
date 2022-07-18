package com.module.home.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.lib.common.adapter.BannerViewAdapter
import com.lib.common.base.BaseFragment
import com.module.home.R
import com.module.home.bean.HomeData
import com.module.home.databinding.FragmentHomeDiscoverBinding
import com.module.home.viewmodel.HomeDiscoverViewModel

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class DiscoverFragment : BaseFragment<FragmentHomeDiscoverBinding>() {

    private lateinit var mViewModel: HomeDiscoverViewModel

    override fun getLayoutId(): Int = R.layout.fragment_home_discover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(HomeDiscoverViewModel::class.java)
        initPage()
    }

    private fun initPage(){
        mViewModel.mDiscoverData.observe(viewLifecycleOwner, this::handleHomeDiscoverData)
        mViewModel.getHomeDiscoverData()
    }

    private fun handleHomeDiscoverData(data: HomeData) {
        Log.d("Discover", data.itemList[0].type)
        val urls = data.itemList[0].data.itemList.map {
            it.data.image
        }
        mBinding.homeDiscoverBanner.setAdapter(BannerViewAdapter(urls, requireContext()))
    }

}