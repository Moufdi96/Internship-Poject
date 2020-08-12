package com.example.pepperapp.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.Robot;
import com.google.gson.Gson;

public class PlayActivity extends Fragment {
    private static final String SHARED_PREF = "sharedPref";
    private static final String LAST_SELECTED_ROBOT = "lastSelectedRobot";
    //private SharedPreferences mSharedPreferences;
    private View mView;
    private ImageView mPlayInRobot;
    private ImageView mPlayDemoVideo;
    //private String mMvtName;
    //private String mMvtName;
    private Movement mMovementToPlay;
    private TextView mMvtNameText;
    private UICommand mUICommand;
    private JsonParseMovementLIst mJsonParseMovementLIst;
    public PlayActivity(Movement movement) {
        this.mMovementToPlay = movement;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.play_activity_fragment, container, false);
        this.mMvtNameText = (TextView) mView.findViewById(R.id.mvt_name);
        this.mMvtNameText.setText(this.mMovementToPlay.getmMovementName());
        this.mPlayInRobot = (ImageView) mView.findViewById(R.id.play_on_robot);
        //this.mJsonParseMovementLIst = new JsonParseMovementLIst(getContext(), loadRobotPreference());
        this.mPlayDemoVideo = (ImageView) mView.findViewById(R.id.demo_video_imview);


        //if (mJsonParseMovementLIst.readJsonFile()) {
         //   mJsonParseMovementLIst.jsonToJavaObject();
        //}

        this.mPlayInRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    mUICommand = new UICommand(ConnectToRobotFragment.getmFtpClient().getFTPClient());
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String id = mMovementToPlay.getmMovementId();
                    mUICommand.sendCommandToServer(UICommand.UIRequest.PLAY_MOVEMENT, id, getContext());
                    if (mUICommand.feedbackFromServer().equals("200 Movement started".trim())) {
                        Toast.makeText(getContext(), "Movement started", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.mPlayDemoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMovementToPlay.getmURI()!=null){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container, new DemoVideoFragment(), "DemoVideoFragment").commit();
                }
            }
        });
        return mView;
    }

    public void openAlertDialog() {
        ConnectionDialog noConnectionAlert = new ConnectionDialog("Oops ! no robot found", "OK", "Please click 'OK' to choose a robot", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent homeActivity = new Intent(getActivity(), MainActivity.class);
                homeActivity.putExtra("connectToRobot", "connectToRobotFragment");
                startActivity(homeActivity);
            }
        });
        noConnectionAlert.show(getFragmentManager(), "noConnectionAlert");
    }

    /*public Robot loadRobotPreference() {
        String json;
        Gson gson = new Gson();
        json = this.mSharedPreferences.getString(LAST_SELECTED_ROBOT, "");
        //json = json + "test";
        Robot robot = gson.fromJson(json, Robot.class);
        return robot;
    }*/
}
