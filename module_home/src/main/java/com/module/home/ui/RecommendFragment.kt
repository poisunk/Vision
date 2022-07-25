package com.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        mBinding.homeRecommendRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                mBinding.homeRecommendRecycler.apply {
                    isEnabled = childCount == 0 || recyclerView.getChildAt(0).top >= 0
                }
            }
        })
        mBinding.recommendRefresh.setOnRefreshListener {
            mViewModel.getRecommendData()
        }
        initFailPage()
    }

    private fun handleHomeData(data: HomeData) {
        mBinding.apply {
            homeRecommendRecycler.visibility = View.VISIBLE
            failedPage.visibility = View.GONE
            homeRecommendRecycler.adapter = HomePageRecyclerAdapter(requireContext(), data.itemList, this@RecommendFragment)
            homeRecommendRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recommendRefresh.isRefreshing = false
        }
    }

    override fun showFailedPage() {
        mBinding.homeRecommendRecycler.visibility = View.GONE
        mBinding.failedPage.visibility = View.VISIBLE
        mBinding.failedImage.apply {
            setAnimation("failed.json")
            loop(false)
            playAnimation()
        }
        mBinding.recommendRefresh.isRefreshing = false
    }

    private fun initFailPage() {
        mBinding.failedButton.setOnClickListener{
            mBinding.failedImage.apply {
                setAnimation("loading.json")
                loop(true)
                playAnimation()
            }
            mViewModel.getRecommendData()
        }
    }
}