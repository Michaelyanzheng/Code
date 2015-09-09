package com.zheng.actionbartext.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zheng.actionbartext.R;

/**
 * Created by michael on 2015/8/26.
 */
public class AlbumFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album,null);

        TextView albumTextView = (TextView) view.findViewById(R.id.album_TextView);
        albumTextView.setTextColor(Color.RED);

        return view;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("Album Fragment");
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        LinearLayout layout = new LinearLayout(getActivity());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layout.addView(textView, params);
//        return layout;
//    }
}
