package com.zheng.slidetext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by michael on 2015/8/25.
 */
public class ZhengFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zheng,container);

        TextView zhengTextView = (TextView) view.findViewById(R.id.zheng_TextView);
        zhengTextView.setText("zheng zheng michael michael");

        return view;
    }
}
