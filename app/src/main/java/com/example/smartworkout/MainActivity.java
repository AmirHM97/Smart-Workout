package com.example.smartworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    StartFragment startFragment;
    ExerciseFragment exerciseFragment;
    NearbyFragment nearbyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent login = new Intent(MainActivity.this,LoginRegisterActivity.class);
        startActivity(login);
        startFragment = new StartFragment();
        exerciseFragment = new ExerciseFragment();
        nearbyFragment = new NearbyFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragmentContainerView_Main,exerciseFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                 switch (item.getItemId()){
                     case R.id.btn_Start:
                         getSupportFragmentManager().beginTransaction()
                                 .replace(R.id.fragmentContainerView_Main,startFragment).commit();
                         return true;
                     case R.id.btn_Exercise:
                         getSupportFragmentManager().beginTransaction()
                                 .replace(R.id.fragmentContainerView_Main,exerciseFragment).commit();
                         return true;
                     case R.id.btn_Nearby:
                         getSupportFragmentManager().beginTransaction()
                                 .replace(R.id.fragmentContainerView_Main,nearbyFragment).commit();
                         return true;
                 }
                 return false;
            }
        });


    }
}