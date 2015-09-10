package com.zheng.convenience;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by michael on 2015/9/10.
 */
public class StartActivity extends Activity {


    public static final String COOL = "cool";

    public static final String ME_OR_COLOR = "me_or_color";

    public static final int TIMEOUT = 2000;

    public Handler mMyHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case 1:

                    startActivity(new Intent(StartActivity.this,MainActivity.class));
                    finish();

                    break;

                default:

                    super.handleMessage(msg);

                    break;
            }
        }
    };

    private boolean meOrColor;

    private ImageView mBackImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBackImageView = new ImageView(this);

        SharedPreferences sharedPreferences = getSharedPreferences(COOL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        meOrColor = sharedPreferences.getBoolean(ME_OR_COLOR,false);

        if (meOrColor){

            mBackImageView.setImageResource(R.drawable.black);
            editor.putBoolean(ME_OR_COLOR,false);

        }else{

            mBackImageView.setImageResource(R.drawable.cool);
            editor.putBoolean(ME_OR_COLOR,true);
        }

        editor.commit();


        setContentView(mBackImageView);


        Message message = new Message();
        message.what = 1;

        mMyHandler.sendMessageDelayed(message,TIMEOUT);
    }
}
