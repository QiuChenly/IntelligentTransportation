package com.example.liunianyishi.intelligenttransportation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuchen on 2018/1/31.
 */

public class illegalQuery {
    public String carID;
    public ArrayList<result> allList;

    public class result {
        //    罚金      扣分        处理状态
        int Forfeit, DeductInt, handleState;
        //     发生时间   发生位置    原因
        String fooTime, Location, Cause;
    }
}
