package com.module.notification

import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.common.base.BaseFragment
import com.lib.common.config.ARouterTable
import com.module.notification.databinding.FragmentNotificationBinding

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@Route(path = ARouterTable.NOTIFICATION)
class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_notification
}