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
import android.view.View;
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
    TextView categoryText;
    TextView subCategoryText;
    TextView restaurantNameText;
    TextView fromText;
    TextView toText;
    TextView venueText;

    LinearLayout clientNameLayout;
    LinearLayout membersLayout;
    LinearLayout subCategoryLayout;
    LinearLayout restaurantNameLayout;
    LinearLayout fromLayout;
    LinearLayout toLayout;
    LinearLayout venueLayout;

    String id;

    LinearLayout clientOrMember;

    /**
     * Identifier for the pet data loader
     */
    private static final int EXPENSE_LOADER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        clientNameLayout = (LinearLayout) findViewById(R.id.l_client_name);
        membersLayout = (LinearLayout) findViewById(R.id.l_members);
        subCategoryLayout = (LinearLayout) findViewById(R.id.l_sub_category);
        restaurantNameLayout = (LinearLayout) findViewById(R.id.l_restaurant_name);
        fromLayout = (LinearLayout)findViewById(R.id.l_from);
        toLayout = (LinearLayout) findViewById(R.id.l_to);
        venueLayout = (LinearLayout) findViewById(R.id.l_venue);

        expenseIdText = (TextView) findViewById(R.id.expense_id);
        expenseNameText = (TextView) findViewById(R.id.expense_name);
        billDateText = (TextView) findViewById(R.id.bill_date);
        purposeText = (TextView) findViewById(R.id.purpose);
        finalAmountText = (TextView) findViewById(R.id.final_amount);
        clientNameText = (TextView) findViewById(R.id.client_name);
        membersText = (TextView) findViewById(R.id.members);
        categoryText = (TextView) findViewById(R.id.category);
        subCategoryText = (TextView) findViewById(R.id.sub_category);
        restaurantNameText = (TextView) findViewById(R.id.restaurant_name);
        fromText = (TextView) findViewById(R.id.from);
        toText = (TextView) findViewById(R.id.to);
        venueText = (TextView) findViewById(R.id.venue);

        clientNameLayout.setVisibility(View.GONE);
        membersLayout.setVisibility(View.GONE);
        subCategoryLayout.setVisibility(View.GONE);
        restaurantNameLayout.setVisibility(View.GONE);
        fromLayout.setVisibility(View.GONE);
        toLayout.setVisibility(View.GONE);
        venueLayout.setVisibility(View.GONE);


        /*LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT ,
                 1.0f

        );*/


       /* clientNameText = new TextView(this);
        clientNameText.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,0.33f));
        clientNameText.setLayoutParams(lparams);

        clientNameText.setText("Client Names");
        clientNameText.setTextSize(20);
        this.clientOrMember.addView(clientNameText );

        TextView tv=new TextView(this);
        tv.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        tv.setLayoutParams(lparams);

        tv.setText("test");


        this.clientOrMember.addView(tv);

        ;*/
        //clientNameText.setLayoutParams(param);


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
                BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT,
                BillContract.BillEntry.COLUMN_EXPENSE_CAT,
                BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT,
                BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME,
                BillContract.BillEntry.COLUMN_EXPENSE_FROM,
                BillContract.BillEntry.COLUMN_EXPENSE_VENUE,
                BillContract.BillEntry.COLUMN_EXPENSE_TO,};

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
            int purposeColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE);
            int finalAmountColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT);
            int categoryColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_CAT);
            int subCategoryColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT);
            int restaurantNameColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME);
            int fromColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_FROM);
            int toColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_TO);
            int venueColumnIndex = cursor.getColumnIndex(BillContract.BillEntry.COLUMN_EXPENSE_VENUE);



            // Extract out the value from the Cursor for the given column index
            id = cursor.getString(idColumnIndex);
            String expenseName = cursor.getString(nameColumnIndex);
            String billDate = cursor.getString(billDateColumnIndex);
            String clientName = cursor.getString(clientNameColumnIndex);
            String member = cursor.getString(membersColumnIndex);
            String purpose = cursor.getString(purposeColumnIndex);
            String category = cursor.getString(categoryColumnIndex);
            String subCategory  = cursor.getString(subCategoryColumnIndex);
            String restaurantName= cursor.getString(restaurantNameColumnIndex);
            String from = cursor.getString(fromColumnIndex);
            String to = cursor.getString(toColumnIndex);
            String finalAmount = cursor.getString(finalAmountColumnIndex);
            String venue = cursor.getString(venueColumnIndex);

            // Update the views on the screen with the values from the database
            expenseIdText.setText(id);
            expenseNameText.setText(expenseName);
            billDateText.setText(billDate);
            //clientNameText.setText(clientName);
            //memberText.setText(member);
            finalAmountText.setText(finalAmount);
            purposeText.setText(purpose);
            categoryText.setText(category);

            if(category.equalsIgnoreCase("Meal")){
                clientNameLayout.setVisibility(View.VISIBLE);
                clientNameText.setText(clientName);
                restaurantNameLayout.setVisibility(View.VISIBLE);
                restaurantNameText.setText(restaurantName);
            }

            if(category.equalsIgnoreCase("Team Expense")){
                membersLayout.setVisibility(View.VISIBLE);
                membersText.setText(member);

            }

            if(category.equalsIgnoreCase("Distant Travel")&&subCategory.equalsIgnoreCase("Travel")){
                fromLayout.setVisibility(View.VISIBLE);
                fromText.setText(from);
                toLayout.setVisibility(View.VISIBLE);
                toText.setText(to);
                subCategoryLayout.setVisibility(View.VISIBLE);
                subCategoryText.setText(subCategory);

            }

            if(category.equalsIgnoreCase("Distant Travel")&&subCategory.equalsIgnoreCase("Meal")){
                fromLayout.setVisibility(View.VISIBLE);
                fromText.setText(from);
                toLayout.setVisibility(View.VISIBLE);
                toText.setText(to);
                clientNameLayout.setVisibility(View.VISIBLE);
                clientNameText.setText(clientName);
                restaurantNameLayout.setVisibility(View.VISIBLE);
                restaurantNameText.setText(restaurantName);
                subCategoryLayout.setVisibility(View.VISIBLE);
                subCategoryText.setText(subCategory);

            }

            if(category.equalsIgnoreCase("Distant Travel")&&subCategory.equalsIgnoreCase("Stay")){
                fromLayout.setVisibility(View.VISIBLE);
                fromText.setText(from);
                toLayout.setVisibility(View.VISIBLE);
                toText.setText(to);
                venueLayout.setVisibility(View.VISIBLE);
                venueText.setText(venue);
                subCategoryLayout.setVisibility(View.VISIBLE);
                subCategoryText.setText(subCategory);

            }

            if(category.equalsIgnoreCase("Local Travel")&&subCategory.equalsIgnoreCase("Travel")){
                fromLayout.setVisibility(View.VISIBLE);
                fromText.setText(from);
                toLayout.setVisibility(View.VISIBLE);
                toText.setText(to);
                subCategoryLayout.setVisibility(View.VISIBLE);
                subCategoryText.setText(subCategory);

            }


            if(category.equalsIgnoreCase("Local Travel")&&subCategory.equalsIgnoreCase("Meal")){
                fromLayout.setVisibility(View.VISIBLE);
                fromText.setText(from);
                toLayout.setVisibility(View.VISIBLE);
                toText.setText(to);
                clientNameLayout.setVisibility(View.VISIBLE);
                clientNameText.setText(clientName);
                restaurantNameLayout.setVisibility(View.VISIBLE);
                restaurantNameText.setText(restaurantName);
                subCategoryLayout.setVisibility(View.VISIBLE);
                subCategoryText.setText(subCategory);

            }


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
        String selectionArgs[] = new String[]{id};
        int rowsDeleted = getContentResolver().delete(BillContract.BillEntry.CONTENT_URI, selection, selectionArgs);


    }
}