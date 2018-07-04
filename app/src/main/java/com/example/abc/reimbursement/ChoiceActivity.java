package com.example.abc.reimbursement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;

public class ChoiceActivity extends AppCompatActivity  {


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
        getWindow().setLayout((int) (width * .70), (int) (height * .30));


    }



    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.meal:
                if (checked) {
                    Intent intent =  new Intent(ChoiceActivity.this , MealActivity.class);
                    intent.putExtra("category " , "Meal");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break; }
            case R.id.localtravel:
                if (checked){
                    // Ninjas rule
                    Intent intent =  new Intent(ChoiceActivity.this , LocalTravel.class);
                    intent.putExtra("category " , "Local Travel");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break; }
            case R.id.distanttravel:
                if (checked){Intent intent =  new Intent(ChoiceActivity.this , DistantTravel.class);
                    intent.putExtra("category " , "Distant Travel");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);

                    break;}
                    // Pirates are the best

            case R.id.teamexpense:
                if (checked){
                    Intent intent =  new Intent(ChoiceActivity.this , TeamExpenseActivity.class);
                    intent.putExtra("category " , "Team Expense");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break;
                }
                    // Pirates are the best

            case R.id.misc:
                if (checked){Intent intent =  new Intent(ChoiceActivity.this , MiscellaneousActivity.class);
                    intent.putExtra("category " , "Miscellaneous");
                    intent.putExtra("expenseName",expenseName);
                    startActivity(intent);


                    break;}
                    // Pirates are the best

        }
    }
}