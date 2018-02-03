package com.example.liunianyishi.intelligenttransportation.Util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by qiuchen on 2018/1/31.
 */
class mDatabaseHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    val TABLE_QUERYHISTORY="TB_QUERYILLEGAL"
    val TABLE_QUERYHISTORY_DETAILS="TB_QUERYILLEGAL_DETAILS"

    fun initAllTable(){
        var crt_Table = "create table if not exists $TABLE_QUERYHISTORY(" +
                "id integer primary key autoincrement," +
                "nohandleCount integer not null," +
                "deduct integer,forfeit integer);"
        this.writableDatabase.execSQL(crt_Table)
        crt_Table = "create table if not exists $TABLE_QUERYHISTORY_DETAILS(" +
                "id integer primary key autoincrement," +
                "nohandleCount integer not null," +
                "deduct integer,forfeit integer);"
        this.writableDatabase.execSQL(crt_Table)
    }
}