package com.lib.common.ext

import android.widget.Toast
import com.lib.common.BaseApp

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */

fun toast(text: String) {
    Toast.makeText(BaseApp.appContext, text, Toast.LENGTH_SHORT).show()
}

fun String.makeToast() {
    Toast.makeText(BaseApp.appContext, this, Toast.LENGTH_SHORT).show()
}