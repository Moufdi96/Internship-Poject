package com.example.pepperapp.Controller;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.JsonParseRobotList;
import com.example.pepperapp.model.Robot;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.net.ssl.SNIServerName;

public class NewRobotFragment extends Fragment {
    private View mView;
    private TextInputEditText mRobotName;
    private TextInputEditText mRobotIPAddress;
    private TextInputEditText mRoboPort;
    private String mRNameInput;
    private String mRIPAddressInput;
    private String mRPortInput;
    private Button mSave;
    private TextWatcher mTextWatcher;
    private JsonParseRobotList mRobotList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.new_robot_fragment, container, false);
        this.init();
        mRobotName.addTextChangedListener(mTextWatcher);
        mRobotIPAddress.addTextChangedListener(mTextWatcher);
        mRoboPort.addTextChangedListener(mTextWatcher);
        return mView;
    }

    public void init() {
        this.mRobotList = new JsonParseRobotList(getContext());
        if(this.mRobotList.readJsonFile()){
            this.mRobotList.jsonToJavaObject();
        }
        this.mRobotName = mView.findViewById(R.id.robot_name);
        this.mRobotIPAddress = mView.findViewById(R.id.robot_ip_address);
        this.mRoboPort = mView.findViewById(R.id.robot_port);
        this.mSave = mView.findViewById(R.id.save_button);
        this.mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRNameInput = mRobotName.getText().toString().trim();
                mRIPAddressInput = mRobotIPAddress.getText().toString().trim();
                mRPortInput = mRoboPort.getText().toString().trim();
                if (!mRNameInput.isEmpty() && !mRIPAddressInput.isEmpty() && !mRPortInput.isEmpty()) {
                    mSave.setEnabled(true);
                    mSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), mRNameInput + " .added successfully to the robot list", Toast.LENGTH_SHORT).show();
                            Robot newRobot = new Robot(mRNameInput, mRIPAddressInput, mRPortInput,false);
                            mRobotList.getmRobotList().add(newRobot);
                            mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());

                            getActivity().onBackPressed();
                        }
                    });

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}
