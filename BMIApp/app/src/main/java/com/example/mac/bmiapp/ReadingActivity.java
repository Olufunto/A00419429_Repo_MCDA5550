package com.example.mac.bmiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Intent;

import static com.example.mac.bmiapp.RegisterActivity.usr;
import static com.example.mac.bmiapp.MainActivity.login_usr;

public class ReadingActivity extends AppCompatActivity {
    String l_usr;
    Button calc,next;
    EditText v_weight, v_height;
    TextView v_bmi;
    Double v_bmi_calc;
    DBHelper appdb ;
    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String formattedDate = df.format(c);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Log.i("DBcall","About to make a db call");
        appdb = new DBHelper(this);
        Log.i("AndroidCall","No Motherhood exception");
        v_height = (EditText) findViewById(R.id.editTextHeight);
        v_weight = (EditText) findViewById(R.id.editTextWeight);
        v_bmi = (TextView) findViewById(R.id.bmiTextView) ;
        calc = (Button)findViewById(R.id.calcButton);
        Log.i("Appcall","Height is "+v_weight);
        Log.i("Appcall","Weight is "+v_weight);
        Log.i("Appcall","Abt to call the OnClickListener");
        next= (Button)findViewById(R.id.summaryButton);

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
                l_usr = login_usr;
                Log.i("AppCall","In the on click event now");
                Log.i("AppCall","Before the calculation  "+v_bmi_calc);
                v_bmi_calc  = Double.parseDouble(v_weight.getText().toString())/Math.pow(Double.parseDouble(v_height.getText().toString()),2);
                Log.i("Appcall","After the calculation __BMI is "+v_bmi_calc);

                v_bmi.setText(""+v_bmi_calc.toString());
                Log.i("Appcall","Successful up to Heaven");

                appdb.insertReading(l_usr,
                        formattedDate,
                        v_weight.getText().toString(),
                         v_height.getText().toString(),
                        v_bmi_calc.toString());
                Log.i("Dbcall","Reading inserted .."+l_usr+".."+formattedDate+".."+v_weight.getText().toString()+".."+v_height.getText().toString()+".."+v_bmi_calc.toString());




            }
        });
    }
}
