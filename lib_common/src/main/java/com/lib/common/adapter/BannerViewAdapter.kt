package com.lib.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lib.common.R
import com.lib.common.widget.BannerView
import java.util.zip.Inflater

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class BannerViewAdapter(
    private val data: List<String>,
    private val context: Context
) : RecyclerView.Adapter<BannerViewAdapter.ViewHolder>() {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById<ImageView>(R.id.item_banner_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val index = if (position == 0){
            data.size - 1
        }else if(position == itemCount - 1) {
            0
        }else {
            position - 1
        }
        Glide.with(context).load(data[index]).into(holder.image)
    }

    override fun getItemCount(): Int = data.size + 2
}