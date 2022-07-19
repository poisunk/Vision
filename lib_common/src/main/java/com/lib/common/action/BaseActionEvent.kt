package com.lib.common.action

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BaseActionEvent(var action: Int = 0){

    companion object {
        const val SHOW_LOADING_DIALOG = 1

        const val DISMISS_LOADING_DIALOG = 2

        const val SHOW_TOAST = 3

        const val SHOW_FAILED_PAGE = 4
    }

    var message: String? = null
}