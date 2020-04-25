package com.example.pepperapp.Controller;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class ConnectToRobotFragment extends Fragment {
    private View mView;
    private CardView mConnectButton;
    private ListView mRobotListView;
    private ArrayAdapter<String> mRobotListAdapter;
    private List<String> mListRobot;
    int index = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.robot_connection_fragment,container,false);
        this.mRobotListView = mView.findViewById(R.id.robot_list);
        this.mConnectButton = mView.findViewById(R.id.connect_button);
        this.mListRobot = new ArrayList<>();
        this.mRobotListAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,mListRobot);
        this.mListRobot.add("Add a new robot");
        this.mListRobot.add("Max");
        this.mListRobot.add("Pepper");
        this.mListRobot.add("Moh");
        this.mListRobot.add("Fatima");
        this.mListRobot.add("Julian");
        this.mListRobot.add("Moufdi");
        mRobotListView.setAdapter(mRobotListAdapter);

        mRobotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                index = position;
                if (position==0){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewRobotFragment(),"newRobotFragment").commit();
                }
            }
        });

        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index == -1){
                    Toast.makeText(getContext(),"Please select first a Robot then click the button",Toast.LENGTH_LONG).show();
                }
            }
        });

        return mView;

    }
}
