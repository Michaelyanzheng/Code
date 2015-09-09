package com.zheng.soundpooltest;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private Button mSoundButton;
    private Button mMusicButton;

    private SoundPool mSoundPool;
    private MediaPlayer mMediaPlayer = null;

    private int mSoundId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        mSoundId = mSoundPool.load(this,R.raw.song,1);

        mSoundButton = (Button) findViewById(R.id.sound_button);
        mMusicButton = (Button) findViewById(R.id.music_button);

        mSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSoundPool.play(mSoundId,1,1,1,0,1);
            }
        });

        mMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null){
                    mMediaPlayer.start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        mMediaPlayer = MediaPlayer.create(this,R.raw.fei);
        try {

            mMediaPlayer.prepare();

        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer != null){
            mMediaPlayer.release();
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
