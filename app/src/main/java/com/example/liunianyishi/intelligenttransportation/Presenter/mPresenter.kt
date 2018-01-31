package com.example.liunianyishi.intelligenttransportation.Presenter

import com.example.liunianyishi.intelligenttransportation.Bean.illegalQuery

/**
 * Created by qiuchen on 2018/1/31.
 */
class mPresenter {
    companion object {

        const val WHO_QUERY_RESULT = 0x001
        const val WHO_QUERY_Details = 0x002


        private val data = mData()
        fun login(u: String, p: String, cb: loginCallback) {
            Thread {
                kotlin.run {
                    Thread.sleep(1450)//模拟网络请求延迟
                    data.login(u, p, object : loginCallback {
                        override fun login_Result(state: Int) {
                            cb.login_Result(state)
                        }
                    })
                }
            }.start()
        }

        fun queryillegal(carID: String, cb: queryCallback) {
            data.queryillegal(carID, object : queryCallback {
                override fun retQueryResult(isWho: Int, queryResult: illegalQuery?) {
                    cb.retQueryResult(WHO_QUERY_RESULT, queryResult)
                }
            })
        }
    }


    //*****************************
    //其他接口类
    //*****************************

    interface queryCallback {
        fun retQueryResult(whois: Int, queryResult: illegalQuery?)
    }

    interface loginCallback {
        /**
         * state : 1 = 登录成功
         *         2 = 登录失败
         */
        fun login_Result(state: Int)
    }
}