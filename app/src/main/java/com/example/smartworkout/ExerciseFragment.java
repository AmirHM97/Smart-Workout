package com.example.smartworkout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ExerciseFragment extends Fragment {
private ArrayList<Exercise> exercises;
private RecyclerView recyclerView;


    public ExerciseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        exercises=new ArrayList<Exercise>();
        View view= inflater.inflate(R.layout.fragment_exercise, container, false);
        recyclerView= view.findViewById(R.id.recyclerView_ExerciseFragment);
        exercises= populateExercises();
        setAdapter();


        return view;

    }
    private  void setAdapter(){
        ExerciseCardAdapter adapter=new ExerciseCardAdapter(exercises, getActivity());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    public ArrayList<Exercise>  populateExercises(){
        ArrayList<Exercise> exercises=new ArrayList<Exercise>();

        //chest
        Exercise e1=new Exercise(1,R.drawable.benchpress,"Bench press","Bench press description");
        Exercise e2=new Exercise(2,R.drawable.incline_benchpress,"Incline Bench press","Incline Bench press description");
        Exercise e3=new Exercise(3,R.drawable.decline_benchpress,"Decline Bench press","Decline Bench press description");
        Exercise e4=new Exercise(4,R.drawable.chest_fly,"Chest Fly","Chest Fly description");
        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);
        //arm
        Exercise e5=new Exercise(5,R.drawable.bicep_curl,"Bicep Curl","Bicep Curl description");
        Exercise e6=new Exercise(6,R.drawable.preacher_curl,"Preacher Curl","Preacher Curl description");
        Exercise e7=new Exercise(7,R.drawable.triceps_extension,"Triceps Extension","Triceps Extension press description");
        Exercise e8=new Exercise(8,R.drawable.dip_machine,"Dip Machine","Dip Machine description");
        exercises.add(e5);
        exercises.add(e6);
        exercises.add(e7);
        exercises.add(e8);
        //leg
        Exercise e9=new Exercise(9,R.drawable.leg_extension,"Leg Extension","Leg Extension description");
        Exercise e10=new Exercise(10,R.drawable.leg_press,"Leg Press","Leg press description");
        Exercise e11=new Exercise(11,R.drawable.dead_lift,"Dead lift","Dead lift description");
        Exercise e12=new Exercise(12,R.drawable.leg_curl,"Leg Curl","Leg Curl description");
        exercises.add(e9);
        exercises.add(e10);
        exercises.add(e11);
        exercises.add(e12);
        return  exercises;
    }
}