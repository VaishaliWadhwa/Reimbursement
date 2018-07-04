package com.example.abc.reimbursement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String expenseName;
    String Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .70), (int) (height * .70));

        Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ChoiceActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

       /* DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.50),(int)(height*.20));*/

        mySpinner.setOnItemSelectedListener(this);

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        int category = i;
        switch(i)
        {
            case (0) :
            {
                break;


            }
            case (1) :
            {


                Intent intent = new Intent (ChoiceActivity.this , MealActivity.class );
                intent.putExtra("category","Meal");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
                break;
            }

            case (2):
            {

                Intent intent = new Intent (ChoiceActivity.this , LocalTravel.class );
                startActivity(intent);
                break;




            }
            case (3):
            {

                Intent intent = new Intent (ChoiceActivity.this , DistantTravel.class );
                startActivity(intent);
                break;




            }

            case (4):
            {

                Intent intent = new Intent (ChoiceActivity.this , TeamExpenseActivity.class );
                intent.putExtra("category","Team Expense");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
                break;




            }
            case (5):
            {

                Intent intent = new Intent (ChoiceActivity.this , MiscellaneousActivity.class );
                intent.putExtra("category","Miscellaneous");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
                break;




            }
            default :
            {
                Toast.makeText(this, "Slct other", Toast.LENGTH_SHORT).show();
                break;

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}