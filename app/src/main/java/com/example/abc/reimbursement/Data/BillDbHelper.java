package com.example.abc.reimbursement.Data;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.abc.reimbursement.Data.BillContract.BillEntry.COLUMN_EXPENSE_NAME;

public class BillDbHelper extends SQLiteOpenHelper {


        public static final String LOG_TAG = BillDbHelper.class.getSimpleName();

        /** Name of the database file */
        private static final String DATABASE_NAME = "Reimbursement.db";

        private static final String TAG = BillDbHelper.class.getName();

        /**
         * Database version. If you change the database schema, you must increment the database version.
         */
        private static final int DATABASE_VERSION = 1;


        String SQL_CREATE_EXPENSE_TABLE;
        private final Context context;

        /**
         * Constructs a new instance of {@link BillDbHelper}.
         *
         * @param context of the app
         */
        public BillDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        /**
         * This is called when the database is created for the first time.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create a String that contains the SQL statement to create the pets table
             SQL_CREATE_EXPENSE_TABLE =  "CREATE TABLE " + BillContract.BillEntry.TABLE_NAME + " ("
                   // + BillContract.BillEntry.COLUMN_EXPENSE_ID + " INTEGER , "
                    + COLUMN_EXPENSE_NAME + " TEXT NOT NULL  ,"
                    + BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE+ " TEXT, "

                    + BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE+ " TEXT , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE+ " TEXT, "
                    + BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME+ " TEXT , "
                    + BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME+ " TEXT ,"
                     + BillContract.BillEntry.COLUMN_EXPENSE_MEMBERS+ " TEXT ,"
                    + BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE+ " TEXT,"
                + BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT +" REAL, "
                        + BillContract.BillEntry.COLUMN_EXPENSE_CAT+ " TEXT DEFAULT 'NoCategory',"
                    //+BillContract.BillEntry.COLUMN_EXPENSE_BILL_ID+ " INTEGER  PRIMARY KEY   AUTOINCREMENT , "

                    +BillContract.BillEntry._ID+ " INTEGER  PRIMARY KEY   AUTOINCREMENT , "


                    + BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT+ " TEXT)";



            // Execute the SQL statement
            db.execSQL(SQL_CREATE_EXPENSE_TABLE); }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    private void readAndExecuteSQLScript(SQLiteDatabase db, Context ctx, String fileName) {

    }

    private void executeSQLScript(SQLiteDatabase db, BufferedReader reader) throws IOException {
        String line;
        StringBuilder statement = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            statement.append(line);
            statement.append("\n");
            if (line.endsWith(";")) {
                db.execSQL(statement.toString());
                statement = new StringBuilder();
            }
        }
    }

}
