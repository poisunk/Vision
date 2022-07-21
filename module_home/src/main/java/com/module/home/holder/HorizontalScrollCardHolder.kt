package com.module.home.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lib.common.adapter.BannerViewAdapter
import com.lib.common.widget.BannerView
import com.module.home.R
import com.module.home.bean.Data

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class HorizontalScrollCardHolder(private val v: View): BaseHomeViewHolder(v) {
    private val banner: BannerView = v.findViewById(R.id.item_banner)

    override fun onBindView(context: Context, data: Data) {
        val urls = data.itemList.map {
            it.data.image
        }
        banner.setAdapter(BannerViewAdapter(urls, context))
    }
}