package com.example.abc.reimbursement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.reimbursement.Data.BillContract;

import java.util.Calendar;

public class LocalTravel extends AppCompatActivity implements View.OnClickListener {
    String expenseName;
    String category;
    public static EditText mTravelFrom;
    public static EditText mTravelTo;

    public static String travelFrom;
    public static String travelTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_travel);
        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");
        category = intent.getStringExtra("category");

        mTravelFrom = (EditText) findViewById(R.id.from);
        mTravelTo = (EditText)findViewById(R.id.to);

        //travelFrom = mTravelFrom.getText().toString();
        //travelTo = mTravelTo.getText().toString();


        Bundle bundle = new Bundle();

        bundle.putString("expenseName",expenseName);
        bundle.putString("category",category);
        //bundle.putString("travelFrom",travelFrom);
        //bundle.putString("travelTo", travelTo);

        Fragment fragment = new LocalTravelTravelFragment();
        fragment.setArguments(bundle);
            /*FragmentManager manager = getFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.fragment2, fragment);
            fragmentTransaction.commit();*/
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();



        Button mTravelButton =  findViewById(R.id.travel_button);
        mTravelButton.setOnClickListener(this);
        Button mMealButton = findViewById(R.id.meal_button);
        mMealButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v.getId() == R.id.travel_button) {
            Bundle bundle = new Bundle();
            bundle.putString("expenseName",expenseName);
            bundle.putString("category",category);
            //bundle.putString("travelFrom",travelFrom);
            //bundle.putString("travelTo", travelTo);
            fragment = new LocalTravelTravelFragment();
            fragment.setArguments(bundle);

        } else if (v.getId() == R.id.meal_button){
            Bundle bundle = new Bundle();
            bundle.putString("expenseName",expenseName);
            bundle.putString("category",category);
            //bundle.putString("travelFrom",travelFrom);
            //bundle.putString("travelTo", travelTo);
            fragment = new LocalTravelMealFragment();
            fragment.setArguments(bundle);

        }

        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public static String getTravelFrom(){
        travelFrom = mTravelFrom.getText().toString();
        return travelFrom;
    }
    public static String getTravelTo(){
        travelTo = mTravelTo.getText().toString();
        return travelTo;
    }

}




















   /* TextView mBillDateEditText;
    EditText mPurposeEditText;

    EditText mFinalAmountEditText;
    BillCursorAdapter mCursorAdapter;

    EditText mTravelAmountEditText;
    EditText mMealAmountEditText;

    String expenseName;
    String category;

    double result ;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_travel);

        mMealAmountEditText = (EditText) findViewById(R.id.meal_amount);
        mTravelAmountEditText = (EditText) findViewById(R.id.travel_amount);
        mFinalAmountEditText = (EditText) findViewById(R.id.final_amount);
        mBillDateEditText = (TextView) findViewById(R.id.bill_date);
        mPurposeEditText = (EditText) findViewById(R.id.purpose);

        mCursorAdapter = new BillCursorAdapter(this, null);

        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");
        category = intent.getStringExtra("category");



        mBillDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(LocalTravel.this,
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
                mBillDateEditText.setText(date);

            }
        };



        Button buttonTravelScan = (Button) findViewById(R.id.travel_scan);

        buttonTravelScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent(LocalTravel.this, ImageToTextConverter.class);
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent, 1);
            }
        });

        Button buttonMealScan = (Button) findViewById(R.id.travel_scan);

        buttonMealScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent(LocalTravel.this, ImageToTextConverter.class);
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent, 2);
            }
        });

        Button buttonFinalSubmit = (Button) findViewById(R.id.final_submit);
        buttonFinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBill();
                Intent intent = new Intent(LocalTravel.this, ExpenseReport.class);
                intent.putExtra("category","Meal");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //EditText finalAmount = (EditText) findViewById(R.id.final_amount);

        double travelResult = 0;
        double mealResult = 0;
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {

                travelResult = data.getDoubleExtra("result" , 0.00);
                mTravelAmountEditText.setText(Double.toString(travelResult));
            }
            if(resultCode == RESULT_CANCELED){

            }
        }

        if(requestCode == 2) {
            if (resultCode == RESULT_OK) {

                mealResult = data.getDoubleExtra("result" , 0.00);
                mMealAmountEditText.setText(Double.toString(mealResult));
            }
            if(resultCode == RESULT_CANCELED){

            }
        }

        mFinalAmountEditText.setText(Double.toString(travelResult+mealResult));
    }

    private void saveBill() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        *//*String nameString = mNameEditText.getText().toString().trim();
        String category = mCategoryEditText.getText().toString().trim();*//*

        String billDate = mBillDateEditText.getText().toString().trim();
        String purpose = mPurposeEditText.getText().toString().trim();
        String finalAmount = mFinalAmountEditText.getText().toString().trim();


        ContentValues values = new ContentValues();
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_NAME, expenseName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_CAT, category);

        values.put(BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE, billDate);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE, purpose);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT, finalAmount);

        // Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);
        Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);


        //saveExpense();
        finish();

    }


}
*/