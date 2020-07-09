package com.example.pepperapp.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.Controller.FTPCoponents.FTPTransferData;
import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.ClientRequest;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.google.android.material.textfield.TextInputEditText;

import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPReply;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;

public class CreatNewMovementFragment extends Fragment {
    private View mView;
    private ImageView mRecordMovement;
    private ImageView mStopRecordMovement;
    private Spinner mTypeSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private List<String> mExerciseTypeList;
    private TextInputEditText mExerciseName;
    private UICommand mUICommand;
    private Movement mNewMovement;
    private boolean animationModeStatus;
    private Button mSaveMovement;
    private JsonParseMovementLIst mJsonParseMovementLIst;
    private String mName;
    private String mType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.creat_new_movment_fragment, container, false);
        this.getActivity().setTitle("New Exercise");
        this.mTypeSpinner = (Spinner) mView.findViewById(R.id.spinner);
        this.mExerciseTypeList = new ArrayList<>();
        this.mExerciseTypeList.add("");
        this.mExerciseName = mView.findViewById(R.id.exercise_name);
        this.mRecordMovement = (ImageView) mView.findViewById(R.id.animation_mode);
        this.mStopRecordMovement = (ImageView) mView.findViewById(R.id.stop_animation_mode);
        this.mSaveMovement = (Button)mView.findViewById(R.id.save_movement_button);
        this.mJsonParseMovementLIst = new JsonParseMovementLIst(getContext());
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

        if(mJsonParseMovementLIst.readJsonFile()){
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

        this.mRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectToRobotFragment.getmFtpClient() != null && ConnectToRobotFragment.getmFtpClient().isConnectionSuccessful() && ConnectToRobotFragment.getmFtpClient().isLoginSuccessful()) {
                    mUICommand = new UICommand(ConnectToRobotFragment.getmFtpClient().getFTPClient());
                    mUICommand.sendCommandToServer(UICommand.UIRequest.ACTIVATE_ANIMATION_MODE, getContext());
                    Log.d("Reply", ""+ mUICommand.feedbackFromServer());
                    if (mUICommand.feedbackFromServer().equals("200 Animation mode is on".trim())) {
                        Toast.makeText(getContext(), "Animation mode is on", Toast.LENGTH_SHORT).show();
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
                    //mRecordMovement.setImageResource(R.drawable.ic_play_circle);
                    //Toast.makeText(getContext(),"Animation mode deactivated",Toast.LENGTH_SHORT).show();
                    //}

                } else {
                    Toast.makeText(getContext(), "No connection to robot detected !", Toast.LENGTH_LONG).show();
                }
            }
        });

        this.mStopRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUICommand.sendCommandToServer(UICommand.UIRequest.DEACTIVATE_ANIMATION_MODE, getContext());
                if (mUICommand.feedbackFromServer().equals("200 Animation mode is on".trim())) {
                    Toast.makeText(getContext(), "Animation mode is off", Toast.LENGTH_SHORT).show();
                    mSaveMovement.setEnabled(true);
                }
            }
        });

        this.mSaveMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mName.isEmpty() && !mType.isEmpty()){
                    mNewMovement = new Movement(0,mName,MovementType.valueOf(mType),"");
                }

                mJsonParseMovementLIst.getMovementList().get(mNewMovement.getmMovementType()).add(mNewMovement);
                mJsonParseMovementLIst.writeToJsonFile(mJsonParseMovementLIst.javaObjectToJson());
                getActivity().onBackPressed();
            }
        });

        return mView;

    }
}
