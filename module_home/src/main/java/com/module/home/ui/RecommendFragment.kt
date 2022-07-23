package com.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.common.ui.BaseFragment
import com.module.home.R
import com.module.home.adapter.HomePageRecyclerAdapter
import com.module.home.bean.HomeData
import com.module.home.databinding.FragmentHomeRecommendBinding
import com.module.home.viewmodel.RecommendViewModel

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class RecommendFragment: BaseFragment<FragmentHomeRecommendBinding, RecommendViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_home_recommend

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    private fun initPage() {
        mViewModel.mRecommendData.observe(viewLifecycleOwner, this::handleHomeData)
    }

    private fun handleHomeData(data: HomeData) {
        val adapter = HomePageRecyclerAdapter(requireContext(), data.itemList, this)
        mBinding.homeRecommendRecycler.adapter = adapter
        mBinding.homeRecommendRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }
}