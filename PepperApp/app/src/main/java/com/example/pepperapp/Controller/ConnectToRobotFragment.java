package com.example.pepperapp.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.Controller.FTPCoponents.FtpClient;
import com.example.pepperapp.Controller.FTPCoponents.UICommand;
import com.example.pepperapp.R;
import com.example.pepperapp.model.JsonParseRobotList;
import com.example.pepperapp.model.Robot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.apache.commons.net.ftp.FTPClient;

import java.util.ArrayList;
import java.util.List;

public class ConnectToRobotFragment extends Fragment {
    private static final String SHARED_PREF = "sharedPref";
    private static final String LAST_SELECTED_ROBOT = "lastSelectedRobot";
    private View mView;
    private ImageButton mDeleteRobotButton;
    private ImageButton mEditRobotInfoButton;
    private ListView mRobotListView;
    private ArrayAdapter<String> mRobotListAdapter;
    private SharedPreferences mSharedPreferences;
    private JsonParseRobotList mRobotList;
    private List<String> mListRobotNames;
    private static FtpClient mFtpClient;
    private UICommand mUICommand;
    private Robot mLastConnectedRobot;
    private Switch mSwitch;
    private TextView mTextView;
    private TextView mTextNoRobot;
    private int index = -1;
    private FloatingActionButton mBAddToActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.robot_connection_fragment, container, false);
        this.mRobotListView = mView.findViewById(R.id.robot_list);
        this.mDeleteRobotButton = mView.findViewById(R.id.delete_robot);
        this.mTextView = mView.findViewById(R.id.last_connected_robot);
        this.mSwitch = mView.findViewById(R.id.connect_switch);
        this.mTextNoRobot = (TextView) mView.findViewById(R.id.no_robot_text);
        this.mBAddToActivity = (FloatingActionButton) mView.findViewById(R.id.fab_add_robot);
        this.mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        if (savedInstanceState != null) {
            //mSwitch.setChecked(savedInstanceState.getBoolean("switchState"));
            //mTextView.setText(savedInstanceState.getString("textViewState"));
        }
        if (loadRobotPreference() != null) {
            this.mLastConnectedRobot = loadRobotPreference();
            this.mSwitch.setChecked(this.mLastConnectedRobot.getmConnectionStatus());
            if (this.mSwitch.isChecked()) {
                this.mTextView.setText("Connected to " + mLastConnectedRobot.getmRobotName());
            }
        } else {
            this.mLastConnectedRobot = new Robot("", "", "", false);
            this.mSwitch.setChecked(false);
        }

        this.mSwitch.setEnabled(false);
        this.mEditRobotInfoButton = mView.findViewById(R.id.edit_robot_info);
        this.mListRobotNames = new ArrayList<>();
        this.mRobotListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mListRobotNames);

        this.mRobotList = new JsonParseRobotList(this.getContext());
        if (this.mRobotList.readJsonFile()) {
            this.mRobotList.jsonToJavaObject();
            for (Robot robot : mRobotList.getmRobotList()) {
                this.mListRobotNames.add(robot.getmRobotName());

            }
        }
        if (this.mRobotList.getmRobotList().isEmpty()) {
            this.mTextNoRobot.setVisibility(View.VISIBLE);
            this.mRobotListView.setVisibility(View.INVISIBLE);
            this.mSwitch.setVisibility(View.INVISIBLE);
        } else {
            mRobotListView.setVisibility(View.VISIBLE);
            mTextNoRobot.setVisibility(View.INVISIBLE);
            mSwitch.setVisibility(View.VISIBLE);
        }
        mRobotListView.setAdapter(mRobotListAdapter);

        mRobotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                index = position;
                mSwitch.setEnabled(true);
                mSwitch.setChecked(mRobotList.getmRobotList().get(index).getmConnectionStatus());
            }
        });

        mRobotListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                mEditRobotInfoButton.setVisibility(View.VISIBLE);
                mDeleteRobotButton.setVisibility(View.VISIBLE);
                mView.findViewById(R.id.cardview1).setVisibility(View.VISIBLE);
                mView.findViewById(R.id.cardview2).setVisibility(View.VISIBLE);
                return false;
            }
        });

        mEditRobotInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("selectedRobotPosition", index);
                EditRobotFragment editRobotFragment = new EditRobotFragment();
                editRobotFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, editRobotFragment, "EditRobotFragment").commit();
            }
        });

        /*mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTcpClient.sendRequestToServer(ClientRequest.CREATE_NEW_MOVEMENT);
            }
        });*/

        mDeleteRobotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mRobotList.getmRobotList().isEmpty()) {
                    if (mLastConnectedRobot.getmRobotName() == mRobotList.getmRobotList().get(index).getmRobotName()) {
                        mLastConnectedRobot = new Robot("", "", "", false);
                        saveRobotPreference(mLastConnectedRobot);
                        mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());
                        mTextView.setText("Select a Robot");
                        mSwitch.setChecked(false);
                    }
                    mRobotList.getmRobotList().remove(index);
                    mListRobotNames.remove(index);
                    mRobotListAdapter.notifyDataSetChanged();
                    mRobotListView.setAdapter(mRobotListAdapter);
                    if (mRobotList.getmRobotList().isEmpty()) {
                        mTextNoRobot.setVisibility(View.VISIBLE);
                        mRobotListView.setVisibility(View.INVISIBLE);
                        mSwitch.setVisibility(View.INVISIBLE);
                    }
                    mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());
                    //mEditRobotInfoButton.setVisibility(View.INVISIBLE);
                    //mDeleteRobotButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        mBAddToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewRobotFragment(), "createNewRobotFragment").commit();
            }
        });

        /*mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (index > 0 && !isChecked) {
                    mTextView.setText("Select a Robot");
                    mTcpClient.disconnectSocket();
                    mLastConnectedRobot.setmConnectionStatus(false);
                    saveRobotPreference(mLastConnectedRobot);
                    mRobotList.getmRobotList().get(index - 1).setmConnectionStatus(false);
                    mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());

                } else if (index > 0 && isChecked) {
                    if (!mLastConnectedRobot.getmConnectionStatus()) {
                        mTcpClient = new TCPClient(mRobotList.getmRobotList().get(index - 1));
                        mTcpClient.getmThread().start();
                        if (mTcpClient.isSocketConnected()) {
                            mRobotList.getmRobotList().get(index - 1).setmConnectionStatus(true);
                            mLastConnectedRobot = mRobotList.getmRobotList().get(index - 1);
                            saveRobotPreference(mLastConnectedRobot);
                            mTextView.setText("Connected to " + mLastConnectedRobot.getmRobotName());
                            mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());

                        }else{
                            mSwitch.setChecked(false);
                        }
                    } else {
                        mSwitch.setChecked(false);
                        Toast.makeText(getContext(), "Already connected to " + mLastConnectedRobot.getmRobotName()
                                + "please disconnect first if you wish to connect to another robot", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });*/

        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index >= 0 && !mSwitch.isChecked() && mFtpClient != null) {
                    mFtpClient.disconnect();
                    if (mFtpClient.isCLosingSuccessful()) {
                        mLastConnectedRobot.setmConnectionStatus(false);
                        saveRobotPreference(mLastConnectedRobot);
                        mRobotList.getmRobotList().get(index).setmConnectionStatus(false);
                        mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());
                        Toast.makeText(getContext(), "Disconnected", Toast.LENGTH_LONG).show();
                        mTextView.setText("Select a Robot");

                    }
                } else if (index >= 0 && mSwitch.isChecked()) {
                    if (!mLastConnectedRobot.getmConnectionStatus()) {
                        mFtpClient = new FtpClient(mRobotList.getmRobotList().get(index));
                        mFtpClient.getmThread().start();
                        try {

                            mFtpClient.getmThread().join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (mFtpClient.isConnectionSuccessful() && mFtpClient.isLoginSuccessful()) {
                            Log.d("Login", mFtpClient.getFTPClient().getReplyString());
                            mRobotList.getmRobotList().get(index).setmConnectionStatus(true);
                            mLastConnectedRobot = mRobotList.getmRobotList().get(index);
                            saveRobotPreference(mLastConnectedRobot);
                            mTextView.setText("      Connected to " + mLastConnectedRobot.getmRobotName());
                            Toast.makeText(getContext(), "Connected", Toast.LENGTH_LONG).show();
                            mRobotList.writeToJsonFile(mRobotList.javaObjectToJson());
                        } else {
                            Toast.makeText(getContext(), "Connection failed " + mRobotList.getmRobotList().get(index).getmRobotName() + "'s server not found",
                                    Toast.LENGTH_LONG).show();
                            mSwitch.setChecked(false);
                        }

                    } else {
                        mSwitch.setChecked(false);
                        Toast.makeText(getContext(), "Already connected to " + mLastConnectedRobot.getmRobotName()
                                + " please disconnect first if you wish to connect to another robot", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return mView;

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("switchState", mSwitch.isChecked());
        outState.putString("textViewState", mTextView.getText().toString());
    }

    public void saveRobotPreference(Robot robot) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LAST_SELECTED_ROBOT, gson.toJson(robot)).commit();
    }

    public Robot loadRobotPreference() {
        String json;
        Gson gson = new Gson();
        json = this.mSharedPreferences.getString(LAST_SELECTED_ROBOT, "");
        //json = json + "test";
        Robot robot = gson.fromJson(json, Robot.class);
        return robot;
    }

    public static FtpClient getmFtpClient() {
        return mFtpClient;
    }
}
