package com.module.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.adapter.BaseFragmentPagerAdapter
import com.lib.common.base.BaseFragment
import com.lib.common.base.BaseViewModel
import com.lib.common.config.ARouterTable
import com.module.home.R
import com.module.home.databinding.FragmentHomeBinding

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
        val fragments = arrayListOf<Fragment>(DiscoverFragment())
        mBinding.homeViewPager.adapter = BaseFragmentPagerAdapter(fragments, childFragmentManager, lifecycle)
    }

}