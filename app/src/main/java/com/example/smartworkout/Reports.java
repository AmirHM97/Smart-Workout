package com.example.smartworkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {

private ProgressBar circular_progress_arms,circular_progress_legs,circular_progress_chest;
private TextView status1,status2,status3;
private double progressStat1 = 0;
private double progressStat2 = 0;
private double progressStat3 = 0;
private double majmoo;
private ImageView imageView;
private Intent intent;
private MyDatabaseHelper myDatabaseHelper;
private  MyDataBaseHelper_Legs myDataBaseHelper_legs;
private MyDataBaseHelper_Chest myDataBaseHelper_chest;
ArrayList<Double> Arms_Saved,Legs_Saved,Chest_Saved;
ArrayList<Integer> Arms_ID,Legs_ID2,Chest_ID;
ArrayList<Double> All_Sessions;
private int Controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        imageView = (ImageView) findViewById(R.id.imageViewHome);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousActivity();
            }
        });

        Arms_Saved = new ArrayList<>();
        Arms_ID = new ArrayList<>();

        Legs_Saved = new ArrayList<>();
        Legs_ID2 = new ArrayList<>();

        Chest_Saved = new ArrayList<>();
        Chest_ID = new ArrayList<>();



        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        circular_progress_arms = (ProgressBar) findViewById(R.id.progressBarArms);
        status1 = (TextView) findViewById(R.id.textStatus1);

        circular_progress_legs = (ProgressBar) findViewById(R.id.progressBarLegs);
        status2 = (TextView) findViewById(R.id.textStatus2);

        circular_progress_chest = (ProgressBar) findViewById(R.id.progressBarCHEST);
        status3 = (TextView) findViewById(R.id.textStatus3);

        if (b != null) {
            majmoo = (Double) b.get("ArrayForCalcFromSession");
            System.out.println("Adado az session gereftam! rikhtam tu majmoo baraye mohasebeye progress: -->" + majmoo);
            this.Controller = (int) b.get("sessionSelector");

            switch (Controller) {  // inja bayad az session ye vorudi begirim berizim tu controller ta tain kone
                case 1:
                    MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(Reports.this);
                    myDatabaseHelper.Add_Arms_Record(majmoo);  /// INJA DATA RO BE DB EZAFE MIKONIM
                    System.out.println("umad too arms be dorosti. qable rikhtan tu araye");


                    break;
                case 2:
                    MyDataBaseHelper_Legs myDataBaseHelper_legs = new MyDataBaseHelper_Legs(Reports.this);
                    myDataBaseHelper_legs.Add_Legs_Record(majmoo);
                    System.out.println("_________controller o Legs gerefte va dare mire ke araye ro por kone_______");

                    break;
                case 3:
                    MyDataBaseHelper_Chest myDataBaseHelper_chest = new MyDataBaseHelper_Chest(Reports.this);
                    myDataBaseHelper_chest.Add_Chest_Record(majmoo);
                    System.out.println("_________controller o Chest gerefte va dare mire ke araye ro por kone_______");
                    break;
                default:
                    break;
            }

        }


        //   System.out.println("uni ke mikhay alan: --->" + majmoo);



//        circular_progress_chest = (ProgressBar) findViewById(R.id.progressBarCHEST);
//        status3 = (TextView) findViewById(R.id.textStatus3);



        Storing_Data_FromDB_ToArrays_Arms();
        System.out.println("bade rikhtan tu arraye");
        for (int i = 0; i < Arms_Saved.size(); i++) {  // MEMBERS E ARRAY RO BAHAM JAM MIKONIM VA VASE DARSAD TAQSIM MIKONIM
            progressStat1 += ((Arms_Saved.get(i)) / 1000);
        }
        circular_progress_arms.setProgress((int) progressStat1);
        status1.setText(Double.toString(Double.parseDouble(String.format("%.2f", progressStat1))) + "%");

        /////////////////////////// LEGS

        Storing_Data_FromDB_ToArrays_Legs();
        for (int i = 0; i < Legs_Saved.size(); i++) {
            progressStat2 += ((Legs_Saved.get(i)) / 1000);
        }   // MEMBERS E ARRAY RO BAHAM JAM MIKONIM VA VASE DARSAD TAQSIM MIKONIM
        circular_progress_legs.setProgress((int) progressStat2);
        status2.setText(Double.toString(Double.parseDouble(String.format("%.2f", progressStat2))) + "%");

        ///////////////////////////// CHEST

        Storing_Data_FromDB_ToArrays_Chest();
        for (int i = 0; i < Chest_Saved.size(); i++) {
            progressStat3 += ((Chest_Saved.get(i)) / 1000);
        }   // MEMBERS E ARRAY RO BAHAM JAM MIKONIM VA VASE DARSAD TAQSIM MIKONIM
        circular_progress_chest.setProgress((int) progressStat3);
        status3.setText(Double.toString(Double.parseDouble(String.format("%.2f", progressStat3))) + "%");

        // DATABASE RETRIEVAL
        myDatabaseHelper = new MyDatabaseHelper(Reports.this);
        myDataBaseHelper_legs = new MyDataBaseHelper_Legs(Reports.this);
        myDataBaseHelper_chest = new MyDataBaseHelper_Chest(Reports.this);

      //  System.out.println("Progress bad az jam ba jadid az DB: --> " + progressStat1);



    }




    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble("SavedProgress",progressStat1);

    }



    public void goToPreviousActivity(){
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    void Storing_Data_FromDB_ToArrays_Arms(){   /// READALLDATA RO SEDA MIZANE KE RETURNESH AZ JENSE CURSOR E VA MIRIZE TU YE MOTEQAYERE JADID BE ESME CURSOR
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(Reports.this);
        Cursor cursor = (Cursor) myDatabaseHelper.ReadAllData_Arms();///  MIGE AGE KHALI BUD KE TOAST. AGAR NA, KHAT BE KHAT BEKHUN VA DAR ARRAY HAYE TARID SHODE ADD KON
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                Arms_ID.add(cursor.getInt(0));
                Arms_Saved.add(cursor.getDouble(1));
            }
        }
    }


    void Storing_Data_FromDB_ToArrays_Legs(){   /// READALLDATA RO SEDA MIZANE KE RETURNESH AZ JENSE CURSOR E VA MIRIZE TU YE MOTEQAYERE JADID BE ESME CURSOR
       MyDataBaseHelper_Legs myDataBaseHelper_legs = new MyDataBaseHelper_Legs(Reports.this);
        Cursor cursor2 = (Cursor) myDataBaseHelper_legs.ReadAllData_Legs();///  MIGE AGE KHALI BUD KE TOAST. AGAR NA, KHAT BE KHAT BEKHUN VA DAR ARRAY HAYE TARID SHODE ADD KON
        if (cursor2.getCount() == 0) {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor2.moveToNext()){
                Legs_ID2.add(cursor2.getInt(0));
                Legs_Saved.add(cursor2.getDouble(1));
            }
        }
    }



    void Storing_Data_FromDB_ToArrays_Chest(){   /// READALLDATA RO SEDA MIZANE KE RETURNESH AZ JENSE CURSOR E VA MIRIZE TU YE MOTEQAYERE JADID BE ESME CURSOR
        MyDataBaseHelper_Chest myDataBaseHelper_chest = new MyDataBaseHelper_Chest(Reports.this);
        Cursor cursor2 = (Cursor) myDataBaseHelper_chest.ReadAllData_Chest();///  MIGE AGE KHALI BUD KE TOAST. AGAR NA, KHAT BE KHAT BEKHUN VA DAR ARRAY HAYE TARID SHODE ADD KON
        if (cursor2.getCount() == 0) {
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor2.moveToNext()){
                Chest_ID.add(cursor2.getInt(0));
                Chest_Saved.add(cursor2.getDouble(1));
            }
        }
    }


//    void Storing_Data_FromDB_ToArrays_Chest(){   /// READALLDATA RO SEDA MIZANE KE RETURNESH AZ JENSE CURSOR E VA MIRIZE TU YE MOTEQAYERE JADID BE ESME CURSOR
//        Cursor cursor = myDatabaseHelper.ReadAllData_Chest();///  MIGE AGE KHALI BUD KE TOAST. AGAR NA, KHAT BE KHAT BEKHUN VA DAR ARRAY HAYE TARID SHODE ADD KON
//        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()){
//                Chest_ID.add(cursor.getInt(0));
//                Chest_Saved.add(cursor.getDouble(1));
//            }
//        }
    }




    ///