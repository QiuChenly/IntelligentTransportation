package com.example.liunianyishi.intelligenttransportation.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.liunianyishi.intelligenttransportation.Utils.mSharedContext;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4 0004.
 */

public class mDB extends SQLiteOpenHelper {
    private mDB mDB;
    private final String CREATE_TABLES = "create table if not " +
            "exists User(_id INTEGER PRIMARY KEY AUTOINCREMENT " +
            "NOT NULL,name TEXT not null,phone TEXT not null,pass TEXT not null);";
    private final static int Version = 1;
    private final static String DataBaseName = "mDataBase";
    public mDB() {
        super(mSharedContext.getContext(), DataBaseName,null, Version);
        mDB = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLES);
    }

    String userManageName = "userManage";
    String rechargeHistoryName = "rechargeHistory";
    public void CreateUserManage(){
        String exc ="create table if not exists " + userManageName + " (" +
                "_index integer primary key autoincrement,carImage integer not null,carNo TEXT not null," +
                "carMaster TEXT not null,carMoney integer not null" +
                ");";
        mDB.getWritableDatabase().execSQL(exc);
    }
    public void CreateRechargeHistory(){
        String exc = "create table if not exists "+rechargeHistoryName+" ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "carNo TEXT NOT NULL,carMaster TEXT NOT NULL,carMoney INTEGER NOT NULL);";
        mDB.getWritableDatabase().execSQL(exc);
    }

    public void InsertUserManage(int imageId,String carNo,String carMaster,int carMoney){
        String exc = "insert into "+userManageName+" (carImage,carNo,carMaster,carMoney)"+
                " values ("+imageId+",'"+carNo+"','"+carMaster+"',"+carMoney+");";
        mDB.getWritableDatabase().execSQL(exc);
    }
    public void UpdateMoney(String carNo,int reChargeMoney){
        String exc = "update "+userManageName+" set carMoney = "+reChargeMoney+
                " where carNo = '"+carNo+"';";
        mDB.getWritableDatabase().execSQL(exc);
    }
    public int mGetMoneyByCarID(String carNo) {
        String exc = "select carMoney from "
                + userManageName + " where carNo = '" + carNo + "';";
        Cursor c = this.getReadableDatabase().rawQuery(exc, null);
        if (c.moveToFirst()) {
            int a = c.getInt(0);
            c.close();
            return a;
        }
        c.close();
        return 0;
    }

    public void InsertRechargeHistory(String carNo,String carMaster,int carMoney){
        String exc = "insert into "+rechargeHistoryName+" (carNo,carMaster,carMoney)"+
                " values ("+"'"+carNo+"','"+carMaster+"',"+carMoney+");";
        mDB.getWritableDatabase().execSQL(exc);
    }
    public List<UserInfo> SearchUserManage(){
            List<UserInfo> userInfo = new ArrayList<>();
            String exc = "select * from "+userManageName;
            Cursor cursor = this.getReadableDatabase().rawQuery(exc,null);
            if (cursor.moveToFirst()){
                int id = 1;
                for (;;cursor.moveToNext()){
                    UserInfo user = new UserInfo();
                    user.index = id;
                    id++;
                    user.imageId = cursor.getInt(1);
                    user.carNo = cursor.getString(2);
                    user.carMaster = cursor.getString(3);
                    user.carMoney = cursor.getInt(4);
                    userInfo.add(user);
                    if (cursor.isLast()){
                        break;
                    }
                }
            }
            cursor.close();
            return userInfo;
    }
    public List<RechargeHistory> SearchRechargeHistory(){
        List<RechargeHistory> rechargeHistory = new ArrayList<>();
        String exc = "select * from "+rechargeHistoryName;
        Cursor cursor = this.getReadableDatabase().rawQuery(exc,null);
        if (cursor.moveToFirst()){
            int id = 1;
            for (;;cursor.moveToNext()){
                RechargeHistory history = new RechargeHistory();
                history.id = id;
                id++;
                history.carNo = cursor.getString(1);
                history.carMaster = cursor.getString(2);
                history.carMoney = cursor.getInt(3);
                rechargeHistory.add(history);
                if (cursor.isLast()){
                    break;
                }
            }
        }
        cursor.close();
        return rechargeHistory;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
