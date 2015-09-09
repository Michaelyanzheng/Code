package com.zheng.notetest;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Michael on 2015/7/25.
 */
public class AtyPhotoViewer extends Activity {

    public static final String EXTRA_PATH = "path";

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageView = new ImageView(this);
        setContentView(mImageView);


        String path = getIntent().getStringExtra(EXTRA_PATH);
        if (path != null){
            mImageView.setImageURI(Uri.fromFile(new File(path)));
        }else{
            finish();
        }
    }


}
