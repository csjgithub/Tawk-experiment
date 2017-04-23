package com.tawk.experiment.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    public static final String PREF_FILE = "1111";
    public static final String TAG = "Hello";
    public static final String PROFILE_NAME = "Profile Name";
    public static final String PROFILE_ID = "Profile ID";
    CallbackManager callbackManager;
    private LoginButton loginButton;
    private AccessTokenTracker mAccessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Profile.getCurrentProfile() != null){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        final SharedPreferences preference = this.getSharedPreferences(PROFILE_NAME,Context.MODE_PRIVATE);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("public_profile");

        // Other app specific specialization
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginToMyFbApp();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            private ProfileTracker mProfileTracker;

            @Override
            public void onSuccess(LoginResult loginResult) {
                if(Profile.getCurrentProfile() == null) {
                    Log.v(TAG, "Profile is null");
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            Log.v(TAG, profile2.getFirstName());
                            mProfileTracker.stopTracking();
                            startMain();
                        }

                    };
                    mProfileTracker.startTracking();
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                }
                else {
                    Log.v(TAG, "Profile is NOT null");
                    startMain();
                }
            }

            private void startMain() {
                SharedPreferences.Editor editor = preference.edit();
                editor.putString(PROFILE_NAME, Profile.getCurrentProfile().getFirstName());
                //editor.putString("E-mail"), Profile.getCurrentProfile().;
                editor.putString(PROFILE_ID, Profile.getCurrentProfile().getId());
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                Log.v(TAG, "cancelled");
            }

            @Override
            public void onError(FacebookException e) {
                Log.v(TAG, e.getMessage());
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void loginToMyFbApp() {
        if (AccessToken.getCurrentAccessToken() != null) {
            mAccessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    mAccessTokenTracker.stopTracking();
                    if(currentAccessToken == null) {
                        //(the user has revoked your permissions -
                        //by going to his settings and deleted your app)
                        //do the simple login to FaceBook
                        //case 1
                        Log.v(TAG, "currentAccessToken == null");
                    }
                    else {
                        //you've got the new access token now.
                        //AccessToken.getToken() could be same for both
                        //parameters but you should only use "currentAccessToken"
                        //case 2
                        Log.v(TAG, "currentAccessToken != null");
                        fetchProfile();
                    }
                }
            };
            mAccessTokenTracker.startTracking();
            AccessToken.refreshCurrentAccessTokenAsync();
        }
        else {
            //do the simple login to FaceBook
            //case 1
        }
    }

    private void fetchProfile() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // this is where you should have the profile
                        Log.v("fetched info", object.toString());
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link"); //write the fields you need
        request.setParameters(parameters);
        request.executeAsync();
    }
}
