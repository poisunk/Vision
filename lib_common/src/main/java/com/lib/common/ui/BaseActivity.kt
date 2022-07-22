@file:Suppress("UNCHECKED_CAST")

package com.lib.common.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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
abstract class BaseActivity<T: ViewDataBinding, B: BaseViewModel> : AppCompatActivity(){

    lateinit var mBinding: T

    lateinit var mViewModel: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cancelStatusBar()
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<B>
        mViewModel = ViewModelProvider(this).get(viewModelClass)
        mViewModel.mActionLiveData.observeWithLifecycle(
            lifecycleOwner = this,
            minActiveState = Lifecycle.State.RESUMED,
            this::handleActionEvent
        )
        init()
    }

    private fun cancelStatusBar() {
        val decorView = window.decorView
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = true
        window.statusBarColor = Color.TRANSPARENT
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

    protected abstract fun init()
}