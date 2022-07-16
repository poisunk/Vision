package com.lib.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
abstract class BaseApp : Application() {

    private val isDebug = false

    override fun onCreate() {
        super.onCreate()
        if(isDebug){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}