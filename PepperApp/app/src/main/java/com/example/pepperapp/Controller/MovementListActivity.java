package com.example.pepperapp.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperapp.model.Category;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;

import com.example.pepperproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovementListActivity extends AppCompatActivity {
    //riiiiiiiiiiiiiiiiip
    private ListView mListView;
    private ListView mActivityListView;
    private Category mSelectedCategory;
    private ArrayList<Object> mMovementList;
    private ArrayList<Object> mActivityList;
    private ArrayAdapter<Object> mMovementArrayAdapter1;
    private ArrayAdapter<Object> mMovementArrayAdapter2;
    private ArrayAdapter<Object> mActivityArrayAdapter1;
    private ArrayAdapter<Object> mActivityArrayAdapter2;
    private FloatingActionButton mBAddToActivity;
    //private LinearLayout mLinearLayout;
    //private  TextView mDescription;
    private ImageButton mDeleteMovement;
    private ImageButton mEditMovement;
    private ImageButton mPlayMovement;
    //private  ImageButton mBBackToCategory;
    private Intent mChangeActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_list);
        Intent intent = this.getIntent();
        this.layoutInit();
        this.mMovementList = new ArrayList<>();
        this.mActivityList = new ArrayList<>();
        this.mActivityArrayAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mActivityList);
        this.mActivityArrayAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,mActivityList);
        this.mMovementArrayAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mMovementList);
        this.mMovementArrayAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,mMovementList);

        this.mBAddToActivity.setBackgroundColor(Color.GRAY);
        this.mPlayMovement.setTag("play");
        this.mEditMovement.setTag("edit");
        String retrieveIntentData = intent.getStringExtra("movement type");
        MovementType selectedCategoryType = MovementType.valueOf(retrieveIntentData);
        switch (selectedCategoryType){
            case HIP:
                this.mSelectedCategory = new Category(MovementType.HIP);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case SHOULDER:
                this.mSelectedCategory = new Category(MovementType.SHOULDER);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case WRIST:
                this.mSelectedCategory = new Category(MovementType.WRIST);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case NECK:
                this.mSelectedCategory = new Category(MovementType.NECK);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case FIST:
                this.mSelectedCategory = new Category(MovementType.FIST);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case ELBOW:
                this.mSelectedCategory = new Category(MovementType.ELBOW);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
            case COMBINED:
                this.mSelectedCategory = new Category(MovementType.COMBINED);
                this.setTitle(mSelectedCategory.getmCategoryTitle());
                break;
        }

        int index = 0;
        this.mSelectedCategory.addMovementToCategory(new Movement(index,  "..........................movement 1..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 2..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 3..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 4..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 5..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 6..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 7..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 8..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 9..........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 10.........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 11.........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 12.........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 13.........................",mSelectedCategory.getmCategoryType(),""));
        this.mSelectedCategory.addMovementToCategory(new Movement(index++,"..........................movement 14.........................",mSelectedCategory.getmCategoryType(),""));

        for(int i = 0;i< this.mSelectedCategory.getmMovementList().size();i++)
            this.mMovementList.add(mSelectedCategory.getmMovementList().get(i).getmMovementName());

        this.mActivityList.add("..............Create a new Activity..................");
        this.mActivityList.add(".....................Activity 1......................");
        this.mActivityList.add(".....................Activity 2......................");
        this.mActivityList.add(".....................Activity 3......................");
        this.mActivityList.add(".....................Activity 4......................");
        this.mActivityList.add(".....................Activity 5......................");

        this.mListView.setAdapter(mMovementArrayAdapter1);
        this.mActivityListView.setVisibility(View.INVISIBLE);
        this.mActivityListView.setAdapter(mActivityArrayAdapter1);
        if (mMovementList.isEmpty()){
            this.mBAddToActivity.hide();
        }

        /*this.mMovementListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                //Log.i("checkedItems", "idskdscbsjdcb");
                mMovementListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                mMovementListView.setAdapter(mMovementArrayAdapter2);
                mDeleteMovement.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItems = mMovementListView.getCheckedItemPositions();
                        int count = mMovementArrayAdapter1.getCount();

                        for(int index = count-1;index >= 0;index--){
                            if(checkedItems.get(index)){
                                mMovementArrayAdapter2.remove(mMovementList.get(index));

                            }
                        }
                        checkedItems.clear();
                        mMovementArrayAdapter2.notifyDataSetChanged();
                    }
                });


                return false;
            }
        });*/

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int index = -1;
            boolean activityListDisplayed = false;
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,final int position, long id) {
                if(!this.activityListDisplayed){
                    mBAddToActivity.show();
                }
                else {
                    mBAddToActivity.hide();
                }
                this.index = position;
                mPlayMovement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d("tag", "onItemClick: "+index);
                            if(index > -1){
                                index = -1;
                                //int position = mMovementListView.getSelectedItemPosition();
                                String tag = mPlayMovement.getTag().toString();
                                if (tag == "play") {
                                }//TODO

                                if (tag == "confirm") {
                                    activityListDisplayed = false;
                                    mListView.setAdapter(mMovementArrayAdapter1);
                                    mPlayMovement.setTag("play");
                                    //mActivityListView.setVisibility(View.INVISIBLE);
                                    //mDescription.setVisibility(View.VISIBLE);
                                    mDeleteMovement.setVisibility(View.VISIBLE);
                                    mPlayMovement.setBackgroundResource(R.color.colorAccent);
                                    mEditMovement.setBackgroundResource(R.color.colorAccent);
                                    mPlayMovement.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                                    mEditMovement.setImageResource(R.drawable.ic_mode_edit_black_24dp);
                                    Toast.makeText(MovementListActivity.this, "The Movement has been added to the selected activity", Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(MovementListActivity.this, "No Movement left to delete", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                mEditMovement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            index = -1;
                            //int position = mMovementListView.getSelectedItemPosition();
                            String tag = mEditMovement.getTag().toString();
                            if (tag == "edit") {
                            } // TODO

                            if (tag == "cancel") {
                                activityListDisplayed = false;
                                mListView.setAdapter(mMovementArrayAdapter1);
                                mEditMovement.setTag("edit");
                                mActivityListView.setVisibility(View.INVISIBLE);
                                //mDescription.setVisibility(View.VISIBLE);
                                mDeleteMovement.setVisibility(View.VISIBLE);
                                mPlayMovement.setBackgroundResource(R.color.colorAccent);
                                mEditMovement.setBackgroundResource(R.color.colorAccent);
                                mEditMovement.setImageResource(R.drawable.ic_mode_edit_black_24dp);
                                mPlayMovement.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                                //Toast.makeText(MovementListActivity.this,"The Movement has been added to the selected activity",Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(MovementListActivity.this, "No Movement left to edit", Toast.LENGTH_LONG).show();
                        }


                    }
                });

                mDeleteMovement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index =-1;
                        if (!mMovementList.isEmpty()) {
                            mMovementList.remove(position);
                            mMovementArrayAdapter1.notifyDataSetChanged();
                            if (mMovementList.isEmpty()){
                                mBAddToActivity.hide();
                            }
                        }
                    }
                });

                mBAddToActivity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        index = -1;
                        activityListDisplayed = true;
                        mListView.setAdapter(mActivityArrayAdapter1);
                        //mDescription.setVisibility(View.INVISIBLE);
                        //mActivityListView.setVisibility(View.VISIBLE);
                        mDeleteMovement.setVisibility(View.INVISIBLE);
                        mEditMovement.setTag("cancel");
                        mPlayMovement.setTag("confirm");
                        mPlayMovement.setBackgroundColor(Color.TRANSPARENT);
                        mEditMovement.setBackgroundColor(Color.TRANSPARENT);
                        mPlayMovement.setImageResource(R.drawable.ic_done_black_40dp);
                        mEditMovement.setImageResource(R.drawable.ic_close_black_24dp);
                        mBAddToActivity.hide();

                    }
                });
            }
        });



        /*mActivityListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mActivityListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                mActivityListView.setAdapter(mActivityArrayAdapter2);
                mDeleteMovement.setVisibility(View.VISIBLE);

                return false;
            }
        });*/

        mActivityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        /*mBBackToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeActivityIntent = new Intent(MovementListActivity.this,MovementCategoryActivity.class);
                startActivity(mChangeActivityIntent);
            }
        });*/



        /*mDeleteMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDeleteMovement.getTag().toString() == "confirm"){
                    mDeleteMovement.setTag("delete");
                    mActivityListView.setVisibility(View.INVISIBLE);
                    mDescription.setVisibility(View.VISIBLE);
                    mEditMovement.setVisibility(View.VISIBLE);
                    mPlayMovement.setVisibility(View.VISIBLE);
                    mDeleteMovement.setBackgroundResource(R.color.colorAccent);
                }
            }
        });



        mDeleteMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Object selectedItem = mMovementListView.getSelectedItem();
                 mMovementArrayAdapter1.remove(selectedItem);
                mMovementArrayAdapter1.notifyDataSetChanged();

            }
        });
*/



    }

    public void layoutInit(){
        this.mActivityListView = (ListView)findViewById(R.id.activity_list);
        this.mListView = (ListView)findViewById(R.id.movement_list);
        this.mBAddToActivity = (FloatingActionButton)findViewById(R.id.fab);
        this.mDeleteMovement = (ImageButton)findViewById(R.id.delete_button);
        this.mPlayMovement = (ImageButton)findViewById(R.id.play_button);
        this.mEditMovement = (ImageButton)findViewById(R.id.edit_button);
        //this.mLinearLayout = (LinearLayout)findViewById(R.id.ll);
        //this.mDescription = (TextView)findViewById(R.id.description);
        //this.mBBackToCategory = (ImageButton)findViewById(R.id.back_button_category);

    }
}
