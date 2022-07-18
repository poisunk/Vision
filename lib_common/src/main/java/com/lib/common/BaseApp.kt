package com.lib.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseApp : Application() {

    companion object {
        lateinit var appContext: Context
            private set
    }

    private val isDebug = false

    override fun onCreate() {
        super.onCreate()
        appContext = this
        if(isDebug){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}