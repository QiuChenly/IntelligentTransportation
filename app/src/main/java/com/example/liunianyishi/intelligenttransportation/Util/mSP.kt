package com.example.liunianyishi.intelligenttransportation.Util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by QiuChenly on 2018年1月26日 0026.
 * SharedPreference调用方法:
 * #JAVA
 *      mSP.companion.FUNCTION_NAME
 * #Kotlin
 *      mSP.FUNCTION_NAME
 */
class mSP {
    companion object {
        private const val SP_NAME = "transportationSP"
        private const val SP_USER_NAME = "cao_userName"
        private const val SP_USER_PASS = "cao_passWord"
        private lateinit var SP: SharedPreferences
        fun initSP(c: Context) {
            SP = c.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }


        /**
         * 保存用户名密码
         * 调用:mSP.saveUserPass(var1,var2)
         * 函数写法采用内联(inline)规则
         */
        fun saveUserPass(user: String, pass: String) = SP.edit().putString(SP_USER_NAME, user)
                .putString(SP_USER_PASS, pass)
                .apply()

        /**
         * 得到用户名
         * 调用:mSP.getUserName()
         * 函数写法采用内联(inline)规则
         */
        fun getUserName(): String = SP.getString(SP_USER_NAME, "")

        /**
         * 得到密码
         * 调用:mSP.getUserPass()
         * 函数写法采用内联(inline)规则
         */
        fun getUserPass(): String = SP.getString(SP_USER_PASS, "")

        val SP_REMEMBER_PASS = "rmPass"
        fun saveRMPassState(state: Boolean) = SP.edit().putBoolean(SP_REMEMBER_PASS, state).apply()
        fun getRMPassState() = SP.getBoolean(SP_REMEMBER_PASS, false)
    }
}