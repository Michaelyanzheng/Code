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
public class MichaelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_michael,container);

        TextView michaelTextView = (TextView) view.findViewById(R.id.michael_TextView);
        michaelTextView.setText("michael michael TextView");

        return view;
    }
}
