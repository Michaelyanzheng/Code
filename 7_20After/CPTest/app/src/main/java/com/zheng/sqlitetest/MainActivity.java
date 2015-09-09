package com.zheng.sqlitetest;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    private static final String NAME = "name";
    private static final String SEX = "sex";

    private static final String TAG = "MainActivity";

    private DB db;
    private SQLiteDatabase readDb;
    private SQLiteDatabase writerDb;

    private EditText etName;
    private EditText etSex;

    private Button btnSave;

    private SimpleCursorAdapter simpleCursorAdapter;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = new DB(this);
        readDb = db.getReadableDatabase();
        writerDb = db.getWritableDatabase();

        etName = (EditText) findViewById(R.id.etName);
        etSex = (EditText) findViewById(R.id.etSex);
        btnSave = (Button) findViewById(R.id.btnSave);

        etName.setFocusable(true);
        etName.setFocusableInTouchMode(true);
        etName.requestFocus();

        refreshListView();

        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.user_list_item,cursor,
                new String[]{"name","sex"},new int[]{R.id.tvName,R.id.tvSex});

        setListAdapter(simpleCursorAdapter);


        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String sex = etSex.getText().toString();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex)) {

                    ContentValues values = new ContentValues();
                    values.put("name", name);
                    values.put("sex", sex);
//                    writerDb.insert("user", null, values);
                    getContentResolver().insert(UsersCP.uri, values);
                    values.clear();

                    etName.setText("");
                    etSex.setText("");

                    etName.setFocusable(true);
                    etName.setFocusableInTouchMode(true);
                    etName.requestFocus();
                }else{
                    Toast.makeText(MainActivity.this,"信息请填全",Toast.LENGTH_SHORT).show();
                }


                refreshListView();
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                new AlertDialog.Builder(MainActivity.this).setTitle("提示").setMessage("是否删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Cursor cursor = simpleCursorAdapter.getCursor();
                                cursor.moveToPosition(position);

                                int _id = cursor.getInt(cursor.getColumnIndex("_id"));

//                                writerDb.delete("user","_id=?",new String[]{_id+""});

                                getContentResolver().delete(UsersCP.uri,"_id=?",new String[]{_id+""});
                                refreshListView();

                            }
                        }).setNegativeButton("取消",null).show();


                return true;

            }
        });


//        DB db = new DB(this);
//
//        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(NAME,"zheng");
//        contentValues.put(SEX,"boy");
//        sqLiteDatabase.insert(DB.DB_TABLE, null, contentValues);
//
//        contentValues.clear();
//
//        contentValues.put(NAME, "michael");
//        contentValues.put(SEX, "man");
//        sqLiteDatabase.insert(DB.DB_TABLE,null,contentValues);
//
//        contentValues.clear();
//        sqLiteDatabase.close();


//        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
//
//        Cursor cursor = sqLiteDatabase.query(DB.DB_TABLE, null, null, null, null, null, null);
//
//        while (cursor.moveToNext()){
//
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String sex = cursor.getString(cursor.getColumnIndex("sex"));
//
//            Log.d(TAG, name + "---" + sex);
//        }
//        sqLiteDatabase.close();
    }

    private void refreshListView(){

//        cursor = readDb.query("user",null,null,null,null,null,null);
        simpleCursorAdapter.changeCursor(getContentResolver().query(UsersCP.uri,null,null,null,null,null));


    }
}
