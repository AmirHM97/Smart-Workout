package com.example.smartworkout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class StartFragment extends Fragment {

    private ImageView imageStatBTN;
    private TextView email;
    private TextView name;
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private ImageView imageInfo1;
    private ImageButton imageInfo2;
    private ImageButton imageInfo3;
    private FragmentTransaction fragmentTransaction;





    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);


        email = view.findViewById(R.id.Email);
        name = view.findViewById(R.id.Name);
        email.setText("Artificial@Mussy.com");
        name.setText("Akbar");


        cardView1 = view.findViewById(R.id.Card_Session_Arms);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView_Main, new SessionFragment(1));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        cardView2 = view.findViewById(R.id.Card_Session_Legs);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView_Main, new SessionFragment(2));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        cardView3= view.findViewById(R.id.Card_Session_Chest);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView_Main, new SessionFragment(3));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageStatBTN = view.findViewById(R.id.imageButton_Stat);
        imageStatBTN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Reports.class);
                startActivity(intent);
            }
        });
        imageInfo1 = view.findViewById(R.id.imageInfo1);
        imageInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialogKhan = new AlertDialog.Builder(getActivity()).setTitle("Arms Session Info").setMessage("This...")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setIcon(R.drawable.ic_baseline_fitness_center_24).show();
            }
        });

        imageInfo2 = view.findViewById(R.id.imageinfo2);
        imageInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialogKhan = new AlertDialog.Builder(getActivity()).setTitle("Legs Session Info").setMessage("This...")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setIcon(R.drawable.ic_baseline_fitness_center_24).show();
            }
        });

        imageInfo3 = view.findViewById(R.id.imageinfo3);
        imageInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialogKhan = new AlertDialog.Builder(getActivity()).setTitle("Chest Session Info").setMessage("This...")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setIcon(R.drawable.ic_baseline_fitness_center_24).show();
            }
        });





        return view;
    }

    public void kir(){

    }
}