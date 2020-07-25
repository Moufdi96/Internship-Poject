package com.example.pepperapp.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.Controller.FTPCoponents.FTPTransferData;
import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.example.pepperapp.model.Robot;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CreatNewMovementFragment extends Fragment {
    private static final String SHARED_PREF = "sharedPref";
    private static final String LAST_SELECTED_ROBOT = "lastSelectedRobot";
    private static final int REQUEST_CAMERA = 1;
    private View mView;
    private ImageView mRecordMovement;
    private ImageView mStopRecordMovement;
    private ImageButton mDemoVideo;
    private VideoView videoView;
    private Spinner mTypeSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private List<String> mExerciseTypeList;
    private TextInputEditText mExerciseName;
    private UICommand mUICommand;
    private Movement mNewMovement;
    private Button mSaveMovement;
    private JsonParseMovementLIst mJsonParseMovementLIst;
    private TextView mURIText;
    private String mName;
    private String mType;
    private String mUri;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.creat_new_movment_fragment, container, false);
        this.getActivity().setTitle("New Exercise");
        this.mTypeSpinner = (Spinner) mView.findViewById(R.id.spinner);
        this.mExerciseTypeList = new ArrayList<>();
        this.mExerciseTypeList.add("");
        this.mExerciseName = mView.findViewById(R.id.exercise_name);
        this.mDemoVideo = (ImageButton) mView.findViewById(R.id.demo_video);
        this.mRecordMovement = (ImageView) mView.findViewById(R.id.animation_mode);
        this.mURIText = (TextView) mView.findViewById(R.id.uri);
        this.mStopRecordMovement = (ImageView) mView.findViewById(R.id.stop_animation_mode);
        this.mSaveMovement = (Button) mView.findViewById(R.id.save_movement_button);
        this.mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        this.mJsonParseMovementLIst = new JsonParseMovementLIst(getContext(), loadRobotPreference());
        this.mExerciseTypeList.add(MovementType.NECK.toString());
        this.mExerciseTypeList.add(MovementType.ELBOW.toString());
        this.mExerciseTypeList.add(MovementType.FIST.toString());
        this.mExerciseTypeList.add(MovementType.HIP.toString());
        this.mExerciseTypeList.add(MovementType.SHOULDER.toString());
        this.mExerciseTypeList.add(MovementType.WRIST.toString());
        this.mExerciseTypeList.add(MovementType.COMBINED.toString());
        this.mSpinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mExerciseTypeList);
        this.mTypeSpinner.setAdapter(mSpinnerAdapter);
        this.mSaveMovement.setEnabled(false);
        this.mStopRecordMovement.setEnabled(false);

        if (mJsonParseMovementLIst.readJsonFile()) {
            mJsonParseMovementLIst.jsonToJavaObject();
        }

        this.mExerciseName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mName = mExerciseName.getText().toString().trim();
                mTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        mType = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        this.mDemoVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallery = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(openGallery, REQUEST_CAMERA);
            }
        });

        this.mRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    mUICommand = new UICommand(ConnectToRobotFragment.getmFtpClient().getFTPClient());
                    mUICommand.sendCommandToServer(UICommand.UIRequest.ACTIVATE_ANIMATION_MODE, getContext());
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Log.d("Reply", "" + mUICommand.feedbackFromServer());
                    if (mUICommand.feedbackFromServer().equals("200 Animation mode is on".trim())) {
                        Toast.makeText(getContext(), "Animation mode is on", Toast.LENGTH_SHORT).show();
                        mStopRecordMovement.setEnabled(true);
                    }

                    //FTPTransferData ftpTransferData = new FTPTransferData(ConnectToRobotFragment.getmFtpClient().getFTPClient());
                    //ftpTransferData.retrieveFile("Mov111.txt","Documents/FTPUploadedFiles","/data/data/com.example.pepperapp/files");
                    //Log.d("File", ftpTransferData.feedbackFromServer());
                    //ftpTransferData.sendFile("Mov222.txt","Documents/FTPUploadedFiles","/data/data/com.example.pepperapp/files");
                    //Log.d("File", ftpTransferData.feedbackFromServer());
                    //animationModeStatus = true;
                    //if(animationModeStatus){
                    //mRecordMovement.setImageResource(R.drawable.ic_pause_circle);
                    //} else{
                    //mRecordMovement.setImageResource(R.drawable.);
                    //Toast.makeText(getContext(),"Animation mode deactivated",Toast.LENGTH_SHORT).show();
                    //}

                } else {
                    openAlertDialog();
                }
            }
        });

        this.mStopRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    mUICommand.sendCommandToServer(UICommand.UIRequest.DEACTIVATE_ANIMATION_MODE, getContext());
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mUICommand.feedbackFromServer().equals("200 Animation mode is off".trim())) {
                        Toast.makeText(getContext(), "Animation mode is off", Toast.LENGTH_SHORT).show();
                        mSaveMovement.setEnabled(true);
                    }
                }
            }
        });

        this.mSaveMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    mUICommand.sendCommandToServer(UICommand.UIRequest.SAVE, getContext());
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("FEEDBACK", mUICommand.feedbackFromServer());

                    if (mUICommand.feedbackFromServer().equals("200 Movement saved".trim())) {
                        if (!mName.isEmpty() && !mType.isEmpty()) {
                            if (mUri != null) {
                                mNewMovement = new Movement(0, mName, MovementType.valueOf(mType), "", mUri);
                            } else {
                                mNewMovement = new Movement(0, mName, MovementType.valueOf(mType), "", null);
                            }
                            mJsonParseMovementLIst.getMovementList().get(mNewMovement.getmMovementType()).add(mNewMovement);
                            mJsonParseMovementLIst.writeToJsonFile(mJsonParseMovementLIst.javaObjectToJson());

                        }
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    }
                }


                getActivity().onBackPressed();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            mUri = data.getData().toString();
            this.mURIText.setText("Uri: " + mUri.toString());
            Log.d("PATH", "" + mUri);
        }
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
