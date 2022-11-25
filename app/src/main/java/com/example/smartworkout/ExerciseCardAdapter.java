package com.example.smartworkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExerciseCardAdapter extends RecyclerView.Adapter<CardViewHolder> {
    private ArrayList<Exercise> Exercises;
    private Activity context;
    public ExerciseCardAdapter(ArrayList<Exercise> exercises, Activity Context) {
        Exercises = exercises;
        context = Context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.excercise_cardview, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String name=Exercises.get(position).Name;
        int image=Exercises.get(position).Cover;
        int p=holder.getAdapterPosition();
        holder.getExerciseName().setText(name);
        holder.getExerciseImage().setImageResource(image);
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,ExerciseDetailActivity.class);
                i.putExtra("Id",Exercises.get(p).Id);
                i.putExtra("Name",Exercises.get(p).Name);
                i.putExtra("Cover",Exercises.get(p).Cover);
                i.putExtra("Description",Exercises.get(p).Description);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Exercises.size();
    }
}
