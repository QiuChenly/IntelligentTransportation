package com.example.liunianyishi.intelligenttransportation.View.ViewReslove

import android.view.View

/**
 * Created by qiuchen on 2018/1/31.
 * 所有第三方托管View事件挂载必须继承自此类
 */
abstract class BaseViewResolve(private var v: View) : View.OnClickListener {
    fun <T : View> fb(id: Int): T {
        return v.findViewById(id)
    }

    fun <T : View> fb(id: Int, allowClick: Boolean): T {
        val t: T = v.findViewById(id)
        t.setOnClickListener(this)
        return t
    }

    //获取View上下文
    fun getContext() = v.context
}