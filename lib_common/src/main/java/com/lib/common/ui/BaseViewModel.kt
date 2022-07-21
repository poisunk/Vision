package com.lib.common.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lib.common.action.BaseActionEvent
import com.lib.common.action.IViewModelAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
open class BaseViewModel : ViewModel(), IViewModelAction {

    private val actionLiveData = Channel<BaseActionEvent>()
    val mActionLiveData: Flow<BaseActionEvent>
        get() = actionLiveData.receiveAsFlow()

    override fun startLoading() {
        val action = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG)
        sendAction(action)
    }

    override fun dismissLoading() {
        val action = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
        sendAction(action)
    }

    override fun showToast(message: String) {
        val action = BaseActionEvent(BaseActionEvent.SHOW_TOAST)
        action.message = message
        sendAction(action)
    }

    override fun showFailedPage() {
        val action = BaseActionEvent(BaseActionEvent.SHOW_FAILED_PAGE)
        sendAction(action)
    }

    private fun sendAction(action: BaseActionEvent) {
        viewModelScope.launch {
            actionLiveData.send(action)
        }
    }

}