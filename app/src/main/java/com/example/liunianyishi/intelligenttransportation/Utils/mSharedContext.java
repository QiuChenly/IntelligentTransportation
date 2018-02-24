package com.example.liunianyishi.intelligenttransportation.Utils;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.example.liunianyishi.intelligenttransportation.DataBase.mDB;

/**
 * Created by LiuNianyishi on 2018/1/24.
 */

public class mSharedContext extends Application {
    public static Context context;
    public static String SDCardFile = "";
    public static int threshold = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mSP.Companion.initSP(this);
        //得到SD卡路径
        SDCardFile = Environment.getExternalStorageDirectory() + "/Transportation";
        DBHelper = new mDatabaseHelper(this.getApplicationContext(),"LYDB",null,1);
        DBHelper.initAllTable();//resolve recursively called

        //2018.02.24 QiuChenly
        //fix 用户管理闪退BUG
        JDBHelper = new mDB();
        //初始化数据库表
        JDBHelper.CreateRechargeHistory();
        JDBHelper.CreateUserManage();
    }

    public static mDatabaseHelper DBHelper;
    /**
     * Java代码编写的数据库类
     * Author:高薛阳
     */
    public static mDB JDBHelper;

    public static Context getContext() {
        return context;
    }


}
