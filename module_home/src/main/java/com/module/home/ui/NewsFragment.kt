package com.module.home.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.common.ui.BaseFragment
import com.module.home.R
import com.module.home.adapter.HomePageRecyclerAdapter
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
        mViewModel.mNewsData.observe(viewLifecycleOwner, this::handleHomeData)
        initFailPage()
    }

    private var adapter: HomePageRecyclerAdapter? = null

    private fun handleHomeData(data: HomeData) {
        mBinding.homeNewsRecycler.visibility = View.VISIBLE
        mBinding.failedPage.visibility = View.GONE
        adapter = HomePageRecyclerAdapter(requireActivity(), data.itemList, this).apply {
            onVideoPlayerDetachListener = {
                val windowInsetsController = ViewCompat.getWindowInsetsController(requireActivity().window.decorView)
                windowInsetsController?.isAppearanceLightStatusBars = true
            }
        }
        mBinding.homeNewsRecycler.adapter = adapter
        mBinding.homeNewsRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun showFailedPage() {
        mBinding.homeNewsRecycler.visibility = View.GONE
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
            mViewModel.getNewsData()
        }
    }
}