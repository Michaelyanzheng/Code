package com.zheng.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 2015/7/18.
 */
public class DB extends SQLiteOpenHelper{

    public static final String DB_NAME = "db.db";

    public static final String DB_TABLE = "user";

    public DB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE + "(" +
                "_id integer primary key autoincrement," +
                "name text," +
                "sex text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
