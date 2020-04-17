package com.example.pepperapp.Controller;

import android.content.Intent;
import android.media.Image;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;

public class HomeFragment extends Fragment {
    private ImageView mExercise;
    private ImageView mActivities;
    private ImageView mMonitoring;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment,container,false);
        mExercise = mView.findViewById(R.id.movements);
        mExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mMvtListActivity = new Intent(getActivity(),MovementCategoryActivity.class);
                startActivity(mMvtListActivity);
            }
        });
        return mView;
    }
}
