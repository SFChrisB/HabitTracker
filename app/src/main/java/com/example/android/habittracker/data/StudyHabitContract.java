package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by AgiChrisPC on 09/06/2017.
 */

public final class StudyHabitContract {

    private StudyHabitContract(){}

    public static final class StudyHabitEntry implements BaseColumns {

        //The table name
        public final static String TBL_NAME = "studyhabits";

        //The different columns within the studyhabits table
        public final static String _ID = BaseColumns._ID;
        public final static String CLN_STUDY_SUBJECT = "subject";
        public final static String CLN_STUDY_DURATION = "duration";
        public final static String CLN_STUDY_DAYOFWEEK = "day_of_week";

        //The days of the week would come up on a spinner menu and the following
        //would be the selections
        public static final int MONDAY = 0;
        public static final int TUESDAY = 1;
        public static final int WEDNESDAY = 2;
        public static final int THURSDAY = 3;
        public static final int FRIDAY = 4;
        public static final int SATURDAY = 5;
        public static final int SUNDAY = 6;
        public static final int CHOOSEDAY = 7;
    }
}

