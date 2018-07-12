package com.example.abc.reimbursement;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.reimbursement.Data.BillContract;

import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class DistantTravelMealFragment extends Fragment {

    String expenseName;
    String category;
    String travelFrom;
    String travelTo;

    TextView mBillDateEditText;
    EditText mRestaurantNameEditText;
    EditText mClientNameEditText;
    EditText mPurposeEditText;

    EditText mFinalAmountEditText;

    BillCursorAdapter mCursorAdapter;

    DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.distant_travel_meal_fragment, container, false);

        try {
            expenseName = getArguments().getString("expenseName");
            category = getArguments().getString("category");
            travelFrom = getArguments().getString("travelFrom");
            travelTo = getArguments().getString("travelTo");

        }catch(NullPointerException e)
        {
            System.out.println("NullPointerException caught");
        }

        mFinalAmountEditText = (EditText) view.findViewById(R.id.final_amount);

        mBillDateEditText = (TextView) view.findViewById(R.id.bill_date);
        mRestaurantNameEditText = (EditText) view.findViewById(R.id.restaurant_name);
        mClientNameEditText = (EditText) view.findViewById(R.id.client_name);
        mPurposeEditText = (EditText) view.findViewById(R.id.purpose);

        mCursorAdapter = new BillCursorAdapter(getActivity(), null);

        // Inflate the layout for this fragment


        mBillDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
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
                mBillDateEditText.setText(date);

            }
        } ;

        Button buttonScan =(Button)view.findViewById(R.id.button_scan);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageToTextIntent = new Intent (getActivity() , ImageToTextConverter.class );
                //startActivity(imageToTextIntent);
                startActivityForResult(imageToTextIntent , 1);
            }
        });

        Button buttonFinalSubmit = (Button) view.findViewById(R.id.final_submit);
        buttonFinalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBill();
                Intent intent = new Intent(getActivity(), ExpenseReport.class);
                intent.putExtra("category","Meal");
                intent.putExtra("expenseName", expenseName);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //EditText finalAmount = (EditText) findViewById(R.id.final_amount);
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {

                double result = data.getDoubleExtra("result" , 0.00);
                mFinalAmountEditText.setText(Double.toString(result));
            }
            if(resultCode == RESULT_CANCELED){

            }
        }
    }

    private void saveBill() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        /*String nameString = mNameEditText.getText().toString().trim();
        String category = mCategoryEditText.getText().toString().trim();*/

        String billDate = mBillDateEditText.getText().toString().trim();
        String restaurantName = mRestaurantNameEditText.getText().toString().trim();
        String clientName = mClientNameEditText.getText().toString().trim();
        String purpose = mPurposeEditText.getText().toString().trim();
        String finalAmount = mFinalAmountEditText.getText().toString().trim();


        ContentValues values = new ContentValues();
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_NAME, expenseName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_CAT, category);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_FROM, DistantTravel.getTravelFrom());
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_TO, DistantTravel.getTravelTo());
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_SUBCAT,"Meal");

        values.put(BillContract.BillEntry.COLUMN_EXPENSE_BILLDATE, billDate);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_RESTNAME, restaurantName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_CLIENTNAME, clientName);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_PURPOSE, purpose);
        values.put(BillContract.BillEntry.COLUMN_EXPENSE_FINAL_AMOUNT, finalAmount);

        // Uri newUri = getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);
        Uri newUri = getActivity().getContentResolver().insert(BillContract.BillEntry.CONTENT_URI, values);


        //saveExpense();
        Toast.makeText(getActivity(),"Your Meal Details have been saved" ,Toast.LENGTH_LONG).show();

    }


}