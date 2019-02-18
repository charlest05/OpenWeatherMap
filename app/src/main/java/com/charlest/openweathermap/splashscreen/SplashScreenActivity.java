package com.charlest.openweathermap.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.charlest.openweathermap.R;
import com.charlest.openweathermap.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler mPostDelayedHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mPostDelayedHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPostDelayedHandler.removeCallbacksAndMessages(null);
    }
}
