package com.zheng.notetest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 2015/7/25.
 */
public class NoteDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "notes";

    public static final String TABLE_NAME_NOTES = "notes";
    public static final String TABLE_NAME_MEDIA = "media";

    public static final String COLUMN_NAME_ID = "_id";

    public static final String COLUMN_NAME_NOTE_NAME = "name";
    public static final String COLUMN_NAME_NOTE_CONTENT = "content";
    public static final String COLUMN_NAME_MEDIA_DATE = "date";

    public static final String COLUMN_NAME_MEDIA_PATH = "path";
    public static final String COLUMN_NAME_MEDIA_OWNER_NOTE_ID = "note_id";

    public NoteDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME_NOTES + "(" +
                COLUMN_NAME_ID + " integer primary key autoincrement, " +
                COLUMN_NAME_NOTE_NAME + " text not null default \"\"," +
                COLUMN_NAME_NOTE_CONTENT + " text not null default \"\"," +
                COLUMN_NAME_MEDIA_DATE + " text not null default \"\"" +
                ")");

        db.execSQL("create table " + TABLE_NAME_MEDIA + "(" +
                COLUMN_NAME_ID + " integer primary key autoincrement, " +
                COLUMN_NAME_MEDIA_PATH + " text not null default \"\"," +
                COLUMN_NAME_MEDIA_OWNER_NOTE_ID + " integer not null default 0" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
