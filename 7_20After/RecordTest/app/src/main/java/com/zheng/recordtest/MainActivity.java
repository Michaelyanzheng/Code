package com.zheng.recordtest;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mStartRecord_button;
    private Button mStopRecord_button;

    private MediaRecorder mMediaRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartRecord_button = (Button) findViewById(R.id.startRecord_Button);
        mStopRecord_button = (Button) findViewById(R.id.stopRecord_Button);

        mStartRecord_button.setOnClickListener(this);
        mStopRecord_button.setOnClickListener(this);
    }

    private void startRecord(){


        if (mMediaRecorder == null){

            File mDir = null;
            File mSave = null;

            mDir = new File(Environment.getExternalStorageDirectory(),"records");
            mDir.mkdirs();

            mSave = new File(mDir,System.currentTimeMillis()+".amr");
            try {
                mSave.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mMediaRecorder = new MediaRecorder();
            try {
                mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mMediaRecorder.setOutputFormat(MediaRecorder.AudioEncoder.AMR_WB);
                mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
                mMediaRecorder.setOutputFile(mSave.getAbsolutePath());
                mMediaRecorder.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mMediaRecorder.start();
        }
    }

    private void stopRecord(){

        if (mMediaRecorder != null){

            mMediaRecorder.stop();
            mMediaRecorder.release();

            mMediaRecorder = null;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startRecord_Button:

                startRecord();

                break;

            case R.id.stopRecord_Button:

                stopRecord();

                break;
        }
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
