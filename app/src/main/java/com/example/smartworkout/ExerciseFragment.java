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
        Exercise e1=new Exercise(1,R.drawable.benchpress,"Bench press","The bench press is a compound exercise that targets the muscles of the upper body. It involves lying on a bench and pressing weight upward using either a barbell or a pair of dumbbells. During a bench press, you lower the weight down to chest level and then press upwards while extending your arms");
        Exercise e2=new Exercise(2,R.drawable.incline_benchpress,"Incline Bench press","The purpose of the incline press is to focus more of the work on the upper pecs. The main benefit in performing incline presses is to develop the upper portion of the pectoral muscles. When the bench is set at an incline (15 to 30 degrees), you activate your shoulders more since it's comparable to a shoulder press.");
        Exercise e3=new Exercise(3,R.drawable.decline_benchpress,"Decline Bench press","In a decline bench press, the bench is set to 15 to 30 degrees on a decline. This angle places your upper body on a downward slope, which activates the lower pectoral muscles as you push weights away from your body. When part of a complete chest routine, decline bench presses can help your pecs look more defined.");
        Exercise e4=new Exercise(4,R.drawable.chest_fly,"Chest Fly","Bring your arms up straight in front of you so they're at chest level, palms facing each other. Extend arms out to the sides, until your arms are extended. Keep arms at chest level the entire time. Bring them back to center. Repeat 10–15 times");
        exercises.add(e1);
        exercises.add(e2);
        exercises.add(e3);
        exercises.add(e4);
        //arm
        Exercise e5=new Exercise(5,R.drawable.bicep_curl,"Bicep Curl","an exercise in which you bend your arm towards your body at the elbow in order to make your biceps (= the large muscle at the front of the upper arm) stronger: Do three sets of biceps curls using 5- to 8-pound weights.");
        Exercise e6=new Exercise(6,R.drawable.preacher_curl,"Preacher Curl"," a weightlifting exercise for the biceps in which a barbell is lifted by flexing the elbows, with the upper arms resting on an angled bench");
        Exercise e7=new Exercise(7,R.drawable.triceps_extension,"Triceps Extension","Stand with feet hip width apart with a slight bend in the knees. Grip the bar with palms facing downwards, about shoulder width apart. Keeping your elbows close to your body, take a slight forward lean, and exhale as you push the bar down till your arms full extended.");
        Exercise e8=new Exercise(8,R.drawable.dip_machine,"Dip Machine","The dip machine is a machine exercise that mimics a triceps dip, a bodyweight exercise performed on parallel bars or on a pull-up and dip station. It targets the triceps first, but also stretches and strengthens the chest and shoulders.");
        exercises.add(e5);
        exercises.add(e6);
        exercises.add(e7);
        exercises.add(e8);
        //leg
        Exercise e9=new Exercise(9,R.drawable.leg_extension,"Leg Extension","Leg extensions are done on a leg extension machine. You sit on the machine with a weighted pad on top of your lower legs. Then you use your quads to repeatedly extend your knees and lift your lower legs. While the leg extension is a great quad workout, it might not be the most practical move");
        Exercise e10=new Exercise(10,R.drawable.leg_press,"Leg Press","Leg presses are done in a seated position. Your legs repeatedly press against weights, which can be adjusted according to your fitness level. This targets your quads, glutes, hamstrings, hips, and calves. The seated position of leg presses helps keep your upper body and torso still");
        Exercise e11=new Exercise(11,R.drawable.dead_lift,"Dead lift","The deadlift is a movement in which your hips hinge backward to lower down and pick up a weighted barbell or kettlebell from the floor. Your back is flat throughout the movement. Some benefits of performing deadlifts include strengthening and gaining more definition in your upper and lower back, glutes, and hamstrings");
        Exercise e12=new Exercise(12,R.drawable.leg_curl,"Leg Curl","The hamstring curl, also called a leg curl, is an exercise that strengthens the hamstrings. It involves bending your knees and moving your heels toward your butt while the rest of your body stays still. Typically, the exercise is done on a leg curl machine");
        exercises.add(e9);
        exercises.add(e10);
        exercises.add(e11);
        exercises.add(e12);
        return  exercises;
    }
}