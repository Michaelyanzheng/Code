package com.zheng.notetest;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zheng.notetest.db.NoteDB;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 2015/7/25.
 */
public class AtyEditNote extends ListActivity {

    private int noteId = -1;

    private static final String TAG = "AtyEditNote";

    public static final String EXTRA_NOTE_ID = "noteId";
    public static final String EXTRA_NOTE_NAME = "nodeName";
    public static final String EXTRA_NOTE_CONTENT = "nodeContent";

    public static final int REQUEST_CODE_GET_PHOTE = 1;
    public static final int REQUEST_CODE_GET_VIDEO = 2;

    private EditText etName;
    private EditText etContent;

    private MediaAdapter mMediaAdapter;

    private String currentPath = null;

    private NoteDB mNoteDB;
    private SQLiteDatabase mReadDB;
    private SQLiteDatabase mWriteDB;

    private View.OnClickListener onclickListenerHander = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent;
            File file;

            switch (v.getId()){

                case R.id.btnPicture:

                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    file = new File(getMediaDir(),System.currentTimeMillis() + ".jpg");

                    if (!file.exists()){
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    currentPath = file.getAbsolutePath();

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent,REQUEST_CODE_GET_PHOTE);
                    break;

                case R.id.btnVideo:

                    intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    file = new File(getMediaDir(),System.currentTimeMillis() + ".mp4");

                    if (!file.exists()){
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    currentPath = file.getAbsolutePath();
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                    startActivityForResult(intent,REQUEST_CODE_GET_VIDEO);

                    break;

                case R.id.btnSave:

                    saveMedia(saveNote());
                    setResult(RESULT_OK);
                    finish();

                    break;

                case R.id.btnCancel:

                    setResult(RESULT_CANCELED);
                    finish();

                    break;

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);

        mNoteDB = new NoteDB(this);
        mReadDB = mNoteDB.getReadableDatabase();
        mWriteDB = mNoteDB.getWritableDatabase();

        etName = (EditText) findViewById(R.id.etName);
        etContent = (EditText) findViewById(R.id.etContent);

        mMediaAdapter = new MediaAdapter(this);
        setListAdapter(mMediaAdapter);

        noteId = getIntent().getIntExtra(EXTRA_NOTE_ID,-1);

        if (noteId > -1){

            etName.setText(getIntent().getStringExtra(EXTRA_NOTE_NAME));
            etContent.setText(getIntent().getStringExtra(EXTRA_NOTE_CONTENT));


            Cursor cursor = mReadDB.query(NoteDB.TABLE_NAME_MEDIA,null,
                    NoteDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID + "=?",new String[]{noteId + ""},null,null,null);

            Log.d(TAG,"------------" + noteId);

            while(cursor.moveToNext()){

                String pathStr = cursor.getString(cursor.getColumnIndex(NoteDB.COLUMN_NAME_MEDIA_PATH));
                int noteId = cursor.getInt(cursor.getColumnIndex(NoteDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID));

                Log.d(TAG,pathStr + "------------" + noteId);

                mMediaAdapter.add(new MediaListCellData(
                        cursor.getString(cursor.getColumnIndex(NoteDB.COLUMN_NAME_MEDIA_PATH)),
                        cursor.getInt(cursor.getColumnIndex(NoteDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID))));
            }

            mMediaAdapter.notifyDataSetChanged();
        }

        findViewById(R.id.btnSave).setOnClickListener(onclickListenerHander);
        findViewById(R.id.btnCancel).setOnClickListener(onclickListenerHander);
        findViewById(R.id.btnPicture).setOnClickListener(onclickListenerHander);
        findViewById(R.id.btnVideo).setOnClickListener(onclickListenerHander);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        MediaListCellData data = mMediaAdapter.getItem(position);

        Intent intent;

        switch (data.type){
            case MediaType.PHOTO:

                intent = new Intent(this,AtyPhotoViewer.class);
                intent.putExtra(AtyPhotoViewer.EXTRA_PATH,data.path);
                startActivity(intent);

                break;

            case MediaType.VIDEO:

                intent = new Intent(this,AtyVideoViewer.class);
                intent.putExtra(AtyVideoViewer.EXTRA_PATH,data.path);
                startActivity(intent);

                break;
        }

        super.onListItemClick(l, v, position, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case REQUEST_CODE_GET_PHOTE:
            case REQUEST_CODE_GET_VIDEO:
                if (resultCode == RESULT_OK){
                    mMediaAdapter.add(new MediaListCellData(currentPath));
                    mMediaAdapter.notifyDataSetChanged();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public File getMediaDir(){

        File dir = new File(Environment.getExternalStorageDirectory(),"NotesMedia");
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }

    public void saveMedia(int notedId){

        MediaListCellData data;

        ContentValues contentValues;

        for (int i = 0; i < mMediaAdapter.getCount(); i++){

            data = mMediaAdapter.getItem(i);

            if (data.id <= -1){

                contentValues = new ContentValues();

                contentValues.put(NoteDB.COLUMN_NAME_MEDIA_PATH,data.path);
                contentValues.put(NoteDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID,notedId);

                mWriteDB.insert(NoteDB.TABLE_NAME_MEDIA,null,contentValues);
            }
        }
    }


    public int saveNote(){

        ContentValues values = new ContentValues();

        values.put(NoteDB.COLUMN_NAME_NOTE_NAME,etName.getText().toString());
        values.put(NoteDB.COLUMN_NAME_NOTE_CONTENT,etContent.getText().toString());

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String mDateString = mSimpleDateFormat.format(new Date());
        values.put(NoteDB.COLUMN_NAME_MEDIA_DATE,mDateString);

        if (noteId > -1){
            mWriteDB.update(NoteDB.TABLE_NAME_NOTES,values,NoteDB.COLUMN_NAME_ID + "=?",new String[]{noteId + ""});
            return noteId;
        }else{
            return (int) mWriteDB.insert(NoteDB.TABLE_NAME_NOTES,null,values);
        }

    }

    @Override
    protected void onDestroy() {
        mReadDB.close();
        mWriteDB.close();
        super.onDestroy();
    }

    static class MediaAdapter extends BaseAdapter{

        private Context mContext;
        private List<MediaListCellData> mList = new ArrayList<MediaListCellData>();

        public MediaAdapter(Context context){
            this.mContext = context;
        }

        public void add(MediaListCellData data){
            mList.add(data);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public MediaListCellData getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.media_list_cell,null);
            }

            MediaListCellData data = mList.get(position);

            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            TextView tvPath = (TextView) convertView.findViewById(R.id.tvPath);

            ivIcon.setImageResource(data.iconId);
            tvPath.setText(data.path);

            return convertView;
        }
    }

    static class MediaListCellData{

        public MediaListCellData(String path){

            this.path = path;
            if (path.endsWith(".jpg")){
                iconId = R.drawable.mao;
                type = MediaType.PHOTO;
            }else{
                iconId = R.drawable.mei;
                type = MediaType.VIDEO;
            }
        }

        public MediaListCellData(String path,int id){
            this(path);
            this.id = id;
        }

        int type = 0;
        int id = -1;
        String path = "";
        int iconId = R.drawable.pu;
    }

    static class MediaType{
        static final int PHOTO = 1;
        static final int VIDEO = 2;
    }
}
