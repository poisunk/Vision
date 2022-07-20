package com.module.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.holder.EmptyHolder
import com.module.home.holder.TextCardHolder
import com.module.home.bean.ItemList

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class NewsRecyclerAdapter(private val itemList: List<ItemList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position].type){
            "textCard" -> {
                HomeItemType.TEXT_CARD.ordinal
            }
            "autoPlayFollowCard" -> {
                HomeItemType.AUTO_PLAY_FOLLOW_CARD.ordinal
            }
            else -> {
                HomeItemType.EMPTY.ordinal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HomeItemType.TEXT_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false)
                TextCardHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                EmptyHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextCardHolder)
        {
            holder.text.text = itemList[position].data.text
        }
    }

    override fun getItemCount(): Int = itemList.size

}