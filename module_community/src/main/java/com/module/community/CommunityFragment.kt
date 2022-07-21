package com.module.community

import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.ui.BaseFragment
import com.lib.common.ui.BaseViewModel
import com.lib.common.config.ARouterTable
import com.module.community.databinding.FragmentCommunityBinding

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.COMMUNITY)
class CommunityFragment : BaseFragment<FragmentCommunityBinding, BaseViewModel>(){

    override fun getLayoutId(): Int = R.layout.fragment_community
}