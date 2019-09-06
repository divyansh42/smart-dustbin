package com.jss.smartdustbin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.jss.smartdustbin.R;
import com.jss.smartdustbin.Utils.SmartDustbinApplication;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            Intent mainIntent;
            if(SmartDustbinApplication.getInstance().getAccessToken() == null)
                mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            else
                mainIntent = new Intent(SplashActivity.this, UserHomeActivity.class);
            startActivity(mainIntent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
