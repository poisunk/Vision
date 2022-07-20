package com.module.home.ui

import com.lib.common.base.BaseFragment
import com.lib.common.base.BaseViewModel
import com.module.home.R
import com.module.home.databinding.FragmentHomeRecommendBinding

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class RecommendFragment: BaseFragment<FragmentHomeRecommendBinding, BaseViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_home_recommend
}