package com.example.smartworkout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class SessionFragment extends Fragment {

    private ListView listView; // moarefie khode list
    private TextView title;
    private String[] listItem; // esme tamrina
    private int [] ax;
    private int Session_Selector;
    private Button buttonStart;
    private Button finishedBTN;
    private EditText editWeight;
    public Chronometer chronometer;
    public Boolean started = false;
    private boolean dialogIs_On = false;
  //  public Boolean finished = false;
    long offset = 0;
    String buffer;

    CustomBaseAdaptor customBaseAdaptor;
    ArrayList<Float> bufferWeight = new ArrayList<Float>();
    ///////////
     public ArrayList<Double> Copy_Clac = new ArrayList<Double>();
     double sum=0;
    ///////////
    private int selector_For_AlertDialog;
    String eyvalla;
    String eyvalla2;


    public SessionFragment() {

    }


    public SessionFragment(int sessionID) {
        this.Session_Selector = sessionID;
    }




// inja esme tamrina o name e session o tarif mikonim

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session, container, false);
        View view2 = inflater.inflate(R.layout.activity_custom_list_view, container, false);

        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        editWeight = (EditText) view2.findViewById(R.id.editTextWeight);

        listView = (ListView) view.findViewById(R.id.list_session);
        title= (TextView)  view.findViewById(R.id.Name);
        finishedBTN = view.findViewById(R.id.finishedBTN);
        finishedBTN.setVisibility(View.INVISIBLE);





        if (savedInstanceState != null) {
            Session_Selector = savedInstanceState.getInt("Number_For_exerName_Pics");
            started = savedInstanceState.getBoolean("Start_Already_Clicked");
            dialogIs_On = savedInstanceState.getBoolean("dialog_is_on");
            sum = savedInstanceState.getDouble("Sum_Points");
            eyvalla = savedInstanceState.getString("Time");
          //  String[] Min_Sec = buffer.split(":");

          //  offset = (long) ((Double.parseDouble(Min_Sec[0]) * 60) + Double.parseDouble(Min_Sec[1]));

      // chronometer.setBase( SystemClock.elapsedRealtime() - offset);  //chronometer.setText
            offset = savedInstanceState.getLong("offset2");


        }


//        if (offset != 0){
//            chronometer.setBase(SystemClock.elapsedRealtime() - offset);
//        }
        List<Exercise> e;
        e=populateExercises();
        switch (Session_Selector){
            case 1:
                listItem = getResources().getStringArray(R.array.sessionArms);
//                e.get(8).Cover;
//                e.get(9).Cover;
//                e.get(10).Cover;
//                e.get(11).Cover;
                ax = new int[]{e.get(8).Cover, e.get(9).Cover,
                        e.get(10).Cover, e.get(11).Cover};
                title.setText("Legs");
                this.selector_For_AlertDialog = 1;
                break;
            case 2:
//                e.get(4).Cover;
//                e.get(5).Cover;
//                e.get(6).Cover;
//                e.get(7).Cover;
                listItem = getResources().getStringArray(R.array.sessionLegs);
                title.setText("Arms");
                ax = new int[]{ e.get(4).Cover,  e.get(5).Cover,
                        e.get(6).Cover, e.get(7).Cover};
                this.selector_For_AlertDialog = 2;
                break;
            case 3:
//                e.get(0).Cover;
//                e.get(1).Cover;
//                e.get(2).Cover;
//                e.get(3).Cover;
                listItem = getResources().getStringArray(R.array.sessionChest);
                title.setText("Chest");
                ax = new int[]{ e.get(0).Cover,  e.get(1).Cover,
                        e.get(2).Cover,  e.get(3).Cover};
                this.selector_For_AlertDialog = 3;
                break;
            default:
                break;
        }

System.out.println("mano negah kon: " + dialogIs_On);

//


        if (sum != 0){
            dialogIs_On = true;
            diaaalog();
        }
        if (dialogIs_On == true) {
            diaaalog();

            chronometer.stop();
       //     Toast.makeText(getActivity(), "umad jati ke bayad biad", Toast.LENGTH_SHORT).show();
            started = false;
            buttonStart.setVisibility(View.INVISIBLE);
            chronometer.setText(eyvalla);
            diaaalog2();


         //   chronometer.setVisibility(View.INVISIBLE);

        }



        CustomBaseAdaptor customBaseAdaptor = new CustomBaseAdaptor(getActivity().getApplicationContext(), listItem, ax );

        CustomBaseAdaptor_2 customBaseAdaptor_2 = new CustomBaseAdaptor_2(getActivity().getApplicationContext(),listItem,ax);




        if (started == false){
        listView.setAdapter(customBaseAdaptor_2);
        }
        else {
            listView.setAdapter(customBaseAdaptor);
            bufferWeight =  customBaseAdaptor.inp_WEIGHTS;
       //     System.out.println("Umade: "+bufferWeight);
            buttonStart.setVisibility(View.INVISIBLE);
            chronometer.setBase(SystemClock.elapsedRealtime() - offset);

            chronometer.start();
            finishedBTN.setVisibility(View.VISIBLE);


        }







        if (started == false){

            buttonStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    started = true;
                    listView.setAdapter(customBaseAdaptor);
                    buttonStart.setVisibility(View.INVISIBLE);
                    chronometer.setBase(SystemClock.elapsedRealtime());  // check
                    chronometer.start();
                    finishedBTN.setVisibility(View.VISIBLE);

                }
            });

        }



        finishedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Reports.class);
                Copy_Clac =  customBaseAdaptor.Calc;
                int i =0;
                sum = 0;

        for ( i = 0; i < Copy_Clac.size(); i++) {
            sum += Copy_Clac.get(i);
          //  System.out.println("majmoo: "+ sum);
        }

                long time = SystemClock.elapsedRealtime()-chronometer.getBase();
                chronometer.stop();
                System.out.println("Nahayatan har Satr: --> "+ Copy_Clac);
                System.out.println("Zamane tamrin:  "+ time/1000);

            if (savedInstanceState == null){
                buffer = chronometer.getText().toString();

            } else {
                buffer = savedInstanceState.getString("Buf");
            }
                dialogIs_On = true;
                diaaalog();
            }
        });


        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Number_For_exerName_Pics",Session_Selector);
        outState.putBoolean("Start_Already_Clicked", started);
        outState.putDouble("Sum_Points", sum);
        outState.putString("Time", chronometer.getText().toString());
        outState.putString("Buf",buffer);
        outState.putBoolean("dialog_is_on", dialogIs_On);
      //  outState.putStringArrayList();
        outState.putLong("offset2", SystemClock.elapsedRealtime() - chronometer.getBase());

    }





    private void diaaalog(){
        Intent intent = new Intent(getActivity(), Reports.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        chronometer.stop();
        // hiiiiii
        builder.setTitle("Save Record?");
        builder.setMessage("Do you want to save this record?\n\n"+ "Workout Duration: "+ chronometer.getText() +"\n Scores Acquired: "+ String.format("%.2f", sum));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogIs_On = false;
                intent.putExtra("ArrayForCalcFromSession", sum);
                intent.putExtra("sessionSelector",Session_Selector);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No, Discard it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogIs_On = false;
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }


        });

        builder.create().show();
    }


    private void diaaalog2(){
        Intent intent = new Intent(getActivity(), Reports.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        chronometer.stop();
        // hiiiiii
        builder.setTitle("Save Record?");
        builder.setMessage("Do you want to save this record?\n\n"+ "Workout Duration: "+ eyvalla +"\n Scores Acquired: "+ String.format("%.2f", sum));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogIs_On = false;
                intent.putExtra("ArrayForCalcFromSession", sum);
                intent.putExtra("sessionSelector",Session_Selector);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No, Discard it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogIs_On = false;
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }


        });

        builder.create().show();
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





