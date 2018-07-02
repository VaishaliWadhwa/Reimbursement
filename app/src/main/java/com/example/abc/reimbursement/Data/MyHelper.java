package com.example.abc.reimbursement.Data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_EXPENSE_TABLE =  "CREATE TABLE " + BillContract.BillEntry.TABLE_NAME + " ("

                + BillContract.BillEntry.COLUMN_EXPENSE_NAME + " TEXT NOT NULL UNIQUE , "
                + BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE+ " TEXT, "

                + BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE+ " TEXT, "
                + BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE+ " DATE  , "
                + BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME+ " TEXT , "
                + BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME+ "TEXT ,"
                + BillContract.BillEntry.COLUMN_EXPENSE_MEMBERS+ "TEXT ,"
                + BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE+ " TEXT,"
                + BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT +" REAL, "
                + BillContract.BillEntry.COLUMN_EXPENSE_CAT+ " TEXT,"
                +BillContract.BillEntry.COLUMN_EXPENSE_BILL_ID+ " INTEGER  , "

                + BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT+ " TEXT)";


        // Execute the SQL statement
        db.execSQL(SQL_CREATE_EXPENSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
