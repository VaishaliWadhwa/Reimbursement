package com.example.abc.reimbursement.Data;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abc.reimbursement.R;

public class EditorExpense extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    EditText mNameEditText;
    EditText mStartDateEditText;
    EditText mEndDateText;
    private Uri mCurrentExpenseUri;

    private static final int EXISTING_EXPENSE_LOADER = 0;
    private boolean mExpenseHasChanged = false;
    private MyHelper mMyHelper;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_editor_expense);

        mMyHelper = new MyHelper(EditorExpense.this,"Reimbursement",null,1);
        mDB = mMyHelper.getWritableDatabase();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int) (width * .80), (int) (height * .42));

        mNameEditText = (EditText) findViewById(R.id.expensename);
        mStartDateEditText = (EditText) findViewById(R.id.startdate);
        mEndDateText = (EditText) findViewById(R.id.enddate);

        Intent intent = getIntent();

        Button button = (Button) findViewById(R.id.okay);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

             saveExpense();
             finish();

            }
        });

        Button button2 = (Button) findViewById(R.id.Back);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }

            ;


        });


    }

    private void saveExpense() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String StartDateString = mStartDateEditText.getText().toString().trim();
        String EndDateString = mEndDateText.getText().toString().trim();

        // Check if this is supposed to be a new pet


        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_NAME, nameString);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE, StartDateString);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE, EndDateString);
        long id = mDB.insert("Expenses",null,values);
        Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();






        // Determine if this is a new or existing pet by checking if mCurrentPetUri is null or not

        }

        @Override
        public Loader<Cursor> onCreateLoader ( int id, Bundle args){
            // Since the editor shows all pet attributes, define a projection that contains
            // all columns from the pet table
            String[] projection ={


                    BillContract.BillEntry.COLUMN_EXPENSE_NAME,
                    BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE,
                    BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE};

            // This loader will execute the ContentProvider's query method on a background thread
            return new CursorLoader(this,   // Parent activity context
                    // Query the content URI for the current pet
                    projection,             // Columns to include in the resulting Cursor
                    null,                   // No selection clause
                    null,                   // No selection arguments
                    null);                  // Default sort order
        }

        @Override
        public void onLoadFinished (Loader < Cursor > loader, Cursor cursor){
            // Bail early if the cursor is null or there is less than 1 row in the cursor
            if (cursor == null || cursor.getCount() < 1) {
                return;
            }

            // Proceed with moving to the first row of the cursor and reading data from it
            // (This should be the only row in the cursor)
            if (cursor.moveToFirst()) {
                // Find the columns of pet attributes that we're interested in
                int nameColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_NAME);
                int startDateColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE);
                int endDateColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE);

                // Extract out the value from the Cursor for the given column index
                String name = cursor.getString(nameColumnIndex);
                String startDate = cursor.getString(startDateColumnIndex);
                int endDate = cursor.getInt(endDateColumnIndex);

                // Update the views on the screen with the values from the database
                mNameEditText.setText(name);
                mStartDateEditText.setText(startDate);
                mEndDateText.setText(endDate);


            }
        }

        @Override
        public void onLoaderReset (Loader < Cursor > loader) {
            // If the loader is invalidated, clear out all the data from the input fields.
            mNameEditText.setText("");
            mStartDateEditText.setText("");
            mEndDateText.setText("");

        }
    }
