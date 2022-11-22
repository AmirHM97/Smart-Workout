package com.example.smartworkout;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    StartFragment startFragment;
    ExerciseFragment exerciseFragment;
    NearbyFragment nearbyFragment;
    MapsFragment mapsFragment;
    SessionFragment sessionFragment;
    Chronometer chronometer;
    Boolean timer_Running;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        startFragment = new StartFragment();
        exerciseFragment = new ExerciseFragment();
        nearbyFragment = new NearbyFragment();
        mapsFragment = (MapsFragment) new MapsFragment();

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
                                 .replace(R.id.fragmentContainerView_Main,mapsFragment).commit();
                         return true;
                 }
                 return false;
            }
        });

        SessionFragment fragment = (SessionFragment) getSupportFragmentManager().findFragmentById(R.id.session_fragment);

    }

    public void StartChrono(View v){
        if (timer_Running == false) {
            //    chronometer.setBase(SystemClock.currentThreadTimeMillis());
            chronometer.start();
            timer_Running = true;
        }
    }

    public void StopChrono(View view){
        if (timer_Running == true) {
            chronometer.start();
            timer_Running = false;
        }
    }
}