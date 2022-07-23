package com.module.home.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lib.common.service.IVideoService
import com.lib.common.service.ServiceManager
import com.module.home.HomeFragment
import com.module.home.R
import com.module.home.bean.Data
import com.module.home.bean.ItemList
import com.module.home.holder.*
import com.module.home.utils.convertVideoData
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class HomePageRecyclerAdapter(
    private val context: Context,
    private val itemList: List<ItemList>,
    fragment: Fragment
): RecyclerView.Adapter<BaseHomeViewHolder>() {

    var onVideoCoverClickListener: ((View, Data) -> Unit)

    init {
        onVideoCoverClickListener = { view, data ->
            val mVideoData = convertVideoData(data)
            val mVideoService = ServiceManager.getService(IVideoService::class)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(
                fragment.requireActivity(),
                view,
                "video_player"
            ).toBundle()
            mVideoService.starActivity(fragment.requireContext(), mVideoData, bundle)
        }
    }

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
                FollowCardHolder(view)
            }
            HomeItemType.AUTO_PLAY_FOLLOW_CARD.ordinal -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_auto_play_follow_card, parent, false)
                AutoPlayCardHolder(view)
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
        when(holder) {
            is FollowCardHolder -> {
                holder.cover.setOnClickListener {
                    onVideoCoverClickListener.invoke(
                        it,
                        itemList[position].data.content.data
                    )
                }
            }
            is VideoSmallCardHolder -> {
                holder.cover.setOnClickListener {
                    onVideoCoverClickListener.invoke(
                        it,
                        itemList[position].data
                    )
                }
            }
            is SquareCardCollectionHolder -> {
                holder.onVideoCoverClickListener = this.onVideoCoverClickListener
            }
        }
        holder.onBindView(context, itemList[position].data)
    }

    private var isOtherVideoPlaying: Boolean = false

    override fun onViewDetachedFromWindow(holder: BaseHomeViewHolder) {
        super.onViewDetachedFromWindow(holder)
        when(holder) {
            is AutoPlayCardHolder -> {
                if(holder.videoPlayer.isInPlayingState) {
                    holder.videoPlayer.onVideoPause()
                    isOtherVideoPlaying = false
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: BaseHomeViewHolder) {
        super.onViewAttachedToWindow(holder)
        when(holder) {
            is AutoPlayCardHolder -> {
                if(!isOtherVideoPlaying) {
                    holder.videoPlayer.startPlayLogic()
                    isOtherVideoPlaying = true
                }
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}