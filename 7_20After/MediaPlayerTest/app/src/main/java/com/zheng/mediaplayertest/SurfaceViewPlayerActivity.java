package com.zheng.mediaplayertest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

import java.io.IOException;


public class SurfaceViewPlayerActivity extends Activity {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mSurfaceView = new SurfaceView(this);
        mSurfaceHolder = mSurfaceView.getHolder();

        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {

            private MediaPlayer mMediaPlayer = null;
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                mMediaPlayer = MediaPlayer.create(SurfaceViewPlayerActivity.this,R.raw.movie);
                try {
                    mMediaPlayer.prepare();
                } catch(IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mMediaPlayer.setDisplay(holder);
                mMediaPlayer.start();


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                if (mMediaPlayer != null){
                    mMediaPlayer.release();
                }
            }
        });

        setContentView(mSurfaceView);
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
