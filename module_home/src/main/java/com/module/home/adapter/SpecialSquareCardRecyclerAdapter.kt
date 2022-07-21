package com.module.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.module.home.R
import com.module.home.bean.ItemList

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class SpecialSquareCardRecyclerAdapter(
    private val itemList: List<ItemList>,
    private val context: Context
) : RecyclerView.Adapter<SpecialSquareCardRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val cover: ImageView = v.findViewById(R.id.square_image)
        val title: TextView = v.findViewById(R.id.square_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_square_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(itemList[position].data.image).into(holder.cover)
        holder.title.text = itemList[position].data.title
    }

    override fun getItemCount(): Int = itemList.size
}