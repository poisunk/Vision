package com.example.vision

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.example.vision.databinding.ActivityMainBinding
import com.lib.common.base.BaseActivity
import com.lib.common.config.ARouterTable


/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val HOME = 0
    private val COMMUNITY = 1
    private val NOTIFICATION = 2
    private val MINE = 3

    private val fragments = mutableListOf<Fragment>()

    private val navList = mutableListOf<CheckedTextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }

    /**
     * 初始化底部导航栏
     */
    private fun initBottomNav() {
        // 将所有底部导航键放到list里方便维护
        mBinding?.apply {
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
        navList[HOME].scaleX = 1.2f
        navList[HOME].scaleY = 1.2f
        replaceFragment(HOME)
    }

    private fun startBottomAnim(i: Int) {
        val v = navList[i]
        v.isChecked = true

        // 开启动画
        ValueAnimator.ofFloat(1f,1.2f).apply {
            duration = 300L
            addUpdateListener {
                v.scaleX = it.animatedValue as Float
                v.scaleY = it.animatedValue as Float
            }
        }.start()

        // 清除其他navigation的动画效果
        for((j, view) in navList.withIndex()){
            if(j != i){
                view.isChecked = false

                view.scaleY = 1f
                view.scaleX = 1f
            }
        }
    }

    private fun replaceFragment(i: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragments[i]).commit()
    }




}