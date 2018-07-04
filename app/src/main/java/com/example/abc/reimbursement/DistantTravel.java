package com.example.abc.reimbursement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DistantTravel extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distant_travel);
        Button btn12 =  findViewById(R.id.button12);
        btn12.setOnClickListener(this);
        Button btn22 = findViewById(R.id.button22);
        btn22.setOnClickListener(this);
        Button btn32 =  findViewById(R.id.button32);
        btn32.setOnClickListener(this);
    }

    /*  @Override
      public  void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          Button btn1 = (Button)findViewById(R.id.button1);
          btn1.setOnClickListener(this);
          Button btn2 = (Button)findViewById(R.id.button2);
          btn2.setOnClickListener(this);
  */


    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v.getId() == R.id.button12) {
            fragment = new DistantTravelMealFragment();
        } else if (v.getId() == R.id.button22) {
            fragment = new DistantTravelTravelActivity();

        } else if (v.getId() == R.id.button32) {
            fragment = new DistantTravelStayFragment();

        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment2, fragment);
        transaction.addToBackStack(null);

        transaction.commit();


    }

    ;
}



