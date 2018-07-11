package com.example.abc.reimbursement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.abc.reimbursement.Data.BillContract;

public class ChoiceActivity extends AppCompatDialogFragment {


    String expenseName;
    private RadioGroup mRadioGroup;

    public ChoiceActivity() {
    }

    @SuppressLint("ValidFragment")
    public ChoiceActivity(String expense) {
        expenseName = expense;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.activity_choice, null);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.meal){
                    Intent intent = new Intent(getActivity(),MealActivity.class);
                    intent.putExtra("category", "Meal");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                }
                if (checkedId==R.id.localtravel){
                    Intent intent = new Intent(getActivity(),LocalTravel.class);
                    intent.putExtra("category", "Local Travel");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                }
                if (checkedId==R.id.distanttravel){
                    Intent intent = new Intent(getActivity(),DistantTravel.class);
                    intent.putExtra("category", "Distant Travel");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                }
                if (checkedId==R.id.teamexpense){
                    Intent intent = new Intent(getActivity(),TeamExpenseActivity.class);
                    intent.putExtra("category", "Team Expense");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                }
                if (checkedId==R.id.misc){
                    Intent intent = new Intent(getActivity(),MiscellaneousActivity.class);
                    intent.putExtra("category", "Miscellaneous");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                }

            }
        });
        builder.setView(view);

        //onRadioButtonClicked(view);
        return builder.create();
    }

   /* public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.meal:
                if (checked) {
                    Intent intent = new Intent(getActivity(), MealActivity.class);
                    intent.putExtra("category", "Meal");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);


                    break;
                }
            case R.id.localtravel:
                if (checked) {
                    // Ninjas rule
                    Intent intent = new Intent(getActivity(), LocalTravel.class);
                    intent.putExtra("category", "Local Travel");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);


                    break;
                }
            case R.id.distanttravel:
                if (checked) {
                    Intent intent = new Intent(getActivity(), DistantTravel.class);
                    intent.putExtra("category", "Distant Travel");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);

                    break;
                }
                // Pirates are the best

            case R.id.teamexpense:
                if (checked) {
                    Intent intent = new Intent(getActivity(), TeamExpenseActivity.class);
                    intent.putExtra("category", "Team Expense");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);


                    break;
                }
                // Pirates are the best

            case R.id.misc:
                if (checked) {
                    Intent intent = new Intent(getActivity(), MiscellaneousActivity.class);
                    intent.putExtra("category", "Miscellaneous");
                    intent.putExtra("expenseName", expenseName);
                    startActivity(intent);


                    break;
                }
                // Pirates are the best

        }
    }*/


}





        /*setContentView(R.layout.activity_choice);

        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .70), (int) (height * .30));


    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.meal:
                if (checked) {
                    Intent intent = new Intent(ChoiceActivity.this , MealActivity.class);
                    intent.putExtra("category" , "Meal");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break; }
            case R.id.localtravel:
                if (checked){
                    // Ninjas rule
                    Intent intent = new Intent(ChoiceActivity.this , LocalTravel.class);
                    intent.putExtra("category" , "Local Travel");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break; }
            case R.id.distanttravel:
                if (checked){Intent intent =  new Intent(ChoiceActivity.this , DistantTravel.class);
                    intent.putExtra("category" , "Distant Travel");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);

                    break;}
                    // Pirates are the best

            case R.id.teamexpense:
                if (checked){
                    Intent intent =  new Intent(ChoiceActivity.this , TeamExpenseActivity.class);
                    intent.putExtra("category" , "Team Expense");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break;
                }
                    // Pirates are the best

            case R.id.misc:
                if (checked){Intent intent =  new Intent(ChoiceActivity.this , MiscellaneousActivity.class);
                    intent.putExtra("category" , "Miscellaneous");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break;}
                    // Pirates are the best

        }
    }
}*/