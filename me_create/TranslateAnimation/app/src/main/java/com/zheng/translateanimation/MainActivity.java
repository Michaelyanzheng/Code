package com.zheng.translateanimation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;


public class MainActivity extends ActionBarActivity {

    private TranslateAnimation mTranslateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTranslateAnimation = new TranslateAnimation(0,200,0,200);
        mTranslateAnimation.setDuration(1000);

        findViewById(R.id.translateAnimation_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                view.startAnimation(mTranslateAnimation);

                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.translateanimation));
            }
        });
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
