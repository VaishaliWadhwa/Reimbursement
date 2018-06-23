package com.example.abc.reimbursement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Travel extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .95), (int) (height * .90));
        /*View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = null;
                if(view == findViewById(R.id.button1)){

                } if(view==findViewById(R.id.button2)){

                }

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.output, fragment);
                transaction.commit();
            }
        };*/


    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v.getId() == R.id.button1){
            fragment = new TravelFragment1();
        }else if (v.getId() == R.id.button2){
            fragment = new TravelFragment2();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.output, fragment);
        transaction.commit();
    }
}
       /* View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }*/
                /*Fragment fragment = null;
                if (view == findViewById(R.id.button1)) {
                    fragment = new TravelFragment1();
                } else {
                    fragment = new TravelFragment2();
                }

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment, fragment);
                transaction.commit(); }

        };
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(listener);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(listener);
    }*/



