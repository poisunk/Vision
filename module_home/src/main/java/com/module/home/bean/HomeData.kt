package com.module.home.bean

import java.io.Serializable


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class HomeData(
    val itemList: List<ItemList>,
    val count: Int,
    val total: Int,
    val nextPageUrl: String,
    val adExist: Boolean
): Serializable