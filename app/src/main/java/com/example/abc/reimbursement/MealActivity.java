package com.example.abc.reimbursement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MealActivity extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText finalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        finalAmount = (EditText) findViewById(R.id.final_amount);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.95),(int)(height*.90));
        final TextView mDisplayDate = (TextView)findViewById(R.id.mealdate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(MealActivity.this,
                        android.R.style.Theme_Holo_Light,
                        mDateSetListener ,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }

        } );
        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = dayOfMonth + "/" + month +"/" + year;
                mDisplayDate.setText(date);

            }
        } ;

        Button buttonScan =(Button)findViewById(R.id.button_scan);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent (MealActivity.this , ImageToTextConverter.class );
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //EditText finalAmount = (EditText) findViewById(R.id.final_amount);
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {

                double result = data.getDoubleExtra("result" , 0.00);
                finalAmount.setText(Double.toString(result));
            }
            if(resultCode == RESULT_CANCELED){

            }
        }
    }

}
