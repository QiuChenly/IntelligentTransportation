package com.example.liunianyishi.intelligenttransportation.Bean;

import java.util.ArrayList;

/**
 * Created by qiuchen on 2018/1/31.
 * 罚款详细数据Bean类
 */

public class illegalQueryBean {
    public String carID;
    public ArrayList<result> allList;

    public class result {
        /**
         * 罚金
         */
        public int Forfeit,
        /**
         * 扣分
         */
        DeductInt,
        /**
         * 处理状态 =1 未处理  =2 已处理
         */
        handleState;

        /**
         * 发生时间
         */
        public String fooTime,
        /**
         * 发生位置
         */
        Location,
        /**
         * 原因
         */
        Cause;
    }
}
