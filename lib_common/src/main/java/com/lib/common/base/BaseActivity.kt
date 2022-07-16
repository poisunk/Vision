package com.lib.common.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseActivity<T:ViewDataBinding> : AppCompatActivity(){

    var mBinding:T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cancelStatusBar()
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    private fun cancelStatusBar() {
        val decorView = window.decorView
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = true
        decorView.fitsSystemWindows = false
        window.statusBarColor = Color.TRANSPARENT
    }

    abstract fun getLayoutId():Int
}