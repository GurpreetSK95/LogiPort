package com.gurpreetsingh.www.logiporttestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class Splash extends AppCompatActivity {

    Intent intent;
    String name, pw, objectId;
    static ParseObject loginObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "xCZkxJAECrzIstFxSYKYAQUHllOdGU1RcRIMefGR", "5uzD5OvR87TZDSvFtMfhvzfKsf5GsOVgPEefRGfn");

        /*boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun) {

            name = "null";
            pw = "null";

            Toast.makeText(Splash.this, "Splash.preferences", Toast.LENGTH_SHORT).show();
            // Save the state
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun", false)
                    .commit();
        }*/
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Login");
        query.fromLocalDatastore();
        query.getInBackground("5uzD5OvR87TZDSvFtMfhvzfKsf5GsOVgPEefRGfn", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    name = object.get("LoginID").toString();
                    pw = object.get("Password").toString();
                    Toast.makeText(Splash.this, "Splash.done", Toast.LENGTH_SHORT).show();
                } else {
                    e.printStackTrace();
                }
            }
        });
*/
        if (loginObject == null) {

            loginObject = new ParseObject("Login");
            loginObject.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Success!
                        objectId = loginObject.getObjectId();
                        loginObject.put("LoginID", "task");
                        loginObject.put("Password", "logitask");
                    } else {
                        // Failure!
                        e.printStackTrace();
                    }
                }
            });




            //save
            loginObject.saveInBackground();

 /*           ParseObject object = ParseObject.createWithoutData("Login", objectId);
            object.fetchFromLocalDatastoreInBackground(new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        // object will be your game score
                        Bundle info = new Bundle();
                        info.putString("ID", object.getString("LoginID"));
                        info.putString("Password", object.getString("Password"));
                        Toast.makeText(Splash.this, "Bundle sent!", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Splash.this, LoginActivity.class);
                        intent.putExtra("BUNDLE", info);
                        startActivity(intent);
                    } else {
                        // something went wrong
                        e.printStackTrace();
                    }
                }
            });     */

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Login");       //Query Not Generated, no result found ERROR
            query.fromLocalDatastore();
            query.getInBackground(objectId, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                    //    name = loginObject.getString("LoginID");
                    //    pw = loginObject.getString("Password");
                        Bundle info = new Bundle();
                        info.putString("ID", object.getString("LoginID"));
                        info.putString("Password", object.getString("Password"));
                        Toast.makeText(Splash.this, "Bundle sent!", Toast.LENGTH_SHORT).show();
                        intent = new Intent(Splash.this, LoginActivity.class);
                        intent.putExtra("BUNDLE", info);
                        startActivity(intent);
                    } else {
                        e.printStackTrace();
                    }
                }
            });



        } else {
            intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
