package com.gurpreetsingh.www.logiporttestapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginET = (EditText) findViewById(R.id.LoginEditText);
        final EditText passwordET = (EditText) findViewById(R.id.PasswordEditText);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    loginID = loginET.getText().toString();
                    password = passwordET.getText().toString();

                    int idLength = checkLength(loginID);
                    int pwLength = checkLength(password);
                    if (idLength == 0 || pwLength == 0) {
                        Toast.makeText(LoginActivity.this, "Please enter Information", Toast.LENGTH_SHORT).show();
                    } else {
                        progress = new ProgressDialog(LoginActivity.this);
                        progress.setTitle("Logging in...");
                        progress.setMessage("Please be patient");
                        progress.show();
                        ParseUser.logInInBackground(loginID, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e != null) {
                                    Toast.makeText(LoginActivity.this
                                            , "Wrong Credentials\nPlease try again", Toast.LENGTH_SHORT).show();
                                    progress.dismiss();
                                    e.printStackTrace();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, MainScreen.class);
                                    startActivity(intent);
                                    progress.dismiss();
                                }
                            }
                        });
                    }
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                    builder1.setMessage("Please connect to network");
                    builder1.setCancelable(true);
                    builder1.setIcon(android.R.drawable.ic_dialog_alert);
                    builder1.setNeutralButton(
                            "Dismiss",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
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


