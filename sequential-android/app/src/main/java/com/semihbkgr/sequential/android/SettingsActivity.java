package com.semihbkgr.sequential.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.semihbkgr.sequential.android.setting.Setting;

public class SettingsActivity extends AppCompatActivity {

    //Components
    private SeekBar speedBar;
    private TextView speedText;

    private Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Load components
        speedBar=findViewById(R.id.speedBar);
        speedText=findViewById(R.id.speedPersentageTextView);

        setting=Setting.getInstance(getApplicationContext());

        //Set components' attribute and event
        speedText.setText(setting.getSpeed()+"%");
        speedBar.setProgress(setting.getSpeed());
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int speedPseudo=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedPseudo=progress;
                speedText.setText(speedPseudo+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(getClass().getName(), "onStartTrackingTouch: speed changing is started");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                setting.setAndSaveSpeed(speedPseudo);
            }
        });

    }
}
