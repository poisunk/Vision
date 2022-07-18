package com.module.home.page.discover

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lib.common.adapter.BannerViewAdapter
import com.lib.common.base.BaseFragment
import com.lib.common.widget.BannerView
import com.module.home.R

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class DiscoverFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_home_discover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage(view)

    }

    private fun initPage(v: View){
        val mBanner = v.findViewById<BannerView>(R.id.home_discover_banner)
        val data = listOf(1,2,3,4,5,6,7,8,9,10).map {
            it.toString()
        }
        mBanner.setAdapter(BannerViewAdapter(data))
    }

}