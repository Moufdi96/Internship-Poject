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

import com.example.pepperapp.R;
import com.example.pepperapp.model.Category;
import com.example.pepperapp.model.Movement;
import com.example.pepperapp.model.MovementType;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovementListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_list);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container,new ListMovementFragment(),"ListMovementFragment").commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.fragment_list_container).getTag()=="createNewMovementFragment"){
            this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list_container, new ListMovementFragment(),"ListMovementFragment").commit();
        } else{
            super.onBackPressed();
        }
    }

}
