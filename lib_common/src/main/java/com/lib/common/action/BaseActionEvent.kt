package com.lib.common.action

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BaseActionEvent(var action: Int = 0){

    companion object {
        // 显示loading
        const val SHOW_LOADING_DIALOG = 1

        // 取消显示loading
        const val DISMISS_LOADING_DIALOG = 2

        // 实现Toast
        const val SHOW_TOAST = 3

        // 显示请求失败时的界面
        const val SHOW_FAILED_PAGE = 4
    }

    var message: String? = null
}