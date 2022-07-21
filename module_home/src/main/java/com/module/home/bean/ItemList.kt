package com.module.home.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class ItemList(
    val type: String,
    val data: Data,
    val trackingData: Any,
    val tag: Any,
    val id: Int,
    val adIndex: Int
)

data class Data(
    val dataType: String,
    val itemList: List<ItemList>,
    val type: String,
    val text: String,
    val title: String,
    val description: String,
    val image: String,
    val actionUrl: String,
    val header: Header,
    val autoPlay: Boolean,
    val content: Content,
    val cover: Cover,
    val owner: Owner,
    val duration: Int,
    val tags: List<Tag>,
    val icon: String,
    val category: String,
    val author: Author
)

data class Label(
    val text: String,
    val card: String,
    val detail: Any
)

data class Header(
    val id: Int,
    val title: String,
    val font: String,
    val subTitle: String,
    val subTitleFont: String,
    val textAlign: String,
    val actionUrl: String,
    val rightText: String,
    val icon: String
)

data class Content(
    val type: String,
    val data: Data
)

data class Cover(
    val feed: String
)

data class Owner(
    val avatar: String,
    val nickname: String
)

data class Tag(
    val name: String
)

data class Author(
    val name: String,
    val icon: String
)