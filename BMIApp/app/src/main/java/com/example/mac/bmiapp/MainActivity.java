package com.example.mac.bmiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//


public class MainActivity extends Activity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    public static String login_usr;

    DBHelper mydb;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    b3= (Button)findViewById(R.id.registerButton);
    b1 = (Button)findViewById(R.id.button);
    ed1 = (EditText)findViewById(R.id.editText);
    ed2 = (EditText)findViewById(R.id.editText2);

    b2 = (Button)findViewById(R.id.button2);
    tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);



        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login_usr = ed1.getText().toString();
            if(ed1.getText().toString().equals("admin") &&
                    ed2.getText().toString().equals("admin")) {
                Toast.makeText(getApplicationContext(),
                        "Redirecting...",Toast.LENGTH_SHORT).show();
                //code for new activity goes here
                Intent myIntent = new Intent(MainActivity.this,
                        ReadingActivity.class);
                startActivity(myIntent);
              //  sendEmail();

            }else{
                Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                        tx1.setVisibility(View.VISIBLE);
                tx1.setBackgroundColor(Color.RED);
                counter--;
                tx1.setText(Integer.toString(counter));

                if (counter == 0) {
                    b1.setEnabled(false);
                }
            }
        }
    });

        b3.setOnClickListener(new View.OnClickListener(){
           @Override
                   public void onClick(View v){
                Intent newIntent = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(newIntent);



            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
}

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"akinyemifunto@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Something is about to happen!");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}










