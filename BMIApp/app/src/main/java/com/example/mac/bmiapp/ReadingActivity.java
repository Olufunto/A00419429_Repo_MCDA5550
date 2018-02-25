package com.example.mac.bmiapp;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;

import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import static com.example.mac.bmiapp.MainActivity.login_usr;

public class ReadingActivity extends AppCompatActivity {
    String l_usr;
    Button calc, next;
    ConstraintLayout ll;
    EditText v_weight, v_height;
    TextView v_bmi;
    Double v_bmi_calc;
    DBHelper appdb;

    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String formattedDate = df.format(c);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Log.i("DBcall", "About to make a db call");
        appdb = new DBHelper(this);
        Log.i("AndroidCall", "No Motherhood exception");
        v_height = (EditText) findViewById(R.id.editTextHeight);
        v_weight = (EditText) findViewById(R.id.editTextWeight);
        v_bmi = (TextView) findViewById(R.id.bmiTextView);
        ll = (ConstraintLayout) findViewById(R.id.readingLayout);
        calc = (Button) findViewById(R.id.calcButton);
        Log.i("Appcall", "Height is " + v_weight);
        Log.i("Appcall", "Weight is " + v_weight);
        Log.i("Appcall", "Abt to call the OnClickListener");
        next = (Button) findViewById(R.id.summaryButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(ReadingActivity.this,
                        SummaryActivity.class);
                startActivity(newIntent);
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v_bmi_calc = 0.0;
                l_usr = login_usr;
                Log.i("AppCall", "In the on click event now");
                Log.i("AppCall", "Before the calculation  " + v_bmi_calc);
                v_bmi_calc = Double.parseDouble(v_weight.getText().toString()) / Math.pow(Double.parseDouble(v_height.getText().toString()), 2);
                v_bmi_calc = v_bmi_calc * 10000;
                Log.i("Appcall", "After the calculation __BMI is " + v_bmi_calc);

                v_bmi.setText("" + v_bmi_calc.toString());
                Log.i("Appcall", "Successful up to Heaven");

                appdb.insertReading(l_usr,
                        formattedDate,
                        v_weight.getText().toString(),
                        v_height.getText().toString(),
                        v_bmi_calc.toString());
                Log.i("Dbcall", "Reading inserted .." + l_usr + ".." + formattedDate + ".." + v_weight.getText().toString() + ".." + v_height.getText().toString() + ".." + v_bmi_calc.toString());


                if (v_bmi_calc <= 24.9 && v_bmi_calc >= 18.5) {
                    ll.setBackgroundColor(Color.BLUE);
                } else {
                    if (v_bmi_calc <= 29.9 && v_bmi_calc >= 25) ll.setBackgroundColor(Color.GREEN);
                    else {
                        if (v_bmi_calc <= 39.9 && v_bmi_calc >= 30)
                            ll.setBackgroundColor(Color.YELLOW);
                        else {
                            if (v_bmi_calc > 40) {

                                ll.setBackgroundColor(Color.RED);
                            }
                        }
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("Appcall", "inside OptionItemsSelected");
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Password Change", Toast.LENGTH_SHORT).show();
                Intent settings = new Intent(this, PasswordChgActivity.class);
                startActivity(settings);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


}
