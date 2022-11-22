package com.example.smartworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper_Legs extends SQLiteOpenHelper {

    private Context context;
    private static final String Database_Name = "PROGRESSTABLELEGS2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String Table_Name_LEG = "progress_record_legs";
    private static final String COLUMN_ID2 = "_id";
    private static final String COLUMN_LEGS = "LEGS_RECORD";


    public MyDataBaseHelper_Legs(@Nullable Context context) {
        super(context, Database_Name, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "CREATE TABLE " + Table_Name_LEG + " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_LEGS + " DOUBLE);";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Name_LEG);
        onCreate(db);

    }

    void Add_Legs_Record(double rec_Legs) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LEGS, rec_Legs);

        long result = db.insert(Table_Name_LEG, null, cv);
        if (result == -1) {
            Toast.makeText(context, "FAILED to INSERT", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "INSERTED!", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor ReadAllData_Legs(){           /// INJA YE METHOD DOROS MIKONIM KE BADAN TU REPORT SEDASH BEZANIM BARAMUN BERE TAMAME TABLE RO BEKHUNE
        String query2 = "SELECT * FROM " + Table_Name_LEG;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor =  db.rawQuery(query2,null);
        }
        return cursor;
    }



}
