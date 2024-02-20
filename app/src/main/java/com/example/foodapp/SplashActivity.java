package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import com.example.foodapp.home_page.view.HomePageActivity;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler;
        handler = new Handler();
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFERENCE , Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userEmail != null && !userEmail.isEmpty()) {
                    Intent homeIntent = new Intent(SplashActivity.this, HomePageActivity.class);
                    startActivity(homeIntent);
                    finish(); // Optional: Close the splash screen activity
                }else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);
    }
}