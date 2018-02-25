package com.example.mac.bmiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.util.Base64;

import android.widget.Toast;

import java.security.MessageDigest;


import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;


import static com.example.mac.bmiapp.MainActivity.login_usr;


public class PasswordChgActivity extends AppCompatActivity {
    DBHelper appdb;
    String l_usr;
    public String AES = "AES";


    EditText v_newpwd, v_retyp_pwd;
    String pwd, v_enryptPassword, v_enryptPassword2, pwd2;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_chg);
        appdb = new DBHelper(this);

        v_newpwd = (EditText) findViewById(R.id.newPwdTextView);
        v_retyp_pwd = (EditText) findViewById(R.id.reTypNewPwdTextView);
        save = (Button) findViewById(R.id.savePwdButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l_usr = login_usr;
                pwd = v_newpwd.getText().toString();
                pwd2 = v_retyp_pwd.getText().toString();


                try {
                    Log.d("AppCall", "Abt to check the encryption function" + encryptpwd(pwd, login_usr));
                    v_enryptPassword = encryptpwd(pwd, login_usr);
                    v_enryptPassword2 = encryptpwd(pwd2, login_usr);
                    if (v_enryptPassword.equals(v_enryptPassword2)) {
                        Log.d("AppCall", "Password encrypted from Main as " + v_enryptPassword);
                        appdb.updatePwd(login_usr, v_enryptPassword);
                        Toast.makeText(getApplicationContext(),
                                "Password Changed.." + v_enryptPassword, Toast.LENGTH_LONG).show();
                    }
                    {
                        Toast.makeText(getApplicationContext(),
                                "Passwords Do not Match", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });


    }

    public String encryptpwd(String Data, String pwd) throws Exception {
        SecretKeySpec key = generateKey(pwd);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;

    }

    public SecretKeySpec generateKey(String pwd) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = pwd.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
