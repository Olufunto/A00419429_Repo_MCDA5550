package com.example.mac.bmiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static javax.crypto.Cipher.DECRYPT_MODE;


public class MainActivity extends Activity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    public static String login_usr, login_pwd;
    public String AES = "AES";
    public String v_enryptPassword;

    DBHelper mydb;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);

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
            login_pwd = ed2.getText().toString();
            Log.i("DbCall","Database called  ");
            Log.i("DbCall","Password entered by user is before if   "+login_pwd);
            try {
                Log.d("AppCall","Abt to check the encryption function"+encryptpwd(login_pwd,login_usr));
                v_enryptPassword = encryptpwd(login_pwd,login_usr);
                Log.d("AppCall","Password encrypted from Main as "+v_enryptPassword);
            } catch (Exception e) {
                e.printStackTrace();
            }

           // if(login_pwd.equals(mydb.getPassword(login_usr))) {
                if(v_enryptPassword.equals(mydb.getPassword(login_usr))) {
                Toast.makeText(getApplicationContext(),
                        "Redirecting...",Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(MainActivity.this,
                        ReadingActivity.class);
                startActivity(myIntent);



            }else{
                Log.d("Dbcall","Password from database is "+mydb.getPassword(login_usr));
                Log.i("DbCall","Password in else is   "+login_pwd);
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
/*
private String decryptpwd (String outputString, String pwd){
    SecretKeySpec key = null;
    try {
        key = generateKey(pwd);
    } catch (Exception e) {
        e.printStackTrace();
    }
    Cipher c = null;
    try {
        c = Cipher.getInstance(AES);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    } catch (NoSuchPaddingException e) {
        e.printStackTrace();
    }
    c.init(DECRYPT_MODE,key);
        byte [] decodedValue = Base64.decode(outputString,Base64.DEFAULT);
        byte [] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue ;

}
*/

public String encryptpwd (String Data, String pwd) throws Exception {
    SecretKeySpec key = generateKey(pwd);
    Cipher c = Cipher.getInstance(AES);
    c.init(Cipher.ENCRYPT_MODE,key);
    byte [] encVal = c.doFinal(Data.getBytes());
    String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
    return encryptedValue;

}

    public SecretKeySpec generateKey(String pwd) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte [] bytes = pwd.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
            byte [] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
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










