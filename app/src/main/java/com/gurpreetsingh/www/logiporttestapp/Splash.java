package com.gurpreetsingh.www.logiporttestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

public class Splash extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (ParseUser.getCurrentUser() != null) {
            intent = new Intent(this, MainScreen.class);
            startActivity(intent);
        } else {
/*
            //Made to register user on first run: UserName and Password pre-defined
            boolean firstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstRun", true);
            if (firstRun) {
                ParseUser logiport = new ParseUser();
                logiport.setUsername("task");
                logiport.setPassword("logitask");
                logiport.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(Splash.this, "SIGN-UP SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
                // Save the state
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("firstRun", false)
                        .commit();
            }
*/
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
