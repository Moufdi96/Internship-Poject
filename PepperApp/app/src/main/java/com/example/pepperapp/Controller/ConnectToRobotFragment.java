package com.example.pepperapp.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.JsonParseRobotList;
import com.example.pepperapp.model.Robot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ConnectToRobotFragment extends Fragment {
    private static final String SHARED_PREF = "sharedPref";
    private static final String LAST_SELECTED_ROBOT = "lastSelectedRobot";
    private View mView;
    private CardView mConnectButton;
    private ListView mRobotListView;
    private ArrayAdapter<String> mRobotListAdapter;
    private SharedPreferences mSharedPreferences;
    private JsonParseRobotList mRobotList;
    private List<String> mListRobotNames;
    int index = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.robot_connection_fragment, container, false);
        this.mRobotListView = mView.findViewById(R.id.robot_list);
        this.mConnectButton = mView.findViewById(R.id.connect_button);
        this.mSharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        this.mListRobotNames = new ArrayList<>();
        this.mRobotListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mListRobotNames);
        this.mListRobotNames.add("Add a new robot");
        this.mRobotList = new JsonParseRobotList(this.getContext());
        if (this.mRobotList.readJsonFile()) {
            this.mRobotList.jsonToJavaObject();
            for (Robot robot : mRobotList.getmRobotList()) {
                this.mListRobotNames.add(robot.getmRobotName());

            }
        }
        mRobotListView.setAdapter(mRobotListAdapter);

        mRobotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                index = position;
                if (position == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewRobotFragment(), "newRobotFragment").commit();
                }
            }
        });

        mRobotListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == -1) {
                    Toast.makeText(getContext(), "Please select first a Robot then click the button", Toast.LENGTH_LONG).show();
                } else if (index > 0) {
                    {
                        saveRobotPreference(mRobotList.getmRobotList().get(index - 1));
                        Log.d("hhhhhhhhhhhhhhhhhhh", index + loadRobotPreference());
                    }
                }
            }
        });

        return mView;


    }


    public void saveRobotPreference(Robot robot) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LAST_SELECTED_ROBOT, gson.toJson(robot)).commit();
    }

    public String loadRobotPreference() {
        String json;
        Gson gson = new Gson();
        json = this.mSharedPreferences.getString(LAST_SELECTED_ROBOT, "");
        json = json + "test";
        //Robot robot = gson.fromJson(json, Robot.class);
        return json;
    }

}
