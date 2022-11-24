package com.example.smartworkout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ExerciseFragment extends Fragment {



    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }
    public ArrayList<Exercise>  populateExercises(){
        ArrayList<Exercise> exercises=new ArrayList<Exercise>();

        //chest
        Exercise e1=new Exercise(R.drawable.benchpress,"Bench press","Bench press description");
        Exercise e2=new Exercise(R.drawable.incline_benchpress,"Incline Bench press","Incline Bench press description");
        Exercise e3=new Exercise(R.drawable.decline_benchpress,"Decline Bench press","Decline Bench press description");
        Exercise e4=new Exercise(R.drawable.chest_fly,"Chest Fly","Chest Fly description");
        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);
        //arm
        Exercise e5=new Exercise(R.drawable.bicep_curl,"Bicep Curl","Bicep Curl description");
        Exercise e6=new Exercise(R.drawable.preacher_curl,"Preacher Curl","Preacher Curl description");
        Exercise e7=new Exercise(R.drawable.triceps_extension,"Triceps Extension","Triceps Extension press description");
        Exercise e8=new Exercise(R.drawable.dip_machine,"Dip Machine","Dip Machine description");
        exercises.add(e5);
        exercises.add(e6);
        exercises.add(e7);
        exercises.add(e8);
        //leg
        Exercise e9=new Exercise(R.drawable.leg_extension,"Leg Extension","Leg Extension description");
        Exercise e10=new Exercise(R.drawable.leg_press,"Leg Press","Leg press description");
        Exercise e11=new Exercise(R.drawable.dead_lift,"Dead lift","Dead lift description");
        Exercise e12=new Exercise(R.drawable.leg_curl,"Leg Curl","Leg Curl description");
        exercises.add(e9);
        exercises.add(e10);
        exercises.add(e11);
        exercises.add(e12);
        return  exercises;
    }
}