package com.example.abc.reimbursement;

import android.app.Activity;
import android.app.DatePickerDialog;
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

public class LocalTravel extends AppCompatActivity {

    EditText mBillDateEditText;
    EditText mPurposeEditText;

    EditText mFinalAmountEditText;
    BillCursorAdapter mCursorAdapter;

    String expenseName;
    String category;

    double result ;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_travel);

        mFinalAmountEditText = (EditText) findViewById(R.id.final_amount);
        mBillDateEditText = (EditText) findViewById(R.id.bill_date);
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



        Button buttonScan = (Button) findViewById(R.id.button_scan);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent(LocalTravel.this, ImageToTextConverter.class);
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent, 1);
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
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                result = data.getDoubleExtra("result", 0.00);
                mFinalAmountEditText.setText(Double.toString(result));

            }
            EditText text = (EditText) findViewById(R.id.final_amount);
            TextWatcher textWatcher = null;
            text.addTextChangedListener(textWatcher);


            textWatcher = new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (mFinalAmountEditText.equals(Double.toString(result))) {

                    } else {
                        Toast.makeText(getApplicationContext(), "change", Toast.LENGTH_LONG).show();
                    }


                }
            };

            if (resultCode == RESULT_CANCELED)
            {

            }

        }
    }

    private void saveBill() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        /*String nameString = mNameEditText.getText().toString().trim();
        String category = mCategoryEditText.getText().toString().trim();*/

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
