package com.example.pepperapp.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.Category;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMovementFragment extends Fragment {
    private View mView;
    private ListView mListView;
    private Category mSelectedCategory;
    private ArrayList<Object> mMovementList;
    private ArrayAdapter<Object> mMovementArrayAdapter1;
    private FloatingActionButton mBAddToActivity;
    private ImageView mDeleteMovement;
    private ImageView mPlayMovement;
    private Intent mIntent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.list_movement_fragement, container, false);
        this.init();
        String retrieveIntentData = this.mIntent.getStringExtra("movement type");
        MovementType selectedCategoryType = MovementType.valueOf(retrieveIntentData);
        switch (selectedCategoryType) {
            case HIP:
                this.mSelectedCategory = new Category(MovementType.HIP);
                break;
            case SHOULDER:
                this.mSelectedCategory = new Category(MovementType.SHOULDER);
                break;
            case WRIST:
                this.mSelectedCategory = new Category(MovementType.WRIST);
                break;
            case NECK:
                this.mSelectedCategory = new Category(MovementType.NECK);
                break;
            case FIST:
                this.mSelectedCategory = new Category(MovementType.FIST);
                break;
            case ELBOW:
                this.mSelectedCategory = new Category(MovementType.ELBOW);
                break;
            case COMBINED:
                this.mSelectedCategory = new Category(MovementType.COMBINED);
                break;

        }
        this.getActivity().setTitle(mSelectedCategory.getmCategoryTitle());

        int index = 0;
        this.mSelectedCategory.addMovementToCategory(new Movement(index, "..........................movement 1..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 2..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 3..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 4..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 5..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 6..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 7..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 8..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 9..........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 10.........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 11.........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 12.........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 13.........................", mSelectedCategory.getmCategoryType(), ""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++, "..........................movement 14.........................", mSelectedCategory.getmCategoryType(), ""));

        for (int i = 0; i < this.mSelectedCategory.getmMovementList().size(); i++){
            this.mMovementList.add(mSelectedCategory.getmMovementList().get(i).getmMovementName());
        }
        this.mListView.setAdapter(this.mMovementArrayAdapter1);
        if (mMovementList.isEmpty()) {
            this.mBAddToActivity.hide();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int index = -1;
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                this.index = position;
                mPlayMovement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //int position = mMovementListView.getSelectedItemPosition();
                        //TODO
                    }
                });


                mDeleteMovement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index = -1;
                        if (!mMovementList.isEmpty()) {
                            mMovementList.remove(position);
                            mMovementArrayAdapter1.notifyDataSetChanged();
                        }
                    }
                });
            }
        });

        mBAddToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container,new CreatNewMovementFragment(),"createNewMovementFragment").commit();
            }
        });



        return mView;
    }

    public void init() {
        this.mListView = (ListView) mView.findViewById(R.id.movement_list);
        this.mBAddToActivity = (FloatingActionButton) mView.findViewById(R.id.fab);
        this.mDeleteMovement = (ImageView) mView.findViewById(R.id.delete_button);
        this.mPlayMovement = (ImageView) mView.findViewById(R.id.play_button);
        this.mMovementList = new ArrayList<>();
        this.mMovementArrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mMovementList);
        this.mBAddToActivity.setBackgroundColor(Color.GRAY);
        this.mIntent = getActivity().getIntent();

    }
}
