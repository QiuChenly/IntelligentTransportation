package com.example.liunianyishi.intelligenttransportation.Adapter

import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter
import com.example.liunianyishi.intelligenttransportation.View.ViewReslove.illegalQueryResult
import com.example.liunianyishi.intelligenttransportation.View.ViewReslove.illegalQueryViewInstance

/**
 * Created by qiuchen on 2018/1/31.
 * optimization RAM
 */
class mPageChangedListener(var viewList: MutableList<View>, private val cb: mPresenter.queryCallback) : ViewPager.OnPageChangeListener {
    var mViews = HashMap<Int, Boolean>()

    init {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    fun pageBeDestroy(p: Int) {
//        mViews[p] = false
        //发现奇特的生命周期,VP并不会销毁View实例,只是从容器中移除了View,但没有销毁View实例
        println("已收到此页面被销毁的信息:" + p)
    }

    override fun onPageSelected(position: Int) {
        val state = mViews[position]
        if (state == null || state == false) {
            mViews[position] = true
            val v = viewList[position]
            when (position) {
                0 -> {
                    //用户管理
                    with(v) {

                    }
                }
                1 -> {
                    //公交查询
                }
                2 -> {
                    //红绿灯管理
                }
                3 -> {
                    //违章查询
                    illegalQueryViewInstance(v, cb)
                }
                4 -> {
                    //查询结果
                    illegalQueryResult(v, cb)
                }
                5 -> {
                    //监控抓拍
                }
                6 -> {
                    //路况查询
                }
                7 -> {
                    //生活助手
                }
                8 -> {
                    //数据分析
                }
                9 -> {
                    //个人中心
                }
                10 -> {
                    //你的创意
                }
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}