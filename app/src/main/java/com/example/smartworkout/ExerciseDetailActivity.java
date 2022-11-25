package com.example.smartworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ExerciseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        Bundle extras = getIntent().getExtras();
        ImageView imageView=findViewById(R.id.exercise_detail_imageview);
        TextView name=findViewById(R.id.exercise_detail_name_textview);
        TextView desc=findViewById(R.id.exercise_detail_description_textview);
        int itemId;
        String itemDescription;
        String itemName;
        int itemCover;
        itemId = extras.getInt("Id");
        itemName = extras.getString("Name");
        itemCover = extras.getInt("Cover");
        itemDescription = extras.getString("Description");
        imageView.setImageResource(itemCover);
        name.setText(itemName);
        desc.setText(itemDescription);
    }
}