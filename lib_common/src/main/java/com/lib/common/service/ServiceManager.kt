package com.lib.common.service

import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import kotlin.reflect.KClass

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object ServiceManager {

    fun <T: Any> getService(clazz: KClass<T>): T {
        Log.d("COMMON", clazz.toString())
        return ARouter.getInstance().navigation(clazz.java)
    }

    fun startActivity(path: String) {
        Log.d("COMMON", path)
        ARouter.getInstance().build(path).navigation()
    }
}