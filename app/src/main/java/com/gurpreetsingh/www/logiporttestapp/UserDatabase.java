package com.gurpreetsingh.www.logiporttestapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gurpreet Singh on 20-Jan-16.
 */
public class UserDatabase extends SQLiteOpenHelper {

    private static String DBname = "MyUserDatabase";
    private static int DBversion = 1;
    private String tableName = "MyTable";
    private String query = "CREATE TABLE " + tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, LOGIN_ID TEXT, PASSWORD TEXT);";
    String loginId;
    String password;

    public UserDatabase(Context context, String loginId, String password) {
        super(context, DBname, null, DBversion);
        this.loginId = loginId;
        this.password = password;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        ContentValues cv = new ContentValues();
        cv.put("LOGIN_ID", loginId);
        cv.put("PASSWORD", password);
        db.insert(tableName, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

}


