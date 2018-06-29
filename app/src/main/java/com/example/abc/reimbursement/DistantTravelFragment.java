package com.example.abc.reimbursement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DistantTravelFragment extends Fragment implements View.OnClickListener {
    private View mMeal,mTravel,mStay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.distant_travel_fragment, viewGroup, false);
        mMeal = view.findViewById(R.id.button12);
        mTravel = view.findViewById(R.id.button22);
        mStay = view.findViewById(R.id.button32);

        mMeal.setOnClickListener(this);
        mTravel.setOnClickListener(this);
        mStay.setOnClickListener(this);


        return view;
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
    public void onClick (View v){
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
        transaction.commit();


    }


}

