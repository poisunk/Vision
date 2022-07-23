package com.module.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.module.video.R
import com.module.video.holder.BaseVideoViewHolder
import com.module.video.bean.ItemList
import com.module.video.holder.EmptyHolder
import com.module.video.holder.TextCardHolder
import com.module.video.holder.VideoSmallCardHolder

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class VideoRecyclerAdapter(
    private val context: Context,
    private val itemList: List<ItemList>
): RecyclerView.Adapter<BaseVideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVideoViewHolder {
        return when(viewType){
            VideoItemType.TEXT_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false)
                TextCardHolder(view)
            }
            VideoItemType.VIDEO_SMALL_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.video_item_video_small_card, parent, false)
                VideoSmallCardHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                EmptyHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position].type) {
            "textCard" -> VideoItemType.TEXT_CARD.ordinal
            "videoSmallCard" -> VideoItemType.VIDEO_SMALL_CARD.ordinal
            else -> VideoItemType.EMPTY.ordinal
        }
    }

    override fun onBindViewHolder(holder: BaseVideoViewHolder, position: Int) {
        holder.onBindView(context, itemList[position].data)
    }

    override fun getItemCount(): Int = itemList.size + 1
}