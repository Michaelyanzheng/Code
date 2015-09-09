package com.zheng.cameratest;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera = null;

    private Button mSavePicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSavePicButton = (Button) findViewById(R.id.savePic_button);

        mSurfaceView = (SurfaceView) findViewById(R.id.cameraPre_SurfaceView);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                startPreview();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

                stopPreview();
            }
        });

        mSavePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCamera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {

                        String path;
                        if ((path = savePic(data)) != null){

                            Intent intent = new Intent(MainActivity.this,ImagePreviewActivity.class);
                            intent.putExtra("path",path);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"保存图片失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     *
     * @return AbsolutePath
     */
    private String savePic(byte[] data){

        try {
            File file = File.createTempFile("temp","img");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);

            fileOutputStream.flush();
            fileOutputStream.close();

            return file.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void startPreview(){
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPreview(){
        mCamera.stopPreview();
        mCamera.release();
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
