package com.example.smartworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String Database_Name = "PROGRESSTABLE.db";
    private static final int DATABASE_VERSION = 1;

    private static final String Table_Name_ARM = "progress_record_arms";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ARMS = "ARMS_RECORD";





    public MyDatabaseHelper(@Nullable Context context) {
        super(context, Database_Name, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ Table_Name_ARM + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ARMS + " DOUBLE);";
        db.execSQL(query);


//


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name_ARM);
        onCreate(db);


//

    }

    void Add_Arms_Record(double rec_arm){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ARMS,rec_arm);

        long result = db.insert(Table_Name_ARM,null,cv);
        if (result == -1) {
            Toast.makeText(context, "FAILED to INSERT", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "INSERTED!", Toast.LENGTH_SHORT).show();
        }

    }


//    }
//

Cursor ReadAllData_Arms(){           /// INJA YE METHOD DOROS MIKONIM KE BADAN TU REPORT SEDASH BEZANIM BARAMUN BERE TAMAME TABLE RO BEKHUNE
        String query = "SELECT * FROM " + Table_Name_ARM;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
    if (db != null) {
       cursor =  db.rawQuery(query,null);
    }
 return cursor;
}

void Delete_Last_One(String rowID){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(Table_Name_ARM, "_id=?", new String[]{rowID});
    if (result == -1) {
        Toast.makeText(context, "FAILED to Delete", Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
    }

}



}
