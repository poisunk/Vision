package com.module.home.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.adapter.SpecialSquareCardRecyclerAdapter
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class SpecialCardHolder(private val v: View):  BaseHomeViewHolder(v){
    private val title: TextView = v.findViewById(R.id.special_card_title)
    private val rightTitle: TextView = v.findViewById(R.id.special_card_right_title)
    private val recycler: RecyclerView = v.findViewById(R.id.special_card_recycler)

    override fun onBindView(context: Context, data: Data) {
        title.text = data.header.title
        rightTitle.text = data.header.rightText
        recycler.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        recycler.adapter = SpecialSquareCardRecyclerAdapter(data.itemList, context)
    }
}