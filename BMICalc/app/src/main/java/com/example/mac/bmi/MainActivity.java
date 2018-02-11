package com.example.mac.bmi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD","DATE"},
                null,null,null,null,null);

         if (cursor.moveToFirst()){
        String  name = cursor.getString(0);
        EditText editname = (EditText) findViewById(R.id.fnameText);
             editname.setText(name);
             String  password = cursor.getString(1);
             EditText editPassword = (EditText) findViewById(R.id.passwordText);
             editname.setText(password);
             String  date = cursor.getString(2);
             EditText editDate = (EditText) findViewById(R.id.dobText);
             editname.setText(date);
             String  Health = cursor.getString(3);
             EditText editHealth = (EditText) findViewById(R.id.hcnText);
             editname.setText(Health);

        }

        cursor.close();
        db.close();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void hellobutton (View v)
    {
        Intent intent=new Intent (this,Main2Activity.class);
    startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
