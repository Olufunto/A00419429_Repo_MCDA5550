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


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BMIdb.db";
    public static final String SMTB_TABLE_NAME = "smtb_user";
    public static final String SMTB_READING_TABLE_NAME = "smtb_reading";
    public static final String SMTB_READING_ID = "_id";
    public static final String SMTB_READING_USR = "username";
    public static final String SMTB_READING_CDATE = "cdate";
    public static final String SMTB_READING_BMI = "bmi";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
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


        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS smtb_user");
        db.execSQL("DROP TABLE IF EXISTS smtb_reading");
        onCreate(db);
    }

    public boolean insertUser(String username, String password, String email, String regdt, String dob, String hcn) {
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

    public boolean insertReading(String username, String cdate, String height, String weight, String bmi) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues readingContentValues = new ContentValues();
        readingContentValues.put("username", username);
        readingContentValues.put("cdate", cdate);
        readingContentValues.put("height", height);
        readingContentValues.put("weight", weight);
        readingContentValues.put("bmi", bmi);
        db.insert("smtb_reading", null, readingContentValues);

        return true;
    }

    public String getPassword(String usrname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlquery = "select username,password from smtb_user";
        Cursor cursor = db.rawQuery(sqlquery, null);
        String v_usrname, v_password;
        v_password = "No Data Found";
        if (cursor.moveToFirst()) {
            do {
                v_usrname = cursor.getString(0);
                if (v_usrname.equals(usrname)) {
                    v_password = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
            cursor.close();

        }
        return v_password;

    }


    public Cursor getData(String uname) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from smtb_user where username=" + uname + "", null);
        return res;
    }

    public Cursor getReading() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor response = db.rawQuery("select rowid as _id, a.* from smtb_reading a ", null);
        return response;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SMTB_TABLE_NAME);
        return numRows;
    }

    public boolean updateUser(String username, String password, String email, String regdt, String dob, String hcn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("regdt", regdt);
        contentValues.put("dob", dob);
        contentValues.put("hcn", hcn);
        db.update("smtb_user", contentValues, "username = ? ", new String[]{username});
        return true;
    }

    public boolean updatePwd(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        db.update("smtb_user", contentValues, "username = ? ", new String[]{username});
        return true;
    }

    public Integer deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("smtb_user",
                "id = ? ",
                new String[]{username});
    }

    public ArrayList<String> getAllUser() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from smtb_user", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(SMTB_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllReading() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from smtb_reading", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(SMTB_READING_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}











