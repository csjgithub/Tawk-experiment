package com.tawk.carl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private Button buttonname;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonname = (Button) findViewById(R.id.tawklogin);
        editText = (EditText) findViewById(R.id.email);
        buttonname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                if (editText.getText().length() < 5) {
                    Toast.makeText(SplashActivity.this, "You have too short a name, dummy!!!!", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);

                }
            }

        });
    }
}
