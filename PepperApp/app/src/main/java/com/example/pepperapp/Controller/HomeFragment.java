package com.example.pepperapp.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.Robot;
import com.google.gson.Gson;

public class HomeFragment extends Fragment {
    private static final String SHARED_PREF = "sharedPref";
    private static final String LAST_SELECTED_ROBOT = "lastSelectedRobot";
    private ImageView mExercise;
    private ImageView mActivities;
    private ImageView mMonitoring;
    private TextView mConnectionStatus;
    private SharedPreferences mSharedPreferences;
    public  ConnectionDialog mRobotInfo;
    private ImageView mImageInfo;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.home_fragment,container,false);
        this.mExercise = mView.findViewById(R.id.movements);
        this.mActivities = mView.findViewById(R.id.activities);
        this.mMonitoring = mView.findViewById(R.id.monitoring);
        this.mImageInfo =(ImageView)mView.findViewById(R.id.img_info);
        this.mConnectionStatus =(TextView)mView.findViewById(R.id.connected_to);
        this.mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        Robot r = this.loadRobotPreference();
        if(r != null && r.getmConnectionStatus()){
            this.mRobotInfo = new ConnectionDialog("Robot info", "Cancel", "Name: " + r.getmRobotName() + "\nIP Address: " + r.getmRobotIPAddress() + "\nPort: " + r.getmRobotPort(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRobotInfo.dismiss();
                }
            });
            mConnectionStatus.setTextColor(Color.parseColor("#2ddc06"));
            mConnectionStatus.setText("connected to "+r.getmRobotName());
        }else{
            this.mRobotInfo = new ConnectionDialog("Not Connected", "Connect", "Have you already chosen a robot to connect with ?! if not please click 'Connect' to choose one, if yes please check your network", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRobotInfo.dismiss();
                    Intent homeActivity = new Intent(getActivity(), MainActivity.class);
                    homeActivity.putExtra ("connectToRobot","connectToRobotFragment");
                    startActivity(homeActivity);
                }
            });
            mConnectionStatus.setTextColor(Color.RED);
            mConnectionStatus.setText("disconnected");
        }
        mImageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRobotInfo.show(getFragmentManager(),"robotInfo");
            }
        });
        mExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mMvtListActivity = new Intent(getActivity(),MovementCategoryActivity.class);
                startActivity(mMvtListActivity);
            }
        });

        mMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"This feature has not been\n      yet implemented",Toast.LENGTH_SHORT).show();
            }
        });

        mActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avtListActivity = new Intent(getActivity(),ActivityListActivity.class);
                startActivity(avtListActivity);
            }
        });


        return mView;
    }

    public Robot loadRobotPreference() {
        String json;
        Gson gson = new Gson();
        json = this.mSharedPreferences.getString(LAST_SELECTED_ROBOT, "");
        //json = json + "test";
        Robot robot = gson.fromJson(json, Robot.class);
        return robot;
    }
}
