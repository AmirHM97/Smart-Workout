package com.example.smartworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExerciseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        Bundle extras = getIntent().getExtras();
        int userName;
        userName = extras.getInt("Id");
//        if (extras != null) {
//            userName = extras.getInt("Id");}

    }
}