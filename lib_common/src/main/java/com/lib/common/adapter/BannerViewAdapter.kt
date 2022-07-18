package com.lib.common.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lib.common.R
import com.lib.common.widget.BannerView
import java.util.zip.Inflater

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BannerViewAdapter(private val data: List<String>) : RecyclerView.Adapter<BannerViewAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val text: TextView = v.findViewById<TextView>(R.id.item_banner_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0){
            holder.text.text = data[data.size - 1]
        }else if(position == itemCount - 1) {
            holder.text.text = data[0]
        }else {
            holder.text.text = data[position - 1]
        }
    }

    override fun getItemCount(): Int = data.size + 2
}