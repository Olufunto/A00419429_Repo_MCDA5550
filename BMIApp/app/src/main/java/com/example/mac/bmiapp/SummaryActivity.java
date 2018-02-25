package com.example.mac.bmiapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;


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
        Log.d("Appcall", "Just intialized DB helper");

        Log.d("Appcall", "Appdb.getAllReading has just been called");

        populateListView();

    }


    public void populateListView() {

        Cursor cursor = AppDB.getReading();

        String[] fromfieldName = new String[]{DBHelper.SMTB_READING_ID, DBHelper.SMTB_READING_USR, DBHelper.SMTB_READING_CDATE, DBHelper.SMTB_READING_BMI};
        int[] toViewId = new int[]{R.id.idtextView, R.id.usrtextView, R.id.datetextView, R.id.bmiSumtextView};
        SimpleCursorAdapter AppCursorAdapter;
        AppCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_summary, cursor, fromfieldName, toViewId, 0);
        ListView appListView = (ListView) findViewById(R.id.summaryListView);
        appListView.setAdapter(AppCursorAdapter);
    }
}



