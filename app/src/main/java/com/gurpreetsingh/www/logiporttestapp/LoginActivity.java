package com.gurpreetsingh.www.logiporttestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

public class LoginActivity extends AppCompatActivity {

    String loginID = "";
    String password = "";
    ParseObject loginObject;
    String name;
    String pw;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText registerET = (EditText) findViewById(R.id.LoginEditText);
        EditText passwordET = (EditText) findViewById(R.id.PasswordEditText);
        loginID = registerET.getText().toString();
        password = passwordET.getText().toString();

        Button button = (Button) findViewById(R.id.button);
/*        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.fromLocalDatastore();
        query.getInBackground("5uzD5OvR87TZDSvFtMfhvzfKsf5GsOVgPEefRGfn", new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    name = object.get("LoginID").toString();
                    pw = object.get("Password").toString();
                } else {
                    e.printStackTrace();
                }
            }
        });
        */
        Bundle bundle = this.getIntent().getBundleExtra("BUNDLE");
        name = bundle.getString("ID");
        pw = bundle.getString("PASSWORD");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginID.equals(name) && password.equals(pw)) {
                    Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "Wrong credentials!\nPlease try again.", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    protected void onPause() {
        super.onPause();
        finish();
    }
/*
    public void buttonClicked(View v) {



    }
    */
}


