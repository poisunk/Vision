package com.module.home.ui

import android.app.ActivityOptions
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.common.service.IVideoService
import com.lib.common.service.ServiceManager
import com.lib.common.ui.BaseFragment
import com.module.home.R
import com.module.home.adapter.HomePageRecyclerAdapter
import com.module.home.bean.HomeData
import com.module.home.databinding.FragmentHomeNewsBinding
import com.module.home.utils.convertVideoData
import com.module.home.viewmodel.NewsViewModel

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class NewsFragment: BaseFragment<FragmentHomeNewsBinding, NewsViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_home_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    private fun initPage() {
        mViewModel.mNewsData.observe(viewLifecycleOwner, this::handleHomeData)
    }

    private var adapter: HomePageRecyclerAdapter? = null

    private fun handleHomeData(data: HomeData) {
        adapter = HomePageRecyclerAdapter(requireActivity(), data.itemList, this)
        mBinding.homeNewsRecycler.adapter = adapter
        mBinding.homeNewsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}