package com.example.mac.bmiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    Button button;
    EditText tusrname, tpwd, thcn, tdob, temail;
    DBHelper appdb ;

    public static String usr;



    Date c = Calendar.getInstance().getTime();


    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String formattedDate = df.format(c);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appdb = new DBHelper(this);


        Log.d("Checking here ","Checking here ");
        tusrname = (EditText) findViewById(R.id.nameEditText);
        tpwd = (EditText) findViewById(R.id.pwdEditText);
        thcn = (EditText) findViewById(R.id.hcnEditText);
        tdob = (EditText) findViewById(R.id.dobEditText);
        temail = (EditText) findViewById(R.id.emailEditText);
        button = (Button) findViewById(R.id.registerSubmitButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usr = tusrname.getText().toString();
                if (!tusrname.getText().toString().equals("") ||
                        !tpwd.getText().toString().isEmpty() ||
                        !temail.getText().toString().isEmpty()) {
                    Log.d("DBCall","Abt to hit db ");
                    Log.i("DBCall","~~~~~~~~~~");
                    appdb.insertUser(tusrname.toString(),
                            tpwd.getText().toString(),
                            temail.getText().toString(),
                            formattedDate, tdob.getText().toString(),
                            thcn.getText().toString());
                    Log.d("DBcall","Inserted successfully to SMTBUSER"+tusrname.toString());

                    Log.d("Saving record ","Saving record ");
                    Toast.makeText(getApplicationContext(),
                            "Saving Record..",Toast.LENGTH_SHORT).show();
                }
                ;
            }
        });

    }



}
