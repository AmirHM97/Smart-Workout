package com.example.smartworkout;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
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
        // i=0
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent i= new Intent(this,LoginRegisterActivity.class);
            startActivity(i);
       }

        if (user != null) {

            Toast.makeText(MainActivity.this, user.getEmail(),
                    Toast.LENGTH_SHORT).show();
        }



        Button logout= findViewById(R.id.logout_btn);
        startFragment = new StartFragment();
        exerciseFragment = new ExerciseFragment();
        nearbyFragment = new NearbyFragment();
        mapsFragment = (MapsFragment) new MapsFragment();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i= new Intent(MainActivity.this,LoginRegisterActivity.class);
                startActivity(i);
            }
        });
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