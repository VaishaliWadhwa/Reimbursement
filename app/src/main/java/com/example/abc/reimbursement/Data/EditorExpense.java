package com.example.abc.reimbursement.Data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.abc.reimbursement.R;

public class EditorExpense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_editor_expense);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;





        getWindow().setLayout((int)(width*.80),(int)(height*.42));
        Button button = (Button)findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }; });
            Button button2 = (Button)findViewById(R.id.Back);
        button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                };


});
    } }
