package com.example.abc.reimbursement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    /*
     * Database helper that will provide us access to the database
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExpenseReport.class);
                startActivity(intent);

            }

        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        // mDbHelper = new PetDbHelper(this);


        // displayDatabaseInfo();
    }

}