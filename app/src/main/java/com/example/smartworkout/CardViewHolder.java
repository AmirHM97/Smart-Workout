package com.example.smartworkout;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CardViewHolder extends RecyclerView.ViewHolder {
    private final TextView exerciseName;
    private final ImageView exerciseImage;
    private final CardView cardView;
     public CardViewHolder(@NonNull View itemView) {
        super(itemView);
        exerciseName=itemView.findViewById(R.id.exercise_cv_textview);
        exerciseImage=itemView.findViewById(R.id.exercise_cv_imageview);
        cardView= itemView.findViewById(R.id.exercise_cv);
    }

    public TextView getExerciseName() {
        return exerciseName;
    }

    public ImageView getExerciseImage() {
        return exerciseImage;
    }

    public CardView getCardView() {
        return cardView;
    }
}
