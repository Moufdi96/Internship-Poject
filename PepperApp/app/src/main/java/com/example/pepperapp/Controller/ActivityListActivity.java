package com.example.pepperapp.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.pepperapp.R;

    public class ActivityListActivity extends AppCompatActivity {
        private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.mToolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(mToolbar);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment_container,new ListActivityFragment(),"listActivityFragment").commit();
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.activity_fragment_container).getTag() == "creatNewActivityFragment"){
            this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment_container,new ListActivityFragment(),"listActivityFragment").commit();
        } else{
            super.onBackPressed();
        }
    }
}
