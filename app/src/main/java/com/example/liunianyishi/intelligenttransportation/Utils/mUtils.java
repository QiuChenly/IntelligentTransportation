package com.example.liunianyishi.intelligenttransportation.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/3 0003.
 */

public class mUtils {
    static SharedPreferences share = null;
    public static SharedPreferences getShare(){
        if (share == null){
            share = mSharedContext.getContext()
                    .getSharedPreferences("threshold", Context.MODE_PRIVATE);
        }
        return share;
    }


    public static void put(String key, int value) {
        SharedPreferences.Editor edit = share.edit();
        edit.putInt(key, value);
        edit.apply();
        edit.commit();
    }

    public static int getInt(String key){
        return share.getInt(key,-1);
    }
}
