package com.tawk.carl.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    public static final String PROFILE_NAME = "profileName";
    private Button buttonname;
    private EditText editText;
    private SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonname = (Button) findViewById(R.id.tawklogin);
        editText = (EditText) findViewById(R.id.email);
        preferences = getSharedPreferences(
                "1111", Context.MODE_PRIVATE);
        buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                if (editText.getText().length() < 5) {
                    Toast.makeText(SplashActivity.this, "You have too short a name, dummy!!!!",
                            Toast.LENGTH_LONG).show();
                } else {
                    preferences.edit().putString(PROFILE_NAME,editText.getText().toString())
                            .apply();
                    startActivity(intent);

                }
            }

        });
    }
}
