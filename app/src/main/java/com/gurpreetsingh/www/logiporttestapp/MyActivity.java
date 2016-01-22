package com.gurpreetsingh.www.logiporttestapp;

import android.app.Application;

import com.parse.Parse;

public class MyActivity extends Application {

    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getString(R.string.PARSE_APP_ID), getString(R.string.PARSE_CLIENT_KEY));
    }
}
