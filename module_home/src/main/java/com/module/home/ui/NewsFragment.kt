package com.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.common.base.BaseFragment
import com.lib.common.base.BaseViewModel
import com.module.home.R
import com.module.home.adapter.NewsRecyclerAdapter
import com.module.home.bean.HomeData
import com.module.home.databinding.FragmentHomeNewsBinding
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
        mBinding.homeNewsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mViewModel.mNewsData.observe(viewLifecycleOwner, this::handleHomeData)
    }

    private fun handleHomeData(data: HomeData) {
        val adapter = NewsRecyclerAdapter(data.itemList)
        mBinding.homeNewsRecycler.adapter = adapter
    }
}