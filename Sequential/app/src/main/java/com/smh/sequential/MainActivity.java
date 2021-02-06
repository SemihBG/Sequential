package com.smh.sequential;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.smh.sequential.Setting.Setting;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(getClass().getName(), "onCreate: main activity created");

    }

    @Override
    protected void onStart() {
        super.onStart();

        Animation animation=new AlphaAnimation(0f,1f);
        animation.setDuration(500);
        findViewById(R.id.backgroundLayout).setAnimation(animation);

        Setting.getInstance(getApplicationContext()).loadSharedPreference(getApplicationContext().getSharedPreferences(
                Contracts.SETTINGS_SHARED_PREFERENCES_NAME,MODE_PRIVATE));

    }

    @Override
    protected void onResume() {
        super.onResume();

        Animation animation=new AlphaAnimation(0f,1f);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getClass().getName(), "onAnimationStart: animation started");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getClass().getName(), "onAnimationEnd: animation end");
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        animation.setDuration(1500);

        findViewById(R.id.logoImage).setAnimation(animation);

    }


}

