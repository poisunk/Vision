package com.module.home.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.bean.Data
import com.module.home.bean.ItemList
import com.module.home.holder.AutoPlayCardHolder
import com.module.home.holder.FollowCardHolder

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class CardCollectionRecyclerAdapter(
    private val context: Context,
    private val itemList: List<ItemList>
): RecyclerView.Adapter<FollowCardHolder>() {

    // 视频封面点击事件
    var onVideoCoverClickListener: ((View, Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_follow_card, parent, false)
        return FollowCardHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: FollowCardHolder, position: Int) {
        holder.onBindView(context, itemList[position].data)
        holder.cover.setOnClickListener {
            onVideoCoverClickListener?.invoke(
                it,
                itemList[position].data.content.data
            )
        }
    }
}