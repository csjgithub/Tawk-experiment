package com.tawk.carl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button myProfileButton = (Button) findViewById(R.id.my_profile);
        Button otherProfileButton = (Button) findViewById(R.id.information_page);
        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.information_page) {
                    Intent intent = new Intent(MainActivity.this, InformationActivity.class);
                    startActivity(intent);
                } else if (v.getId() == R.id.my_profile) {
                    Log.d("1111", "myProfile");
                    Intent intent = new Intent(MainActivity.this, MyProfile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.not_implemented_text,
                            Toast.LENGTH_LONG).show();
                }
            }
        };
        otherProfileButton.setOnClickListener(onClickListener);
        myProfileButton.setOnClickListener(onClickListener);

    }

}
