package com.example.smartworkout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class SessionFragment extends Fragment {

    private ListView listView; // moarefie khode list
    private TextView textView;
    private TextView title;
    private String[] listItem; // esme tamrina
    private int [] ax;
    private int Session_Selector;
    private Button buttonStart;
    private Button finishedBTN;
    private EditText editWeight;
    public Chronometer chronometer;
    public Boolean startOrNot = false;
    ///////////
     public ArrayList<Double> Copy_Clac = new ArrayList<Double>();
     double sum=0;
    ///////////
private int selector_For_AlertDialog;


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
        View view3 = inflater.inflate(R.layout.mylist, container, false);



        listView = (ListView) view.findViewById(R.id.list_session);
        textView= (TextView) view3.findViewById(R.id.textView);
        title= (TextView)  view.findViewById(R.id.Name);
        finishedBTN = view.findViewById(R.id.finishedBTN);
        finishedBTN.setVisibility(View.INVISIBLE);

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

//

        CustomBaseAdaptor customBaseAdaptor = new CustomBaseAdaptor(getActivity().getApplicationContext(), listItem, ax );

        CustomBaseAdaptor_2 customBaseAdaptor_2 = new CustomBaseAdaptor_2(getActivity().getApplicationContext(),listItem,ax);
        listView.setAdapter(customBaseAdaptor_2);


        editWeight = (EditText) view2.findViewById(R.id.editTextWeight);
        editWeight.setVisibility(View.INVISIBLE); // not working
        ArrayList<Float> Weights = new ArrayList<Float>();


        // hala baraye timer
       chronometer = (Chronometer) view.findViewById(R.id.chronometer);
       chronometer.setBase(SystemClock.elapsedRealtime());


               buttonStart = (Button) view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(customBaseAdaptor);
                buttonStart.setVisibility(View.INVISIBLE);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                finishedBTN.setVisibility(View.VISIBLE);

            }
        });

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

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Save Record?");
                builder.setMessage("Do you want to save this record?\n\n"+ "Workout Duration: "+chronometer.getText() +"\n Scores Acquired: "+ String.format("%.2f", sum));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent.putExtra("ArrayForCalcFromSession", sum);
                        intent.putExtra("sessionSelector",Session_Selector);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No, Discard it!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);

                        }


                });

                builder.create().show();
            }
        });


        return view;
    }



}


