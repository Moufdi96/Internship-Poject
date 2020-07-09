package com.example.pepperapp.Controller;

import android.app.Dialog;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pepperapp.R;
import com.example.pepperapp.model.Category;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;
import com.example.pepperapp.model.Robot;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMovementFragment extends Fragment {
    private View mView;
    private ListView mListView;
    private Category mSelectedCategory;
    private ArrayList<String> mMovementListNames;
    private ArrayAdapter<String> mMovementArrayAdapter1;
    private FloatingActionButton mBAddToActivity;
    private ImageView mDeleteMovement;
    private ImageView mPlayMovement;
    private JsonParseMovementLIst mJsonParseMovementLIst;
    private Intent mIntent;
    private MovementType mSelectedCategoryType;
    private TextView mTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.list_movement_fragement, container, false);
        this.init();
        if (mJsonParseMovementLIst.readJsonFile()) {
            this.mJsonParseMovementLIst.jsonToJavaObject();
            for (Movement movement : this.mJsonParseMovementLIst.getMovementList().get(mSelectedCategoryType)) {
                this.mMovementListNames.add(movement.getmMovementName());

            }
        }

        if (this.mJsonParseMovementLIst.getMovementList().get(this.mSelectedCategoryType).isEmpty()) {
            this.mTextView.setVisibility(View.VISIBLE);
            this.mListView.setVisibility(View.INVISIBLE);
        } else {
            mListView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.INVISIBLE);
        }

        this.getActivity().setTitle(mSelectedCategoryType.toString());
        this.mListView.setAdapter(this.mMovementArrayAdapter1);
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
                        if (!mMovementListNames.isEmpty()) {
                            int size = mJsonParseMovementLIst.getMovementList().get(mSelectedCategoryType).size();
                            mJsonParseMovementLIst.getMovementList().get(mSelectedCategoryType).remove(position);
                            mJsonParseMovementLIst.writeToJsonFile(mJsonParseMovementLIst.javaObjectToJson());
                            mMovementListNames.remove(position);
                            mMovementArrayAdapter1.notifyDataSetChanged();
                            Toast.makeText(getContext(),"Successfully deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mBAddToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container, new CreatNewMovementFragment(), "createNewMovementFragment").commit();
            }
        });


        return mView;
    }

    public void init() {
        this.mIntent = getActivity().getIntent();
        this.mSelectedCategoryType = MovementType.valueOf(this.mIntent.getStringExtra("movement type"));
        this.mMovementListNames = new ArrayList<>();
        this.mJsonParseMovementLIst = new JsonParseMovementLIst(getContext());
        this.mListView = (ListView) mView.findViewById(R.id.movement_list);
        this.mBAddToActivity = (FloatingActionButton) mView.findViewById(R.id.fab);
        this.mDeleteMovement = (ImageView) mView.findViewById(R.id.delete_button);
        this.mPlayMovement = (ImageView) mView.findViewById(R.id.play_button);
        this.mTextView = (TextView) mView.findViewById(R.id.no_mvt_text);
        //this.mMovementList = new ArrayList<>();
        this.mMovementArrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mMovementListNames);
        this.mBAddToActivity.setBackgroundColor(Color.GRAY);

    }
}
