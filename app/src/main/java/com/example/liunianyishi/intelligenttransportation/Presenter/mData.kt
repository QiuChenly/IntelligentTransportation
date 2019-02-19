package com.example.liunianyishi.intelligenttransportation.Presenter

import com.example.liunianyishi.intelligenttransportation.Bean.illegalQueryBean
import com.example.liunianyishi.intelligenttransportation.Interface.BaseImpl
import com.google.gson.Gson

/**
 * Created by qiuchen on 2018/1/31.
 */
class mData : BaseImpl {
    val queryRet = "{\n" +
            "    \"carID\": \"A10001\",\n" +
            "    \"allList\": [\n" +
            "        {\n" +
            "            \"Forfeit\": 200,\n" +
            "            \"DeductInt\": 5,\n" +
            "            \"fooTime\": 1496262451234,\n" +
            "            \"Location\": \"北京秋名山飙车大道\",\n" +
            "            \"Cause\": \"这个人开宝马装b,不扣富二代的扣谁的?\",\n" +
            "            \"handleState\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"Forfeit\": 100,\n" +
            "            \"DeductInt\": 2,\n" +
            "            \"fooTime\": 1496262452234,\n" +
            "            \"Location\": \"北京秋名山飙车大道\",\n" +
            "            \"Cause\": \"这个人开宝马装b,不扣富二代的扣谁的?\",\n" +
            "            \"handleState\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"Forfeit\": 200,\n" +
            "            \"DeductInt\": 5,\n" +
            "            \"fooTime\": 1496262453234,\n" +
            "            \"Location\": \"北京秋名山飙车大道\",\n" +
            "            \"Cause\": \"这个人开宝马装b,不扣富二代的扣谁的?\",\n" +
            "            \"handleState\": 1\n" +
            "        },\n" +
            "        {\n" +
            "            \"Forfeit\": 900,\n" +
            "            \"DeductInt\": 100,\n" +
            "            \"fooTime\": 1496262454234,\n" +
            "            \"Location\": \"北京秋名山飙车大道\",\n" +
            "            \"Cause\": \"这个人开宝马装b,不扣富二代的扣谁的?\",\n" +
            "            \"handleState\": 2\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    override fun login(u: String, p: String, cb: MainPresenter.loginCallback) {
        cb.login_Result(1)
    }

    override fun queryillegal(carID: String, cb: MainPresenter.queryCallback) {
        val s = Gson().fromJson<illegalQueryBean>(queryRet, illegalQueryBean::class.java)
        return if (carID == s.carID)
            cb.retQueryResult(MainPresenter.WHO_QUERY_RESULT, s)
        else
            cb.retQueryResult(MainPresenter.WHO_QUERY_RESULT, illegalQueryBean())
    }
}