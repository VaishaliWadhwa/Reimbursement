package com.example.abc.reimbursement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DistantTravel extends AppCompatActivity implements View.OnClickListener {
    String expenseName;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distant_travel);
        Intent intent = getIntent();
        expenseName = intent.getStringExtra("expenseName");
        category = intent.getStringExtra("category");

        Bundle bundle = new Bundle();



        bundle.putString("expenseName",expenseName);
        bundle.putString("category",category);
            Fragment fragment = new DistantTravelStayFragment();
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



        Button btn12 =  findViewById(R.id.button12);
        btn12.setOnClickListener(this);
        Button btn22 = findViewById(R.id.button22);
        btn22.setOnClickListener(this);
        Button btn32 =  findViewById(R.id.button32);
        btn32.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v.getId() == R.id.button12) {
            Bundle bundle = new Bundle();
            bundle.putString("expenseName",expenseName);
            bundle.putString("category",category);
            fragment = new DistantTravelMealFragment();
            fragment.setArguments(bundle);

        } else if (v.getId() == R.id.button22) {
            Bundle bundle = new Bundle();
            bundle.putString("expenseName",expenseName);
            bundle.putString("category",category);
            fragment = new DistantTravelTravelActivity();
            fragment.setArguments(bundle);

        } else if (v.getId() == R.id.button32) {
            Bundle bundle = new Bundle();
            bundle.putString("expenseName",expenseName);
            bundle.putString("category",category);
            fragment = new DistantTravelStayFragment();
            fragment.setArguments(bundle);

        }
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment2, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}



