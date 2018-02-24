package com.example.liunianyishi.intelligenttransportation.Bean;

/**
 * Created by qiuchen on 2018/2/1.
 * 违章车辆简要概览Bean类
 */

public class illegalCarListBean {
    //             车牌     短车牌,用来数据库查询
    public String carID, shortCarID;
    //               未处理计数    扣分          罚款
    public int noHandleCount, deductCount, forfeitCount;
}
