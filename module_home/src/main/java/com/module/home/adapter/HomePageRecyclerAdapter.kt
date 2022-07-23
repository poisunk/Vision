package com.module.home.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.module.home.R
import com.module.home.bean.ItemList
import com.module.home.holder.*
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class HomePageRecyclerAdapter(
    private val context: Activity,
    private val itemList: List<ItemList>
): RecyclerView.Adapter<BaseHomeViewHolder>() {

    private val mVideoPlayerList = mutableListOf<StandardGSYVideoPlayer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeViewHolder {
        return when (viewType) {
            HomeItemType.HORIZONTAL_SCROLL_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_scroll_card, parent, false)
                HorizontalScrollCardHolder(view)
            }
            HomeItemType.TEXT_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text_card, parent, false)
                TextCardHolder(view)
            }
            HomeItemType.SPECIAL_SQUARE_CARD_COLLECTION.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_square_card, parent, false)
                SpecialCardHolder(view)
            }
            HomeItemType.VIDEO_SMALL_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_small_card, parent, false)
                VideoSmallCardHolder(view)
            }
            HomeItemType.BRIEF_CARD.ordinal -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_brief_card, parent, false)
                BriefCardHolder(view)
            }
            HomeItemType.FOLLOW_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_follow_card, parent, false)
                FollowCardHolder(context, view)
            }
            HomeItemType.AUTO_PLAY_FOLLOW_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_auto_play_follow_card, parent, false)
                AutoPlayCardHolder(view).apply {
                    mVideoPlayerList.add(this.videoPlayer)
                }
            }
            HomeItemType.SQUARE_CARD_COLLECTION.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_special_square_card, parent, false)
                SquareCardCollectionHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
                EmptyHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position].type) {
            "horizontalScrollCard" -> {
                HomeItemType.HORIZONTAL_SCROLL_CARD.ordinal
            }
            "squareCardCollection" -> {
                HomeItemType.SQUARE_CARD_COLLECTION.ordinal
            }
            "textCard" -> {
                HomeItemType.TEXT_CARD.ordinal
            }
            "specialSquareCardCollection" -> {
                HomeItemType.SPECIAL_SQUARE_CARD_COLLECTION.ordinal
            }
            "videoSmallCard" -> {
                HomeItemType.VIDEO_SMALL_CARD.ordinal
            }
            "briefCard" -> {
                HomeItemType.BRIEF_CARD.ordinal
            }
            "followCard" -> {
                HomeItemType.FOLLOW_CARD.ordinal
            }
            "autoPlayFollowCard" -> {
                HomeItemType.AUTO_PLAY_FOLLOW_CARD.ordinal
            }
            else -> {
                HomeItemType.EMPTY.ordinal
            }
        }
    }

    override fun onBindViewHolder(holder: BaseHomeViewHolder, position: Int) {
        holder.onBindView(context, itemList[position].data)
    }

    override fun getItemCount(): Int = itemList.size

    fun pauseVideo() {
        mVideoPlayerList.forEach{
            it.onVideoPause()
        }
    }
}