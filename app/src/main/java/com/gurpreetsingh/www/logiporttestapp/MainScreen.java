package com.gurpreetsingh.www.logiporttestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import static com.parse.ParseUser.getCurrentUser;

public class MainScreen extends AppCompatActivity {

    String userName;
    ParseObject object;
    EditText note, number, remarks;
    Switch done;
    boolean switchOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = getCurrentUser().getUsername();

        TextView userNameTV = (TextView) findViewById(R.id.UserNameTV);
        userNameTV.setText(userName);

        note = (EditText) findViewById(R.id.NoteEditText);
        number = (EditText) findViewById(R.id.NumberEditText);
        remarks = (EditText) findViewById(R.id.RemarksEditText);
        done = (Switch) findViewById(R.id.DoneSwitch);
        done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchOP = true;
                } else {
                    switchOP = false;
                }
            }
        });
        Button button = (Button) findViewById(R.id.MainScreenButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (note.getText().toString().length() != 0 && number.getText().toString().length() != 0 && remarks.getText().toString().length() != 0) {

                        object = new ParseObject("MyClass");
                        object.put("NOTE", note.getText().toString());
                        object.put("NUMBER", number.getText().toString());
                        object.put("REMARKS", remarks.getText().toString());
                        object.put("DONE", switchOP);
                        object.saveInBackground();
                        Toast.makeText(MainScreen.this, "Saved", Toast.LENGTH_SHORT).show();
                        setAllNull();
                    } else {
                        Toast.makeText(MainScreen.this, "Please enter some data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainScreen.this);
                    builder1.setMessage("Please connect to network");
                    builder1.setCancelable(true);
                    builder1.setIcon(android.R.drawable.ic_dialog_alert);
                    builder1.setPositiveButton(
                            "OK",
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

    private void setAllNull() {
        note.setText("");
        number.setText("");
        remarks.setText("");
        done.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar i\f it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.LogOut) {
            getCurrentUser().logOutInBackground();
            Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainScreen.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.credits) {
            Toast.makeText(MainScreen.this, "Created by GURPREET SINGH\nfor LogiPort", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
