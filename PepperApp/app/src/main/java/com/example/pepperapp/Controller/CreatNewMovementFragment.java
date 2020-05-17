package com.example.pepperapp.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.ClientRequest;
import com.example.pepperapp.model.MovementType;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;

public class CreatNewMovementFragment extends Fragment implements Spinner.OnItemClickListener{
    private View mView;
    private ImageView mRecordMovement;
    private Spinner mTypeSpinner;
    private ArrayAdapter<String> mSpinnerAdapter;
    private List<String> mExerciseTypeList;
    private TextInputEditText mExerciseName;

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
        this.mExerciseTypeList.add(MovementType.NECK.toString());
        this.mExerciseTypeList.add(MovementType.ELBOW.toString());
        this.mExerciseTypeList.add(MovementType.FIST.toString());
        this.mExerciseTypeList.add(MovementType.HIP.toString());
        this.mExerciseTypeList.add(MovementType.SHOULDER.toString());
        this.mExerciseTypeList.add(MovementType.WRIST.toString());
        this.mExerciseTypeList.add(MovementType.COMBINED.toString());
        this.mSpinnerAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,mExerciseTypeList);
        this.mTypeSpinner.setAdapter(mSpinnerAdapter);

        this.mRecordMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectToRobotFragment.getmTcpClient().sendRequestToServer(ClientRequest.CREATE_NEW_MOVEMENT);
            }
        });

        return mView;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mSelectedTYpe = parent.getItemAtPosition(position).toString();
    }
}
