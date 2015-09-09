package com.zheng.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by michael on 2015/8/27.
 */
public class AudioPlayer {

    private MediaPlayer mMediaPlayer;

    public void stop(){

        if (mMediaPlayer != null){

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play(Context context){

        if (mMediaPlayer == null){

            stop();

            mMediaPlayer = MediaPlayer.create(context,R.raw.one_small_step);

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stop();
                }
            });

            mMediaPlayer.start();
        }
    }
}
