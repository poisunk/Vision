package com.module.video.bean

import com.module.video.bean.ItemList
import java.io.Serializable


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class VideoData(
    val itemList: List<ItemList>,
    val count: Int,
    val total: Int,
    val nextPageUrl: String,
    val adExist: Boolean
): Serializable