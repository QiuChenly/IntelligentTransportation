package com.example.liunianyishi.intelligenttransportation.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.liunianyishi.intelligenttransportation.Bean.illegalCarListBean

/**
 * Created by qiuchen on 2018/1/31.
 */
class mDatabaseHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    //违章查询结果车辆概览数据库
    val TABLE_QUERYHISTORY = "TB_QUERYILLEGAL"
    //违章查询结果缓存数据库
    val TABLE_QUERYHISTORY_DETAILS = "TB_QUERYILLEGAL_DETAILS_CACHE"

    fun initAllTable() {
        var crt_Table = "create table if not exists $TABLE_QUERYHISTORY(" +
                "id integer primary key autoincrement," +
                "carID TEXT not null," +
                "carIDShort TEXT not null," +
                "nohandleCount integer not null," +
                "deduct integer not null," +
                "forfeit integer not null);"
        this.writableDatabase.execSQL(crt_Table)

        //车辆违章查询详情数据库
        //缓存数据库
        //字段:
        //      id 仅编号序号
        //      carID 车辆车牌号 如 A10001
        //      fooTime 发生时间
        //      Location 发生位置
        //      Cause   发生原因
        //deduct 扣分
        //forfeit 罚款
        crt_Table = "create table if not exists $TABLE_QUERYHISTORY_DETAILS(" +
                "id integer primary key autoincrement," +
                "carID TEXT not null," +
                "fooTime TEXT, " +
                "Location TEXT, " +
                "Cause TEXT," +
                "deduct integer," +
                "forfeit integer);"
        this.writableDatabase.execSQL(crt_Table)
    }

    fun getAllQueryHistory(): ArrayList<illegalCarListBean> {
        val exec = "select * from $TABLE_QUERYHISTORY"
        val c = readableDatabase.rawQuery(exec, null)
        val bean: ArrayList<illegalCarListBean> = ArrayList()
        if (c.moveToFirst()) {
            do {
                bean.add(illegalCarListBean().apply {
                    this.carID = c.getString(1)
                    this.shortCarID = c.getString(2)
                    this.noHandleCount = c.getInt(3)
                    this.deductCount = c.getInt(4)
                    this.forfeitCount = c.getInt(5)
                })
                if (c.isLast) {
                    break
                }
            } while (c.moveToNext())
        }
        c.close()
        return bean
    }

    fun saveQueryInfo(s: illegalCarListBean) {
        with(this.writableDatabase) {
            val exec = "insert into $TABLE_QUERYHISTORY (carID,carIDShort,nohandleCount,deduct,forfeit)values(" +
                    "'${s.carID}'," +
                    "'${s.shortCarID}'," +
                    "${s.noHandleCount}," +
                    "${s.deductCount}," +
                    "${s.forfeitCount});"
            execSQL(exec)
        }
    }

    /**
     * 删除历史记录 by车牌号
     */
    fun deleteQueryInfo(string: String) {
        val exec = "delete from $TABLE_QUERYHISTORY where carID = '$string';"
        writableDatabase.execSQL(exec)
    }
}