package com.example.liunianyishi.intelligenttransportation.Utils;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

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
    }

    public static mDatabaseHelper DBHelper;

    public static Context getContext() {
        return context;
    }


}
