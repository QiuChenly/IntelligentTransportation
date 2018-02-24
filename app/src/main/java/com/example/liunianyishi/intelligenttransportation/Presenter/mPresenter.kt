package com.example.liunianyishi.intelligenttransportation.Presenter

import com.example.liunianyishi.intelligenttransportation.Bean.illegalCarListBean
import com.example.liunianyishi.intelligenttransportation.Bean.illegalQueryBean
import com.example.liunianyishi.intelligenttransportation.Utils.mSharedContext
import java.text.SimpleDateFormat
import java.util.*

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
                override fun retQueryResult(isWho: Int, queryBeanResult: illegalQueryBean?) {
                    cb.retQueryResult(WHO_QUERY_RESULT, queryBeanResult)
                }
            })
        }

        /**
         * 将时间戳转换为文本
         */
        fun TimeTick2String(string: String): String {
            return SimpleDateFormat("yyy-dd-mm hh:mm:ss").format(Date(string.toLong()))
        }

        /**
         * 保存查询历史
         */
        fun SaveQueryHistory(s: illegalCarListBean) {
            mSharedContext.DBHelper.saveQueryInfo(s)
        }

        /**
         * 获取所有查询历史
         */
        fun GetQueryHistory(): ArrayList<illegalCarListBean> {
            return mSharedContext.DBHelper.getAllQueryHistory()
        }

        fun DeleteQueryHistory(str:String){
            mSharedContext.DBHelper.deleteQueryInfo(str)
        }
    }


    //*****************************
    //其他接口类
    //*****************************

    interface queryCallback {
        fun retQueryResult(whois: Int, queryBeanResult: illegalQueryBean?)
    }

    interface loginCallback {
        /**
         * state : 1 = 登录成功
         *         2 = 登录失败
         */
        fun login_Result(state: Int)
    }
}