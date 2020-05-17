package com.example.pepperapp.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pepperapp.R;
import com.example.pepperapp.model.JsonParseRobotList;
import com.example.pepperapp.model.Robot;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private androidx.appcompat.widget.Toolbar mToolbar;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        Robot r = new Robot("","","",false);
        if(loadRobotPreference()!= null){
            r = loadRobotPreference();
        }
        if (r.getmConnectionStatus()) {
            mNavigationView.getMenu().getItem(1).setTitle("Connected to " + loadRobotPreference().getmRobotName());
        } else {
            mNavigationView.getMenu().getItem(1).setTitle("Connect to a Robot");
        }
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_container).getTag() == "newRobotFragment") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConnectToRobotFragment()).commit();
        } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_container).getTag() == "EditRobotFragment") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConnectToRobotFragment(), "ConnectToRobotFragment").commit();
        }/*else{
            finish();
            System.exit(0);
        }*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.homepage:
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.add_robot:
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConnectToRobotFragment(), "ConnectToRobotFragment").commit();
                break;
            case R.id.settings:
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
            case R.id.help:
                this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public Robot loadRobotPreference() {
        Gson gson = new Gson();
        SharedPreferences mSharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        String json = mSharedPreferences.getString("lastSelectedRobot", "");
        Robot robot = gson.fromJson(json, Robot.class);
        return robot;
    }

    public void saveRobotPreference(Robot robot) {
        Gson gson = new Gson();
        SharedPreferences mSharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("lastSelectedRobot", gson.toJson(robot)).commit();
    }

    /*@Override
    protected void onStop() {
        JsonParseRobotList j = new JsonParseRobotList(this);
        Robot r = loadRobotPreference();
        r.setmConnectionStatus(false);
        saveRobotPreference(r);
        for (Robot ro : j.getmRobotList()) {
            if (r.getmConnectionStatus()) {
                r.setmConnectionStatus(false);
            }
        }
        j.writeToJsonFile(j.javaObjectToJson());
        super.onStop();
    }*/


}
