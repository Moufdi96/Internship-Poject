package com.example.pepperapp.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;

public class ActivityFragment extends Fragment {
    private CardView mEx;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.activity_fragment,container,false);
        this.mEx = (CardView) mView.findViewById(R.id.ex_1);
        mEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().replace(R.id.activity_fragment_container,new PlayActivity(""),"playActivity").commit();
            }
        });
        return this.mView;

    }
}
