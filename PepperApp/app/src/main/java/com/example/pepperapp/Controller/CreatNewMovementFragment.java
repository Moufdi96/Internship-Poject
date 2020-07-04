package com.example.pepperapp.Controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.ClientRequest;
import com.example.pepperapp.model.MovementType;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;

public class CreatNewMovementFragment extends Fragment implements Spinner.OnItemClickListener {
    private View mView;
    private ImageView mRecordMovement;
    private ImageView mRecordMovement2;
    private Spinner mTypeSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private List<String> mExerciseTypeList;
    private TextInputEditText mExerciseName;
    private UICommand mUICommand;
    private boolean animationModeStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.creat_new_movment_fragment,container,false);
        this.getActivity().setTitle("New Exercise");
        this.mTypeSpinner = (Spinner)mView.findViewById(R.id.spinner);
        this.mExerciseTypeList = new ArrayList<>();
        this.mExerciseTypeList.add("");
        this.mExerciseName = mView.findViewById(R.id.exercise_name);
        this.mRecordMovement = (ImageView) mView.findViewById(R.id.animation_mode);
        this.mRecordMovement2 = (ImageView) mView.findViewById(R.id.stop_animation_mode);
        this.mExerciseTypeList.add(MovementType.NECK.toString());
        this.mExerciseTypeList.add(MovementType.ELBOW.toString());
        this.mExerciseTypeList.add(MovementType.FIST.toString());
        this.mExerciseTypeList.add(MovementType.HIP.toString());
        this.mExerciseTypeList.add(MovementType.SHOULDER.toString());
        this.mExerciseTypeList.add(MovementType.WRIST.toString());
        this.mExerciseTypeList.add(MovementType.COMBINED.toString());
        this.mSpinnerAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,mExerciseTypeList);
        this.mTypeSpinner.setAdapter(mSpinnerAdapter);
        this.animationModeStatus = false;

        this.mRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ConnectToRobotFragment.getmFtpClient() !=null &&  ConnectToRobotFragment.getmFtpClient().isConnected()){
                    mUICommand = new UICommand(ConnectToRobotFragment.getmFtpClient().getFTPClient());
                    mUICommand.sendCommandToServer(UICommand.UIRequest.CREATE_NEW_MOVEMENT,getContext());
                    Log.d("PLAY", mUICommand.feedbackFromServer());
                    //animationModeStatus = true;
                    //if(animationModeStatus){
                        //mRecordMovement.setImageResource(R.drawable.ic_pause_circle);
                        Toast.makeText(getContext(),"Animation mode activated",Toast.LENGTH_SHORT).show();
                    //} else{
                        //mRecordMovement.setImageResource(R.drawable.ic_play_circle);
                        //Toast.makeText(getContext(),"Animation mode deactivated",Toast.LENGTH_SHORT).show();
                    //}

                }else{
                    Toast.makeText(getContext(),"No connection to robot detected !",Toast.LENGTH_LONG).show();
                }
            }
        });

        this.mRecordMovement2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationModeStatus = true;
                Toast.makeText(getContext(),"Animation mode deactivated",Toast.LENGTH_SHORT).show();
            }
        });

        return mView;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mSelectedTYpe = parent.getItemAtPosition(position).toString();
    }
}
