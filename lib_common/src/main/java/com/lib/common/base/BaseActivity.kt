@file:Suppress("UNCHECKED_CAST")

package com.lib.common.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
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
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<B>
        mViewModel = ViewModelProvider(this).get(viewModelClass)
    }

    private fun cancelStatusBar() {
        val decorView = window.decorView
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = true
        decorView.fitsSystemWindows = false
        window.statusBarColor = Color.TRANSPARENT
    }

    protected abstract fun getLayoutId(): Int
}