package com.module.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.adapter.BaseFragmentPagerAdapter
import com.lib.common.config.ARouterTable
import com.lib.common.ext.forceSetOverScrollMode
import com.lib.common.ui.BaseFragment
import com.lib.common.ui.BaseViewModel
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
        val tabs: List<ImageView>
        mBinding.apply {
            tabs = listOf(
                homeBarDiscoverPointer,
                homeBarRecommendPointer,
                homeBarNewsPointer
            )
        }
        val fragments = arrayListOf<Fragment>(DiscoverFragment(), RecommendFragment(), NewsFragment())
        mBinding.homeViewPager.forceSetOverScrollMode(View.OVER_SCROLL_NEVER)
        mBinding.homeViewPager.adapter = BaseFragmentPagerAdapter(fragments, childFragmentManager, lifecycle)
        mBinding.homeViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for((i, v) in tabs.withIndex()){
                    if (i != position) {
                        v.visibility = View.GONE
                    }else {
                        v.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}