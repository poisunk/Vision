package com.module.home.holder

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.adapter.CardCollectionRecyclerAdapter
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class SquareCardCollectionHolder(private val v: View): BaseHomeViewHolder(v) {
    private val title: TextView = v.findViewById(R.id.special_card_title)
    private val rightTitle: TextView = v.findViewById(R.id.special_card_right_title)
    private val recycler: RecyclerView = v.findViewById(R.id.special_card_recycler)

    var onVideoCoverClickListener: ((View, Data) -> Unit)? = null

    override fun onBindView(context: Context, data: Data) {
        title.text = data.header.title
        rightTitle.text = data.header.rightText
        recycler.adapter = CardCollectionRecyclerAdapter(context, data.itemList).apply {
            onVideoCoverClickListener = this@SquareCardCollectionHolder.onVideoCoverClickListener
        }
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}