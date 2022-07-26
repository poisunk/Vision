package com.module.home.bean

import java.io.Serializable

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
): Serializable

data class Data(
    val id: Long,
    val itemList: List<ItemList>,
    val type: String,
    val text: String,
    val title: String,
    val description: String,
    val image: String,
    val actionUrl: String,
    val header: Header,
    val content: Content,
    val cover: Cover,
    val owner: Owner,
    val duration: Int,
    val tags: List<Tag>,
    val icon: String,
    val category: String,
    val author: Author,
    val playUrl: String,
    val date: Long,
    val consumption: Consumption
): Serializable

data class Label(
    val text: String,
    val card: String,
    val detail: Any
): Serializable

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
): Serializable

data class Content(
    val type: String,
    val data: Data,
): Serializable

data class Cover(
    val feed: String
): Serializable

data class Owner(
    val avatar: String,
    val nickname: String
): Serializable

data class Tag(
    val name: String
): Serializable

data class Author(
    val name: String,
    val icon: String,
    val description: String
): Serializable

data class Consumption(
    val collectionCount: Int,
    val shareCount: Int,
    val replyCount: Int
): Serializable