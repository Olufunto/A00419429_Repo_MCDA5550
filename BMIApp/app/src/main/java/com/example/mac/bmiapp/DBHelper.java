package com.example.mac.bmiapp;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mac on 2/15/18.
 */



public class DBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "BMIdb.db";
    public static final String SMTB_TABLE_NAME = "smtb_user";
    public static final String SMTB_READING_TABLE_NAME = "smtb_reading";
   // public static final String SMTB_COLUMN_ID = "id";
    public static final String SMTB_COLUMN_USRNAME = "username";
    public static final String SMTB_COLUMN_PASSWORD = "password";
    public static final String SMTB_COLUMN_EMAIL = "email";
    public static final String SMTB_COLUMN_REGDT = "regdt";
    public static final String SMTB_COLUMN_DOB = "dob";
    public static final String SMTB_COLUMN_HCN = "hcn";
    public static final String SMTB_READING_ID = "_id";
    public static final String SMTB_READING_USR = "username";
    public static final String SMTB_READING_CDATE = "cdate";
    public static final String SMTB_READING_BMI = "bmi";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(
                "create table smtb_user " +
                        "(username text primary key,password text,email text,regdt text, dob text,hcn text)"
        );
        db.execSQL(
                "create table smtb_reading " +
                        "(username text ,cdate text,height text,weight text, bmi text)"

                //String username, String cdate, String height, String weight,String bmi
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS smtb_user");
        db.execSQL("DROP TABLE IF EXISTS smtb_reading");
        onCreate(db);
    }

    public boolean insertUser (String username, String password, String email, String regdt,String dob,String hcn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("regdt", regdt);
        contentValues.put("dob", dob);
        contentValues.put("hcn", hcn);
        db.insert("smtb_user", null, contentValues);
        return true;
    }

    public boolean insertReading (String username, String cdate, String height, String weight,String bmi) {

        SQLiteDatabase db = this.getWritableDatabase();

       // db.execSQL("drop table smtb_reading");
       // db.execSQL(
       //         "create table smtb_reading " +
       //                 "(username text ,cdate text,height text,weight text, bmi text)");
        ContentValues readingContentValues = new ContentValues();
        readingContentValues.put("username", username);
        readingContentValues.put("cdate", cdate);
        readingContentValues.put("height", height);
        readingContentValues.put("weight", weight);
        readingContentValues.put("bmi", bmi);
        db.insert("smtb_reading", null, readingContentValues);

        return true;
    }

    public Cursor getData(String  uname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from smtb_user where username="+uname+"", null );
        return res;
    }

    public Cursor getReading() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor response =  db.rawQuery( "select rowid as _id, a.* from smtb_reading a ", null );
        return response;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SMTB_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser ( String username, String password, String email, String regdt,String dob, String hcn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("regdt", regdt);
        contentValues.put("dob", dob);
        contentValues.put("hcn", hcn);
        db.update("smtb_user", contentValues, "username = ? ", new String[] { username } );
        return true;
    }

    public Integer deleteUser (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("smtb_user",
                "id = ? ",
                new String[] { username });
    }

    public ArrayList<String> getAllUser() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from smtb_user", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SMTB_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllReading() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from smtb_reading", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SMTB_READING_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}











