package com.example.abc.reimbursement;


import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abc.reimbursement.Data.BillContract;
import com.example.abc.reimbursement.Data.BillDbHelper;
import com.example.abc.reimbursement.Data.EditorExpense;
/**
 * Displays list of pets that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity  {

    /**
     * Identifier for the pet data loader
     */
    private static final int EXPENSE_LOADER = 0;

    BillDbHelper mDbHelper;
    /*
     * Database helper that will provide us access to the database
     */

    /**
     * Adapter for the ListView
     */
    BillCursorAdapter mCursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorExpense.class);
                getWindow().setBackgroundDrawable(new ColorDrawable(
                        Color.WHITE));
                startActivity(intent);

            }
        });

        // Find the ListView which will be populated with the pet data
        ListView expenseListView = (ListView) findViewById(R.id.list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        expenseListView.setEmptyView(emptyView);

        // Setup an Adapter to create a list item for each row of pet data in the Cursor.
        // There is no pet data yet (until the loader finishes) so pass in null for the Cursor.
        mCursorAdapter = new BillCursorAdapter(this, null);
        expenseListView.setAdapter(mCursorAdapter);

        // Setup the item click listener
        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent intent = new Intent(MainActivity.this, ExpenseReport.class);

                // Form the content URI that represents the specific pet that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link PetEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.android.pets/pets/2"
                // if the pet with ID 2 was clicked on.
                Uri currentExpenseUri = ContentUris.withAppendedId(BillContract.BillEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentExpenseUri);

                // Launch the {@link EditorActivity} to display the data for the current pet.
                startActivity(intent);
            }
        });

        // Kick off the loader
        //getLoaderManager().initLoader(EXPENSE_LOADER, null, this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_expenses:
                //deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + BillContract.BillEntry.TABLE_NAME, null);


        TextView nameTextView = (TextView) findViewById(R.id.name);
        TextView summaryTextView = (TextView) findViewById(R.id.summary);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_NAME);
        int startDateColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE);
        int endDateColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_STARTDATE);

        // Read the pet attributes from the Cursor for the current pet
        String expenseName = cursor.getString(nameColumnIndex);
        String expenseStartDate = cursor.getString(startDateColumnIndex);
        String expenseEndDate = cursor.getString(endDateColumnIndex);

        // If the pet breed is empty string or null, then use some default text
        // that says "Unknown breed", so the TextView isn't blank.

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(expenseName);
        summaryTextView.setText(expenseStartDate + " - " + expenseEndDate);
        cursor.close();
    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}