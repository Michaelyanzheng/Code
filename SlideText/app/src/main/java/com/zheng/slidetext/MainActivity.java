package com.zheng.slidetext;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends BaseFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new ZhengFragment();
    }


    @Override
    protected void onResume() {
        super.onResume();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_content);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });
    }
}
