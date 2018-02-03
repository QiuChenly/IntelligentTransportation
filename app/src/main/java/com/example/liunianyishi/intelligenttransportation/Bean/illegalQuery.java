package com.example.liunianyishi.intelligenttransportation.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuchen on 2018/1/31.
 * 罚款详细数据Bean类
 */

public class illegalQuery {
    public String carID;
    public ArrayList<result> allList;

    public class result {
        //            罚金      扣分       处理状态 =1 未处理  =2 已处理
        public int Forfeit, DeductInt, handleState;
        //     发生时间   发生位置    原因
        public String fooTime, Location, Cause;
    }
}
