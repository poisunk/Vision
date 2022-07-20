package com.module.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.adapter.BaseFragmentPagerAdapter
import com.lib.common.base.BaseFragment
import com.lib.common.base.BaseViewModel
import com.lib.common.config.ARouterTable
import com.module.home.databinding.FragmentHomeBinding
import com.module.home.ui.DiscoverFragment
import com.module.home.ui.NewsFragment
import com.module.home.ui.RecommendFragment

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.HOME)
class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    private fun initPage() {
        val fragments = arrayListOf<Fragment>(DiscoverFragment(), RecommendFragment(), NewsFragment())
        mBinding.homeViewPager.adapter = BaseFragmentPagerAdapter(fragments, childFragmentManager, lifecycle)
    }

}