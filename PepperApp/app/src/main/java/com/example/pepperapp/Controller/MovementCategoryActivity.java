package com.example.pepperapp.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.pepperapp.model.MovementType;
import com.example.pepperproject.R;


public class MovementCategoryActivity extends AppCompatActivity {

    private ImageButton mBWrist;
    private ImageButton mBHip;
    private ImageButton mBShoulder;
    private ImageButton mBFist;
    private ImageButton mBNeck;
    private ImageButton mBElbow;
    private ImageButton mBCombined;
    private ImageButton mBBack;
    //riiiiiiiiiip
    private TextView mTHip;
    private TextView mTShoulder;
    private TextView mTFist;
    private TextView mTNeck;
    private TextView mTWrist;
    private TextView mTElbow;
    private TextView mTCombined;

    private Intent mChangeActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_category);
        this.setTitle("Exercise Categories");

        this.mBHip = (ImageButton)findViewById(R.id.b_hip);
        this.mBWrist = (ImageButton)findViewById(R.id.b_wrist);
        this.mBShoulder = (ImageButton)findViewById(R.id.b_shoulder);
        this.mBNeck = (ImageButton)findViewById(R.id.b_neck);
        this.mBElbow = (ImageButton)findViewById(R.id.b_Elbow);
        this.mBFist = (ImageButton)findViewById(R.id.b_fist);
        this.mBCombined = (ImageButton)findViewById(R.id.b_conbined);
        this.mBBack = (ImageButton)findViewById(R.id.back_button); 

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
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.SHOULDER.toString());
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBFist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.FIST.toString());
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBNeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.NECK.toString());
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBWrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.WRIST.toString());
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBElbow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type", MovementType.ELBOW.toString());
                startActivity(mChangeActivityIntent);
            }
        });

        this.mBCombined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MovementListActivity.class);
                mChangeActivityIntent.putExtra("movement type",MovementType.COMBINED.toString());
                startActivity(mChangeActivityIntent);
            }
        });
        
        this.mBBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementCategoryActivity.this,MainActivity.class);
                startActivity(mChangeActivityIntent);
            }
        });
    }
}
