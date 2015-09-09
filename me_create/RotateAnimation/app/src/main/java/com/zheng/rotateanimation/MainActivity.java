package com.zheng.rotateanimation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;


public class MainActivity extends ActionBarActivity {

    private RotateAnimation mRotateAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRotateAnimation = new RotateAnimation(0,360,100,50);
        mRotateAnimation = new RotateAnimation(0,-360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        mRotateAnimation.setDuration(1000);

        findViewById(R.id.rotateAnimation_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                view.startAnimation(mRotateAnimation);

                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotateanimation));
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
