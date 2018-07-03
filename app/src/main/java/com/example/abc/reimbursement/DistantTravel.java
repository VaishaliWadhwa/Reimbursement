package com.example.abc.reimbursement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DistantTravel extends AppCompatActivity implements View.OnClickListener {
    private View mMeal,mTravel,mStay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distant_travel); }
    /*  @Override
      public  void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          Button btn1 = (Button)findViewById(R.id.button1);
          btn1.setOnClickListener(this);
          Button btn2 = (Button)findViewById(R.id.button2);
          btn2.setOnClickListener(this);
  */
    @Override
    public void onClick (View v){
        Fragment fragment = null;
        if (v.getId() == R.id.button12) {
            fragment = new DistantTravelMealFragment();
        } else if (v.getId() == R.id.button22) {
            fragment = new DistantTravelTravelActivity();

        } else if (v.getId() == R.id.button32) {
            fragment = new DistantTravelStayFragment();

        }
        android.app.FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        //android.app.FragmentTransaction replace = transaction.replace(R.id.fragment2, fragment);
        transaction.commit();


    }


}




