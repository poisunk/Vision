package com.lib.common.core

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface IViewModelAction {

    fun startLoading()

    fun dismissLoading()

    fun showToast(message: String)

    fun showFailedPage()
}