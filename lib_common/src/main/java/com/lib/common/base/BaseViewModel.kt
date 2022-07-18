package com.lib.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lib.common.core.BaseActionEvent
import com.lib.common.core.IViewModelAction

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
open class BaseViewModel : ViewModel(), IViewModelAction {

    private val _actionLiveData = MutableLiveData<BaseActionEvent>()
    val mActionLiveData: LiveData<BaseActionEvent>
        get() = _actionLiveData

    override fun startLoading() {
        val action = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG)
        _actionLiveData.value = action
    }

    override fun dismissLoading() {
        val action = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
        _actionLiveData.value = action
    }

    override fun showToast(message: String) {
        val action = BaseActionEvent(BaseActionEvent.SHOW_TOAST)
        action.message = message
        _actionLiveData.value = action
    }

    override fun showFailedPage() {
        val action = BaseActionEvent(BaseActionEvent.SHOW_FAILED_PAGE)
        _actionLiveData.value = action
    }

}