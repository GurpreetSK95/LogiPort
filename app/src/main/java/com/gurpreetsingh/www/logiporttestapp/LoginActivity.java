package com.gurpreetsingh.www.logiporttestapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    String loginID;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginET = (EditText) findViewById(R.id.LoginEditText);
        final EditText passwordET = (EditText) findViewById(R.id.PasswordEditText);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                ProgressDialog progress = new ProgressDialog(LoginActivity.this);
                progress.setTitle("Logging in...");
                progress.setMessage("Please be patient");
                progress.show();

                loginID = loginET.getText().toString();
                password = passwordET.getText().toString();

                int idLength = checkLength(loginID);
                int pwLength = checkLength(password);
                if (idLength == 0 || pwLength == 0) {
                    Toast.makeText(LoginActivity.this, "Please enter Information", Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logInInBackground(loginID, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e != null) {
                                Snackbar.make(v, "Wrong Credentials\nPlease try again", Snackbar.LENGTH_LONG).show();
                                e.printStackTrace();
                            } else {
                                Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });
    }

    private int checkLength(String input) {
        return input.length();
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}


