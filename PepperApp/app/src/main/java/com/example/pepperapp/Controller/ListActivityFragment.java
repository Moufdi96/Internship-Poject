package com.example.pepperapp.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.Activity;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListActivityFragment extends Fragment {
    private View mView;
    private ListView mListView;
    private ArrayList<Object> mActivityList;
    private ArrayAdapter<Object> mActivityArrayAdapter;
    private FloatingActionButton mBAddToActivity;
    private ImageView mDeleteActivity;
    private ImageView mPlayActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.list_activity_fragment, container, false);
        this.init();
        int index = 0;
        List listMovment = new ArrayList<>();
        listMovment.add(new Movement(index, "..........................movement 1..........................",MovementType.NECK, ""));
        listMovment.add(new Movement(index, "..........................movement 2..........................",MovementType.COMBINED, ""));
        listMovment.add(new Movement(index, "..........................movement 3..........................",MovementType.WRIST, ""));
        Activity act1 = new Activity("__________Activity1__________",listMovment);
        Activity act2 = new Activity("___________Activity2__________",listMovment);
        Activity act3 = new Activity("Activity3",listMovment);
        Activity act4 = new Activity("Activity4",listMovment);
        Activity act5 = new Activity("Activity5",listMovment);
        Activity act6 = new Activity("Activity6",listMovment);
        Activity act7 = new Activity("Activity7",listMovment);
        Activity act8 = new Activity("Activity8",listMovment);

        mActivityList.add(act1.getmActivityName());
        mActivityList.add(act2.getmActivityName());
        mActivityList.add(act3.getmActivityName());
        mActivityList.add(act4.getmActivityName());
        mActivityList.add(act5.getmActivityName());
        mActivityList.add(act6.getmActivityName());
        mActivityList.add(act7.getmActivityName());
        mActivityList.add(act8.getmActivityName());

        this.mListView.setAdapter(mActivityArrayAdapter);
        mBAddToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment_container,new CreatNewActivityFragment(),"creatNewActivityFragment").commit();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mPlayActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO
                    }
                });

                mDeleteActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!mActivityList.isEmpty()){
                            mActivityList.remove(position);
                            mActivityArrayAdapter.notifyDataSetChanged();
                        }
                    }
                });

            }
        });

        return this.mView;
    }

    public void init() {
        this.mListView = (ListView) mView.findViewById(R.id.activity_list);
        this.mBAddToActivity = (FloatingActionButton) mView.findViewById(R.id.fab_activity_list);
        this.mDeleteActivity = (ImageView) mView.findViewById(R.id.delete_button_activity_list);
        this.mPlayActivity = (ImageView) mView.findViewById(R.id.play_button);
        this.mActivityList = new ArrayList<>();
        this.mActivityArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mActivityList);
        this.mBAddToActivity.setBackgroundColor(Color.GRAY);

    }
}
