package com.lib.common.ext

import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
fun BottomNavigationView.setOnItemChangeListener(func: (id: Int) -> Boolean) {
    setOnItemSelectedListener {
        func.invoke(it.itemId)
    }
}