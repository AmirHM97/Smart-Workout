package com.example.smartworkout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    private Button weightUpBTN;
    private Button weightDownBTN;


    ///////////// VARS FOR REPORTS CALCULATION
    private int [] array_weights;


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
                break;
            case 2:
                listItem = getResources().getStringArray(R.array.sessionLegs);
                title.setText("Legs");
                ax = new int[]{R.drawable.ic_baseline_fitness_center_24, R.drawable.ic_baseline_info_24,
                        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_timer_24,R.drawable.ic_baseline_pin_drop_24};
                break;
            case 3:
                listItem = getResources().getStringArray(R.array.sessionChest);
                title.setText("Chest");
                ax = new int[]{R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_info_24,
                        R.drawable.ic_baseline_bar_chart_24, R.drawable.ic_baseline_timer_24,R.drawable.ic_baseline_pin_drop_24};
                break;
            default:
                break;
        }

//

        CustomBaseAdaptor customBaseAdaptor = new CustomBaseAdaptor(getActivity().getApplicationContext(), listItem, ax );
        listView.setAdapter(customBaseAdaptor);


        // BAKHSHE SET X REPS
        TextView setsXreps = view2.findViewById(R.id.Num_Rep_TXT);

        // BAKHSHE ADADIE WEIGHT

        editWeight = (EditText) view2.findViewById(R.id.editTextWeight);
        editWeight.setVisibility(View.INVISIBLE); // not working
        ArrayList<Float> Weights = new ArrayList<Float>();

        // ezafe kardane adadaye varede be list jahate mohasebat
        Weights.add(Float.parseFloat(editWeight.getText().toString()));


        // hala baraye timer
        Chronometer chronometer;
        Boolean startOrNot;



        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonStart.setVisibility(View.INVISIBLE);
                finishedBTN.setVisibility(View.VISIBLE);
            }
        });

        finishedBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Reports.class);
                startActivity(intent);
            }
        });





        return view;
    }

}