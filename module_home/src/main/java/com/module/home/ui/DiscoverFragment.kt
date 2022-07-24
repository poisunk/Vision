package com.module.home.ui

import android.animation.Animator
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
        initFailPage()
    }

    private fun handleHomeDiscoverData(data: HomeData) {
        mBinding.homeDiscoverRecycler.visibility = View.VISIBLE
        mBinding.failedPage.visibility = View.GONE
        mBinding.homeDiscoverRecycler.adapter = HomePageRecyclerAdapter(requireContext(), data.itemList, this)
        mBinding.homeDiscoverRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun showFailedPage() {
        mBinding.homeDiscoverRecycler.visibility = View.GONE
        mBinding.failedPage.visibility = View.VISIBLE
        mBinding.failedImage.apply {
            setAnimation("failed.json")
            loop(false)
            playAnimation()
        }
    }

    private fun initFailPage() {
        mBinding.failedButton.setOnClickListener{
            mBinding.failedImage.apply {
                setAnimation("loading.json")
                loop(true)
                playAnimation()
            }
            mViewModel.getHomeDiscoverData()
        }
    }

}