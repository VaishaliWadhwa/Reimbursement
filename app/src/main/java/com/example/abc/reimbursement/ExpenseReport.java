package com.example.abc.reimbursement;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

import com.example.abc.reimbursement.Data.BillContract;

public class ExpenseReport extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private Uri mCurrentExpenseUri;
    /** Identifier for the pet data loader */
    private static final int EXISTING_EXPENSE_LOADER = 0;

    String expenseName;
    private static final int EXPENSE_LOADER = 0;

    CategoryCursorAdapter mCursorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_report);

        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");

        setTitle(expenseName);

        ListView expenseListView = (ListView) findViewById(R.id.bill_list);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
       // View emptyView = findViewById(R.id.empty_view);
        //expenseListView.setEmptyView(emptyView);

        mCursorAdapter = new CategoryCursorAdapter(this, null);
        expenseListView.setAdapter(mCursorAdapter);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseReport.this, ChoiceActivity.class);
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
            }
        });



        getLoaderManager().initLoader(EXPENSE_LOADER, null, this);

    }



    @Override
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
    }

}
