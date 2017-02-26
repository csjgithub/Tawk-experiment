package com.tawk.experiment.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        SharedPreferences preferences = getSharedPreferences(
                "1111", Context.MODE_PRIVATE);
        String profileName = preferences.getString(SplashActivity.PROFILE_NAME, null);
        if (profileName == null){
            finish();
        }else{
            TextView profileNameView = (TextView)findViewById(R.id.profile_name);
            profileNameView.setText(profileName);

        }

    }
}
