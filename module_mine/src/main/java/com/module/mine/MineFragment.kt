package com.module.mine

import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.base.BaseFragment
import com.lib.common.config.ARouterTable
import com.module.mine.databinding.FragmentMineBinding

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.MINE)
class MineFragment : BaseFragment<FragmentMineBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_mine
}