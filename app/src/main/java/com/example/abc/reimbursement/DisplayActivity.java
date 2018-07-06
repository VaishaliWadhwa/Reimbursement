package com.example.abc.reimbursement;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.reimbursement.Data.BillContract;

public class DisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    /**
     * Content URI for the existing pet (null if it's a new pet)
     */
    private Uri mCurrentPetUri;
    TextView expenseIdText;
    TextView expenseNameText;
    TextView billDateText;
    TextView purposeText;
    TextView finalAmountText;
    TextView clientNameText;
    TextView membersText;
    String id ;

    LinearLayout clientOrMember;

    /**
     * Identifier for the pet data loader
     */
    private static final int EXPENSE_LOADER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        expenseIdText = (TextView) findViewById(R.id.expense_id);
        expenseNameText = (TextView) findViewById(R.id.expense_name);
        billDateText = (TextView) findViewById(R.id.bill_date);
        purposeText = (TextView) findViewById(R.id.purpose);
        finalAmountText = (TextView) findViewById(R.id.final_amount);

        clientOrMember = (LinearLayout) findViewById(R.id.client_or_member);

        //LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
        //LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //clientNameText = new TextView(this);
        //clientNameText.setLayoutParams(lparams);
        //clientNameText.setText("test");
        //this.clientOrMember.addView(clientNameText);


        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new pet or editing an existing one.
        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();

        // Initialize a loader to read the pet data from the database
        // and display the current values in the editor
        getLoaderManager().initLoader(EXPENSE_LOADER, null, this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                BillContract.BillEntry._ID,
                BillContract.BillEntry.COLUMN_EXPENSE_NAME,
                BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE,
                BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME,
                BillContract.BillEntry.COLUMN_EXPENSE_MEMBERS,
                BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE,
                BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT,};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentPetUri,         // Query the content URI for the current pet
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of pet attributes that we're interested in
            int idColumnIndex = cursor.getColumnIndex(BillContract.BillEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_NAME);
            int billDateColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE);
            int clientNameColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME);
            int membersColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_MEMBERS);
            int finalAmountColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT);

            // Extract out the value from the Cursor for the given column index
            id = cursor.getString(idColumnIndex);
            String expenseName = cursor.getString(nameColumnIndex);
            String billDate = cursor.getString(billDateColumnIndex);
            String clientName = cursor.getString(clientNameColumnIndex);
            String member = cursor.getString(membersColumnIndex);
            String finalAmount = cursor.getString(finalAmountColumnIndex);

            // Update the views on the screen with the values from the database
            expenseIdText.setText(id);
            expenseNameText.setText(expenseName);
            billDateText.setText(billDate);
            //clientNameText.setText(clientName);
            //memberText.setText(member);
            finalAmountText.setText(finalAmount);


        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        /*expenseIdText.setText("");
        expenseNameText.setText("");
        billDateText.setText("");
        //clientNameText.setText("");
        //memberText.setText("");
        finalAmountText.setText("");*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_bill:
                deleteBill();
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteBill() {
        String selection = "_ID=?";
        String selectionArgs [] = new String[] { id };
        int rowsDeleted = getContentResolver().delete(BillContract.BillEntry.CONTENT_URI,selection , selectionArgs);



    } }





























