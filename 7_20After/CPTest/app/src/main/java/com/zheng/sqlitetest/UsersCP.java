package com.zheng.sqlitetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Michael on 2015/7/21.
 */
public class UsersCP extends ContentProvider{

    private DB db;

    private SQLiteDatabase readDB;
    private SQLiteDatabase writerDB;

    public static final String TABLE_NAME = "user";
    public static final Uri uri = Uri.parse("content://com.zheng.sqlitetest.userscp");

    @Override
    public boolean onCreate() {

        db = new DB(getContext());
        readDB = db.getReadableDatabase();
        writerDB = db.getWritableDatabase();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return readDB.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        writerDB.insert(TABLE_NAME,null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return writerDB.delete(TABLE_NAME,selection,selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return writerDB.update(TABLE_NAME,values,selection,selectionArgs);
    }
}
