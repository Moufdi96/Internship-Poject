package com.example.pepperapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;

public class PlayActivity extends Fragment {
    private View mView;
    private ImageView mPlayInRobot;
    private ImageView mPlayDemoVideo;
    private String mMvtName;
    private TextView mMvtNameText;

    public PlayActivity(String name) {
        this.mMvtName = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.play_activity_fragment,container,false);
        this.mMvtNameText = (TextView)mView.findViewById(R.id.mvt_name);
        this.mMvtNameText.setText(this.mMvtName);
        this.mPlayInRobot = (ImageView)mView.findViewById(R.id.play_on_robot);
        this.mPlayDemoVideo = (ImageView)mView.findViewById(R.id.demo_video_imview);
        this.mPlayInRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.mPlayDemoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container,new DemoVideoFragment(),"DemoVideoFragment").commit();
            }
        });
        return mView;
    }
}
