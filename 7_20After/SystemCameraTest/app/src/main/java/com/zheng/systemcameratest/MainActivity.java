package com.zheng.systemcameratest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class MainActivity extends Activity {

    private Button mGetSystemCamera_button;
    private ImageView mImageView;

    private static final int REQUESTCODE_CAMERA_PRITURE = 1;
    private File mCurrentImageFile = null;

    private static final String SAVE_LAND = "save_land";

    private String mImageFilePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){

            mImageFilePath = savedInstanceState.getString(SAVE_LAND,"");
        }


        mImageView = (ImageView) findViewById(R.id.camera_image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startCamera();
            }
        });

        if (mImageFilePath != ""){

            mImageView.setImageURI(Uri.parse(mImageFilePath));
        }

        mGetSystemCamera_button = (Button) findViewById(R.id.getCameraPriture_Button);
        mGetSystemCamera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startCamera();
            }
        });
    }

    private void startCamera(){
        File dir = new File(Environment.getExternalStorageDirectory(),"prictures");
        if (!dir.exists()){
            dir.mkdirs();
        }
        mCurrentImageFile = new File(dir,System.currentTimeMillis()+".jpg");
        if (!mCurrentImageFile.exists()){
            try {
                mCurrentImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentImageFile));

        startActivityForResult(intent, REQUESTCODE_CAMERA_PRITURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case REQUESTCODE_CAMERA_PRITURE:
                if (resultCode == RESULT_OK){

                    mImageView.setImageURI(Uri.fromFile(mCurrentImageFile));
                }


                break;

            default:
                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(SAVE_LAND,mCurrentImageFile.getAbsolutePath());
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
