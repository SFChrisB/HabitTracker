package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habittracker.data.StudyHabitContract.StudyHabitEntry;

/**
 * Created by AgiChrisPC on 09/06/2017.
 */

public class StudyHabitDbHelper extends SQLiteOpenHelper {

    //Name of db file
    private static final String DB_NAME = "studyhabits.db";

    //db version. Updates need the DB_VERS to be incremented
    private static final int DB_VERS = 1;

    public StudyHabitDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERS);
    }

    //Called when db is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db){
        //Create a string that contaisn the SQL statement to create the studyhabits table
        String sqlCreateTbl = "CREATE TABLE " + StudyHabitEntry.TBL_NAME + " ("
                + StudyHabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudyHabitEntry.CLN_STUDY_SUBJECT + " TEXT NOT NULL, "
                + StudyHabitEntry.CLN_STUDY_DURATION + " INTEGER NOT NULL, "
                + StudyHabitEntry.CLN_STUDY_DAYOFWEEK + " TEXT NOT NULL DEFAULT 7);";

        //Execute the SQL statement
        db.execSQL(sqlCreateTbl);
    }

    //Called when db needs an upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVers, int newVers) {
        //Still version one for the time being, otherwise will check which version is most current
        db.execSQL("DROP TABLE IF EXISTS " + StudyHabitEntry.TBL_NAME);
        onCreate(db);
    }
}

