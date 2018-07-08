package com.example.abc.reimbursement;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.reimbursement.Data.BillContract;

import java.util.Calendar;

public class MealActivity extends AppCompatActivity  {
    DatePickerDialog.OnDateSetListener mDateSetListener;
    double result ;

    //EditText mNameEditText;
    //EditText mCategoryEditText;
    EditText mBillDateEditText;
    EditText mRestaurantNameEditText;
    EditText mClientNameEditText;
    EditText mPurposeEditText;

    EditText mFinalAmountEditText;

    String expenseName;
    String category;

    private static final int EXPENSE_LOADER = 0;

    BillCursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mFinalAmountEditText = (EditText) findViewById(R.id.final_amount);

        mBillDateEditText = (EditText) findViewById(R.id.mealdate);
        mRestaurantNameEditText = (EditText) findViewById(R.id.restaurant_name);
        mClientNameEditText = (EditText) findViewById(R.id.client_name);
        mPurposeEditText = (EditText) findViewById(R.id.purpose);

        mCursorAdapter = new BillCursorAdapter(this, null);

        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");
        category = intent.getStringExtra("category");





        /*DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.95),(int)(height*.90));*/
        final TextView mDisplayDate = (TextView) findViewById(R.id.mealdate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(MealActivity.this,
                        android.R.style.Theme_Holo_Light,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);

            }
        };

        Button buttonScan = (Button) findViewById(R.id.button_scan);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent(MealActivity.this, ImageToTextConverter.class);
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent, 1);
            }
        });


        Button buttonFinalSubmit = (Button) findViewById(R.id.final_submit);
        buttonFinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBill();
                Intent intent = new Intent(MealActivity.this, ExpenseReport.class);
                intent.putExtra("category","Meal");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                result = data.getDoubleExtra("result", 0);
                mFinalAmountEditText.setText(String.valueOf(result));

            }

            if (resultCode == RESULT_CANCELED)

            {

            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_all_activity, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                saveBill();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                //showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.


                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(MealActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                //showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveBill() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        /*String nameString = mNameEditText.getText().toString().trim();
        String category = mCategoryEditText.getText().toString().trim();*/

        String billDate = mBillDateEditText.getText().toString().trim();
        String restaurantName = mRestaurantNameEditText.getText().toString().trim();
        String clientName = mClientNameEditText.getText().toString().trim();
        String purpose = mPurposeEditText.getText().toString().trim();
        String finalAmount = mFinalAmountEditText.getText().toString().trim();


        ContentValues values = new ContentValues();
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_NAME, expenseName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_CAT, category);

        values.put(BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE, billDate);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME, restaurantName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME, clientName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE, purpose);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT, finalAmount);

        // Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);
        Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);


        //saveExpense();
        finish();

    }


    /*@Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.


        String[] projection = {
                BillContract.BillEntry._ID,
                BillContract.BillEntry.COLUMN_EXPENSE_CAT,
                BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE,
                BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE};


        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                BillContract.BillEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update {@link PetCursorAdapter} with this new cursor containing updated pet data
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        mCursorAdapter.swapCursor(null);
    }*/
}



