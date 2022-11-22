package com.example.smartworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper_Chest extends SQLiteOpenHelper {

    private Context context;
    private static final String Database_Name = "PROGRESSTABLECHEST3.db";
    private static final int DATABASE_VERSION = 1;

    private static final String Table_Name_CHEST = "progress_record_chest";
    private static final String COLUMN_ID3 = "_id";
    private static final String COLUMN_CHEST = "Chest_RECORD";


    public MyDataBaseHelper_Chest(@Nullable Context context) {
        super(context, Database_Name, null, DATABASE_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query3 = "CREATE TABLE "+ Table_Name_CHEST + " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CHEST + " DOUBLE);";
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name_CHEST);
        onCreate(db);

    }


    void Add_Chest_Record(double rec_Chest){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CHEST,rec_Chest);

        long result = db.insert(Table_Name_CHEST,null,cv);
        if (result == -1) {
            Toast.makeText(context, "FAILED to INSERT", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "INSERTED!", Toast.LENGTH_SHORT).show();
        }

    }


    Cursor ReadAllData_Chest(){           /// INJA YE METHOD DOROS MIKONIM KE BADAN TU REPORT SEDASH BEZANIM BARAMUN BERE TAMAME TABLE RO BEKHUNE
        String query = "SELECT * FROM " + Table_Name_CHEST;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor =  db.rawQuery(query,null);
        }
        return cursor;
    }


}
