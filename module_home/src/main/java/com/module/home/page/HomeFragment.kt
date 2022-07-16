package com.module.home.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.base.BaseFragment
import com.lib.common.config.ARouterTable

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.HOME)
class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}