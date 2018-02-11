package com.example.mac.bmi;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //get the height

            }


        });
    }

    public void calcButton(View v) {
        EditText height = (EditText) findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightAsInt = Double.parseDouble(value);
        System.out.println("Here is the height " + heightAsInt);

        // Repeat for Weight


        EditText weight = (EditText) findViewById(R.id.textWeight);
        String weightvalue = weight.getText().toString();
        Double weightAsInt = Double.parseDouble(weightvalue);
        System.out.println("Here is the height " + weightAsInt);


        Double calc = (weightAsInt / (heightAsInt * heightAsInt));
        EditText result = (EditText) findViewById(R.id.resultText);
        // use DecimalFormat("0.##") to limit to 2 decimal places

        result.setText(Double.toString(calc));

    }


}








