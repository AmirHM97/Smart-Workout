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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


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
          //  System.out.println(Min_Sec +" KIR");
          //  offset = (long) ((Double.parseDouble(Min_Sec[0]) * 60) + Double.parseDouble(Min_Sec[1]));
          //  System.out.println("offset: Kir --> "+ offset);
      // chronometer.setBase( SystemClock.elapsedRealtime() - offset);  //chronometer.setText
            offset = savedInstanceState.getLong("offset2");


        }


//        if (offset != 0){
//            chronometer.setBase(SystemClock.elapsedRealtime() - offset);
//        }

        switch (Session_Selector){
            case 1:
                listItem = getResources().getStringArray(R.array.sessionArms);
                ax = new int[]{R.drawable.ic_baseline_fitness_center_24, R.drawable.ic_baseline_info_24,
                        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_timer_24,R.drawable.ic_baseline_pin_drop_24};
                title.setText("Arms");
                this.selector_For_AlertDialog = 1;
                break;
            case 2:
                listItem = getResources().getStringArray(R.array.sessionLegs);
                title.setText("Legs");
                ax = new int[]{R.drawable.ic_baseline_fitness_center_24, R.drawable.ic_baseline_info_24,
                        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_timer_24,R.drawable.ic_baseline_pin_drop_24};
                this.selector_For_AlertDialog = 2;
                break;
            case 3:
                listItem = getResources().getStringArray(R.array.sessionChest);
                title.setText("Chest");
                ax = new int[]{R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_info_24,
                        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_timer_24,R.drawable.ic_baseline_pin_drop_24};
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
            Toast.makeText(getActivity(), "umad jati ke bayad biad", Toast.LENGTH_SHORT).show();
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
       //     System.out.println("OFFSET ke KIRKOS"+ offset);
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
                System.out.println("kiriBuf "+ buffer);
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

}





