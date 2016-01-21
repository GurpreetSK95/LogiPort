package com.gurpreetsingh.www.logiporttestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainScreen extends AppCompatActivity {

    String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Splash.loginObject.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Success!
                    objectId = Splash.loginObject.getObjectId();

                } else {
                    // Failure!
                    e.printStackTrace();
                }
            }
        });
        final TextView tv = (TextView) findViewById(R.id.TextView);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.fromLocalDatastore();
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    String name = object.get("LoginID").toString();
                    tv.setText(name);

                } else {
                    e.printStackTrace();
                }
            }
        });

    }

}
