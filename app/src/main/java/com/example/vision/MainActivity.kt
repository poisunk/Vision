package com.example.vision

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.CheckedTextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.example.vision.databinding.ActivityMainBinding
import com.lib.common.adapter.BaseFragmentPagerAdapter
import com.lib.common.ui.BaseActivity
import com.lib.common.ui.BaseViewModel
import com.lib.common.config.ARouterTable


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    private val HOME = 0
    private val COMMUNITY = 1
    private val NOTIFICATION = 2
    private val MINE = 3

    private val fragments = mutableListOf<Fragment>()

    private val navList = mutableListOf<CheckedTextView>()

    override fun init() {
        initPage()
        initBottomNav()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    /**
     * 初始化界面，并添加到fragments中
     */
    private fun initPage(){
        fragments.add(HOME, ARouter.getInstance().build(ARouterTable.HOME).navigation() as Fragment)
        fragments.add(COMMUNITY, ARouter.getInstance().build(ARouterTable.COMMUNITY).navigation() as Fragment)
        fragments.add(NOTIFICATION, ARouter.getInstance().build(ARouterTable.NOTIFICATION).navigation() as Fragment)
        fragments.add(MINE, ARouter.getInstance().build(ARouterTable.MINE).navigation() as Fragment)

        mBinding.mainContainer.adapter = BaseFragmentPagerAdapter(fragments, supportFragmentManager, lifecycle)
        mBinding.mainContainer.isUserInputEnabled = false
    }

    /**
     * 初始化底部导航栏
     */
    private fun initBottomNav() {

        // 将所有底部导航键放到list里方便切换
        mBinding.apply {
            navList.addAll(arrayOf(
                mainBottomNavHome,
                mainBottomNavCommunity,
                mainBottomNavNotification,
                mainBottomNavMine
            ))
        }

        // 设置点击事件
        for((i, view) in navList.withIndex()) {
            view.setOnClickListener(View.OnClickListener {
                if(!view.isChecked){
                    startBottomAnim(i)
                    replaceFragment(i)
                }
            })
        }

        // 初始化导航栏
        startBottomAnim(HOME)
        replaceFragment(HOME)
    }

    /**
     * 设置底部导航栏的动画
     */
    private fun startBottomAnim(i: Int) {
        val v = navList[i]
        v.isChecked = true

        // 开启动画
        val animation = ScaleAnimation(1f, 1.1f, 1f, 1.1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.duration = 300L
        animation.fillAfter = true
        v.startAnimation(animation)

        // 清除其他navigation的动画效果
        for((j, view) in navList.withIndex()){
            if(j != i && view.isChecked){
                view.isChecked = false
                val animation = ScaleAnimation(1.1f, 1f, 1.1f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                animation.duration = 300L
                animation.fillAfter = true
                view.startAnimation(animation)
            }
        }
    }

    /**
     * 切换fragment
     */
    private fun replaceFragment(i: Int) {
        mBinding.mainContainer.setCurrentItem(i, false)
    }



}