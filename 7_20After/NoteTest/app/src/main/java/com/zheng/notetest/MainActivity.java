package com.zheng.notetest;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.zheng.notetest.db.NoteDB;


public class MainActivity extends ListActivity {

    private NoteDB mNoteDB;

    private SQLiteDatabase mReadDB;

    private SimpleCursorAdapter mSimpleCursorAdapter;

    private Button mAddButton;

    private static final int REQUEST_CODE_ADD_NOTE = 1;
    private static final int REQUEST_CODE_EDIT_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteDB = new NoteDB(this);

        mReadDB = mNoteDB.getReadableDatabase();

        mSimpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.notes_list_cell,null,
                new String[]{NoteDB.COLUMN_NAME_NOTE_NAME,NoteDB.COLUMN_NAME_MEDIA_DATE},
                new int[]{R.id.tvName,R.id.tvDate});

        setListAdapter(mSimpleCursorAdapter);
        refreshNotesListView();

        mAddButton = (Button) findViewById(R.id.addNote_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AtyEditNote.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Cursor cursor = mSimpleCursorAdapter.getCursor();
        cursor.moveToPosition(position);

        Intent intent = new Intent(MainActivity.this,AtyEditNote.class);

        intent.putExtra(AtyEditNote.EXTRA_NOTE_ID,cursor.getInt(cursor.getColumnIndex(NoteDB.COLUMN_NAME_ID)));
        intent.putExtra(AtyEditNote.EXTRA_NOTE_NAME,cursor.getString(cursor.getColumnIndex(NoteDB.COLUMN_NAME_NOTE_NAME)));
        intent.putExtra(AtyEditNote.EXTRA_NOTE_CONTENT,cursor.getString(cursor.getColumnIndex(NoteDB.COLUMN_NAME_NOTE_CONTENT)));

        startActivityForResult(intent,REQUEST_CODE_EDIT_NOTE);

        super.onListItemClick(l, v, position, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQUEST_CODE_ADD_NOTE:
            case REQUEST_CODE_EDIT_NOTE:

                if (resultCode == Activity.RESULT_OK){
                    refreshNotesListView();
                }
                break;

            default:

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refreshNotesListView(){
        mSimpleCursorAdapter.changeCursor(mReadDB.query(NoteDB.TABLE_NAME_NOTES,null,null,null,null,null,null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
