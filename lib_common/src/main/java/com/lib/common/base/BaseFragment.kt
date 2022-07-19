@file:Suppress("UNCHECKED_CAST")

package com.lib.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.lib.common.action.BaseActionEvent
import com.lib.common.ext.makeToast
import com.lib.common.ext.observeWithLifecycle
import java.lang.reflect.ParameterizedType


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseFragment<T : ViewDataBinding, B: BaseViewModel> : Fragment() {

    lateinit var mBinding: T

    lateinit var mViewModel: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<B>
        mViewModel = ViewModelProvider(this).get(viewModelClass)
        mViewModel.mActionLiveData.observeWithLifecycle(
            fragment = this,
            minActiveState = Lifecycle.State.RESUMED,
            this::handleActionEvent
        )
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mBinding.root
    }

    private fun handleActionEvent(event: BaseActionEvent) {
        when(event.action) {
            BaseActionEvent.SHOW_LOADING_DIALOG -> {
                showLoading()
            }
            BaseActionEvent.DISMISS_LOADING_DIALOG -> {
                dismissLoading()
            }
            BaseActionEvent.SHOW_TOAST -> {
                showToast(event.message)
            }
            BaseActionEvent.SHOW_FAILED_PAGE -> {
                showFailedPage()
            }
        }
    }

    protected open fun showLoading() {}

    protected open fun dismissLoading() {}

    protected open fun showToast(message: String?) {message?.makeToast()}

    protected open fun showFailedPage() {}

    protected abstract fun getLayoutId(): Int

}