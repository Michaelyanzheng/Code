package com.zheng.viewpagetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;

/**
 * Created by Michael on 2015/8/4.
 */
public class WelcomeAty extends Activity {

    private boolean ifFirstIn = false;

    private static final String IFFIRSTIN = "ifFirstIn";

    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private android.os.Handler mHandler = new android.os.Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;

                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        init();
    }

    private void init(){

        SharedPreferences sharedPreferences = getSharedPreferences("zheng", Context.MODE_PRIVATE);

        ifFirstIn = sharedPreferences.getBoolean(IFFIRSTIN,true);
        if (ifFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IFFIRSTIN,false);
            editor.commit();
        }else{
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);

        }
    }

    private void goHome(){
        Intent intent = new Intent(WelcomeAty.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide(){
        Intent intent = new Intent(WelcomeAty.this,Guide.class);
        startActivity(intent);
        finish();
    }
}
