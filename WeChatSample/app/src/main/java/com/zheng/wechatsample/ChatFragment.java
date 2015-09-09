package com.zheng.wechatsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * Created by michael on 2015/8/27.
 */
public class ChatFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        FrameLayout frameLayout = new FrameLayout(getActivity());
        frameLayout.setLayoutParams(layoutParams);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

        TextView textView = new TextView(getActivity());
        layoutParams.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(layoutParams);
//        v.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText("聊天界面");
        textView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, dm));
        frameLayout.addView(textView);

        return frameLayout;
    }
}
