package com.zheng.animationall;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private AnimationSet mAnimationSet;

    private Button mAnimationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnimationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
        mAnimationSet.addAnimation(alphaAnimation);

        TranslateAnimation translateAnimation = new TranslateAnimation(0,300,0,500);
        translateAnimation.setDuration(1000);
        mAnimationSet.addAnimation(translateAnimation);

        mAnimationButton = (Button) findViewById(R.id.animationall_button);

        mAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //                view.startAnimation(mAnimationSet);

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.animationall);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(MainActivity.this,"Animation End",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {


                    }
                });

                view.startAnimation(animation);
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
