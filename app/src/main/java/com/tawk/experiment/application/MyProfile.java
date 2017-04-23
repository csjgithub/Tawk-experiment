package com.tawk.experiment.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
     SharedPreferences  preferences = this.getSharedPreferences(SplashActivity.PREF_FILE, Context.MODE_PRIVATE);

        if (preferences.getString(SplashActivity.PROFILE_ID,null) == null){
            Log.d("MyProfiletag", "Profile is null");
            finish();
        }else{
            TextView profileNameView = (TextView)findViewById(R.id.profile_name);
            profileNameView.setText(preferences.getString(SplashActivity.PROFILE_NAME, ""));
            ImageView profileImgView = (ImageView) findViewById(R.id.profile_picture);
            Picasso.with(this).load("http:// graph.facebook.com/" + preferences.getString(SplashActivity.PROFILE_ID, "") + "/picture?" +
                    "width=100&height=100").into(profileImgView);
        }

    }
}
