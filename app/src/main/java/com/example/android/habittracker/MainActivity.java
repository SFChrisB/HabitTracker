package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.habittracker.data.StudyHabitContract.StudyHabitEntry;
import com.example.android.habittracker.data.StudyHabitDbHelper;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    //Would be for Initialising the Spinner dropdown option for 'duration'
    private Spinner mDayOfWeekSpinner;


    //Database helper that will provide us access to the database
    private StudyHabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create the db helper
        mDbHelper = new StudyHabitDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        //This shows the data on the app
        String[] projection = {
                StudyHabitEntry._ID,
                StudyHabitEntry.CLN_STUDY_SUBJECT,
                StudyHabitEntry.CLN_STUDY_DURATION,
                StudyHabitEntry.CLN_STUDY_DAYOFWEEK
        };

        Cursor cursor = readStudyData(projection);
        cursor.close();
    }

    public Cursor readStudyData(String[] projection) {

        //Create and open database to read from it.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Query of the studyhabits table
        Cursor cursor = db.query(
                StudyHabitEntry.TBL_NAME,    //The table to query
                projection,     // The columns to return
                null,           //The columns fro the WHERE clause
                null,           //The values for the WHERE clause
                null,           //Dont group the rows
                null,           //Don't filter by row groups
                null            // The sort order
        );

        TextView displayView = (TextView) findViewById(R.id.testXML);

        try {
            //Figure out index of each column
            int idClnIndex = cursor.getColumnIndex(StudyHabitEntry._ID);
            int subjectClnIndex = cursor.getColumnIndex(StudyHabitEntry.CLN_STUDY_SUBJECT);
            int durationClnIndex = cursor.getColumnIndex(StudyHabitEntry.CLN_STUDY_DURATION);
            int dayOfWeekClnIndex = cursor.getColumnIndex(StudyHabitEntry.CLN_STUDY_DAYOFWEEK);

            //Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                //Use that index to extract the String or Int of the word
                //at the current row the cursor is on
                int crtID = cursor.getInt(idClnIndex);
                String crtSubject = cursor.getString(subjectClnIndex);
                int crtDuration = cursor.getInt(durationClnIndex);
                String crtDayOfWeek = cursor.getString(dayOfWeekClnIndex);

                //Display the values of each column of the current row in the cursor in the TextView
                displayView.setText(R.string.StudyInfo);
                displayView.append(("\n" + crtID + " - " +
                        crtSubject + " - " +
                        crtDuration + " - " +
                        crtDayOfWeek));
            }
        } finally {
            return cursor;
        }
    }

    private void insertNewEntry() {
        String subject = "Subject written here";
        //Duration in hours collected and converted to Int value
        String durationString = "2";
        int duration = Integer.parseInt(durationString);
        int mDayOfWeek = StudyHabitEntry.CHOOSEDAY;


        //get the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Create ContentValues object where column names are the keys
        ContentValues newValues = new ContentValues();
        newValues.put(StudyHabitEntry.CLN_STUDY_SUBJECT, subject);
        newValues.put(StudyHabitEntry.CLN_STUDY_DURATION, duration);
        newValues.put(StudyHabitEntry.CLN_STUDY_DAYOFWEEK, mDayOfWeek);

        //Insert new row for the user inputted data into the db
        long newRowId = db.insert(StudyHabitEntry.TBL_NAME, null, newValues);
    }

}