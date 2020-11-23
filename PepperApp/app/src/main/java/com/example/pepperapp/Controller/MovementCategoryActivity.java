package com.example.pepperapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.MovementType;

import org.apache.commons.net.ftp.FTPCmd;


public class MovementCategoryActivity extends AppCompatActivity {
    private MovementCategoryActivity instance;
    private ImageButton mBWrist;
    private ImageButton mBHip;
    private ImageButton mBShoulder;
    private ImageButton mBFist;
    private ImageButton mBNeck;
    private ImageButton mBElbow;
    private ImageButton mBCombined;
    private ConnectionDialog mNotConnectedDialogue;
    private ImageButton mBBack;
    private TextView mTHip;
    private TextView mTShoulder;
    private TextView mTFist;
    private TextView mTNeck;
    private TextView mTWrist;
    private TextView mTElbow;
    private TextView mTCombined;
    private Toolbar mToolbar;

    private Intent mChangeActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        setContentView(R.layout.activity_movement_category);
        this.setTitle("Exercise Categories");
        this.mToolbar = findViewById(R.id.category_toolbar);
        this.setSupportActionBar(this.mToolbar);
        this.mNotConnectedDialogue = new ConnectionDialog("Not Connected", "Connect", "It seems you're not connected to a robot yet! Please click 'Connect' to choose one", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNotConnectedDialogue.dismiss();
                Intent homeActivity = new Intent(instance, MainActivity.class);
                homeActivity.putExtra ("connectToRobot","connectToRobotFragment");
                startActivity(homeActivity);
            }
        });
        this.mBHip = (ImageButton)findViewById(R.id.b_hip);
        this.mBWrist = (ImageButton)findViewById(R.id.b_wrist);
        this.mBShoulder = (ImageButton)findViewById(R.id.b_shoulder);
        this.mBNeck = (ImageButton)findViewById(R.id.b_neck);
        this.mBElbow = (ImageButton)findViewById(R.id.b_Elbow);
        this.mBFist = (ImageButton)findViewById(R.id.b_fist);
        this.mBCombined = (ImageButton)findViewById(R.id.b_conbined);

        this.mTHip = (TextView) findViewById(R.id.t_hip);
        this.mTShoulder = (TextView) findViewById(R.id.t_shoulder);
        this.mTFist = (TextView) findViewById(R.id.t_fist);
        this.mTNeck = (TextView) findViewById(R.id.t_neck);
        this.mTWrist = (TextView) findViewById(R.id.t_wrist);
        this.mTElbow = (TextView) findViewById(R.id.t_Elbow);
        this.mTCombined = (TextView) findViewById(R.id.t_conbined);

        this.mBHip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this, MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type", MovementType.HIP.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.SHOULDER.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBFist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.FIST.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBNeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.NECK.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBWrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.WRIST.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBElbow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type", MovementType.ELBOW.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

        this.mBCombined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.COMBINED.toString());
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    startActivity(mChangeActivityIntent);
                }
                else{
                    mNotConnectedDialogue.show(getSupportFragmentManager(),"notConnected");
                }
            }
        });

    }
}
