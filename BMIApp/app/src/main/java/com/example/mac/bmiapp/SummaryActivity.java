package com.example.mac.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import android.util.Log;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;


public class SummaryActivity extends AppCompatActivity {
    private ListView obj;
    DBHelper AppDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        AppDB = new DBHelper(this);
        Log.d("Appcall","Just intialized DB helper");
      //  ArrayList array_list = AppDB.getAllReading();
        Log.d("Appcall","Appdb.getAllReading has just been called");

        populateListView ();
      //  ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array_list);
      //  obj = (ListView)findViewById(R.id.summaryListView);
      //  obj.setAdapter(arrayAdapter);
    /*    obj.setOnItemClickListener(new OnItemClickListener(){
            @Override
           public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3){

                int id_To_Search = arg2 + 1;

               // Bundle dataBundle = new Bundle();
             //   dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),SummaryActivity.class);

           //     intent.putExtras(dataBundle);
                startActivity(intent);

            }


        }); */
    }


    public void populateListView (){

        Cursor cursor = AppDB.getReading();

        String [] fromfieldName = new String [] {DBHelper.SMTB_READING_ID,DBHelper.SMTB_READING_USR,DBHelper.SMTB_READING_CDATE,DBHelper.SMTB_READING_BMI};
        int [] toViewId = new int [] {R.id.idtextView,R.id.usrtextView,R.id.datetextView,R.id.bmiSumtextView};
        SimpleCursorAdapter AppCursorAdapter;
        AppCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_summary,cursor,fromfieldName,toViewId,0);
    ListView appListView = (ListView) findViewById(R.id.summaryListView);
    appListView.setAdapter(AppCursorAdapter);
    }
}



