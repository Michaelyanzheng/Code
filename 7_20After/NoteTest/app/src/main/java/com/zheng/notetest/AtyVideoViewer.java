package com.zheng.notetest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by Michael on 2015/7/25.
 */
public class AtyVideoViewer extends Activity {

    public static final String EXTRA_PATH = "path";

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mVideoView = new VideoView(this);
        mVideoView.setMediaController(new MediaController(this));
        setContentView(mVideoView);

        String path = getIntent().getStringExtra(EXTRA_PATH);
        if (path != null){
            mVideoView.setVideoURI(Uri.fromFile(new File(path)));
        }else{
            finish();
        }
    }
}
