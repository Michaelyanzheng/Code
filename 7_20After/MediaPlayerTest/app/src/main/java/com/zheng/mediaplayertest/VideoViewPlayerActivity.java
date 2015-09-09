package com.zheng.mediaplayertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Michael on 2015/7/21.
 */
public class VideoViewPlayerActivity extends Activity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVideoView = new VideoView(this);
        setContentView(mVideoView);

        mVideoView.setVideoPath("/storage/sdcard1/movie.mp4");
        mVideoView.setMediaController(new MediaController(this));

    }
}
