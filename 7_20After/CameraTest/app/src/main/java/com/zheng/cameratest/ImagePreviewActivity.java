package com.zheng.cameratest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Michael on 2015/7/22.
 */
public class ImagePreviewActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mImageView = new ImageView(this);

        setContentView(mImageView);

        String path = getIntent().getStringExtra("path");
        mImageView.setImageURI(Uri.parse(path));

    }
}
