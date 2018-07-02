package com.example.abc.reimbursement.Data;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abc.reimbursement.R;

public class EditorExpense extends AppCompatActivity {
    EditText mNameEditText;
    EditText mStartDateEditText;
    EditText mEndDateText;
    private Uri mCurrentExpenseUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_editor_expense);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;


        getWindow().setLayout((int) (width * .80), (int) (height * .42));
        Button button = (Button) findViewById(R.id.ok);
         mNameEditText = (EditText) findViewById(R.id.expensename);
        mStartDateEditText = (EditText) findViewById(R.id.startdate);
        mEndDateText = (EditText) findViewById(R.id.enddate);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                saveExpense();

            }

            ;
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
        // and check if all the fields in the editor are blank
        if (mCurrentExpenseUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(StartDateString) &&
                TextUtils.isEmpty(EndDateString) ) {
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_NAME, nameString);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE, StartDateString);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_ENDDATE, EndDateString);

        // Determine if this is a new or existing pet by checking if mCurrentPetUri is null or not
        if (mCurrentExpenseUri == null) {
            // This is a NEW pet, so insert a new pet into the provider,
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, "Insertion Failed Try Again",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // Otherwise this is an EXISTING pet, so update the pet with content URI: mCurrentPetUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentPetUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentExpenseUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "Editing Failed",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, "Editing Successfull",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
