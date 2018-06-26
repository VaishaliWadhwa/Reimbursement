package com.example.abc.reimbursement;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

}