package com.example.liunianyishi.intelligenttransportation.Interface

import com.example.liunianyishi.intelligenttransportation.Presenter.mPresenter

/**
 * Created by qiuchen on 2018/1/31.
 */
interface BaseImpl {
    fun login(u: String, p: String, cb: mPresenter.loginCallback)
    fun queryillegal(carID: String, cb: mPresenter.queryCallback)
}