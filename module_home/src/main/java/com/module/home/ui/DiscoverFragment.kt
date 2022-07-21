package com.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.common.ui.BaseFragment
import com.module.home.R
import com.module.home.adapter.HomePageRecyclerAdapter
import com.module.home.bean.HomeData
import com.module.home.databinding.FragmentHomeDiscoverBinding
import com.module.home.viewmodel.DiscoverViewModel

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class DiscoverFragment : BaseFragment<FragmentHomeDiscoverBinding, DiscoverViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_home_discover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    private fun initPage(){
        mViewModel.mDiscoverData.observe(viewLifecycleOwner, this::handleHomeDiscoverData)
    }

    private fun handleHomeDiscoverData(data: HomeData) {
        mBinding.homeDiscoverContent.visibility = View.VISIBLE
        mBinding.failedPage.visibility = View.GONE
        mBinding.homeDiscoverContent.adapter = HomePageRecyclerAdapter(requireContext(), data.itemList)
        mBinding.homeDiscoverContent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun showFailedPage() {
        mBinding.homeDiscoverContent.visibility = View.GONE
        mBinding.failedPage.visibility = View.VISIBLE
    }

}