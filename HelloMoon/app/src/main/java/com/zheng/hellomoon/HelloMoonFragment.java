package com.zheng.hellomoon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by michael on 2015/8/27.
 */
public class HelloMoonFragment extends Fragment {

    private AudioPlayer mAudioPlayer = new AudioPlayer();

    private Button mPlayButton;
    private Button mStopButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hello_moon,container,false);

        mPlayButton = (Button) view.findViewById(R.id.hellomoon_playButton);
        mStopButton = (Button) view.findViewById(R.id.hellomoon_stopButton);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAudioPlayer.play(getActivity());
            }
        });

        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAudioPlayer.stop();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAudioPlayer.stop();
    }
}
