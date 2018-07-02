package com.example.abc.reimbursement.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BillDbHelper extends SQLiteOpenHelper {


        public static final String LOG_TAG = BillDbHelper.class.getSimpleName();

        /** Name of the database file */
        private static final String DATABASE_NAME = "Reimbursement.db";

        /**
         * Database version. If you change the database schema, you must increment the database version.
         */
        private static final int DATABASE_VERSION = 1;

        /**
         * Constructs a new instance of {@link BillDbHelper}.
         *
         * @param context of the app
         */
        public BillDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /**
         * This is called when the database is created for the first time.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create a String that contains the SQL statement to create the pets table
            String SQL_CREATE_EXPENSE_TABLE =  "CREATE TABLE " + BillContract.BillEntry.TABLE_NAME + " ("
                   // + BillContract.BillEntry.COLUMN_EXPENSE_ID + " INTEGER , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_NAME + " TEXT NOT NULL UNIQUE , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE+ " TEXT, "

                    + BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE+ " TEXT, "
                    + BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE+ " DATE , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME+ " TEXT , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME+ "TEXT ,"
                     + BillContract.BillEntry.COLUMN_EXPENSE_MEMBERS+ "TEXT ,"
                    + BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE+ " TEXT,"
                + BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT +" REAL, "
                        + BillContract.BillEntry.COLUMN_EXPENSE_CAT+ " TEXT,"
                    +BillContract.BillEntry.COLUMN_EXPENSE_BILL_ID+ " INTEGER  PRIMARY KEY   AUTOINCREMENT , "

                    + BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT+ " TEXT)";


            // Execute the SQL statement
            db.execSQL(SQL_CREATE_EXPENSE_TABLE);
        }

        /**
         * This is called when the database needs to be upgraded.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // The database is still at version 1, so there's nothing to do be done here.
        }
    }
