package com.smh.sequential;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smh.sequential.Setting.Setting;
import com.smh.sequential.Utility.AnimationtUtil;

public class MenuActivity extends AppCompatActivity {

    //Activity components
    private ImageButton buttonStart;
    private ImageButton buttonSetting;
    private ImageButton buttonSound;
    private Button buttonLists;
    private TextView textViewActiveList;
    private ProgressBar progressList;
    private Button buttonTrain;
    private Button buttonQuiz;

    private Intent intent;

    private Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.i(getClass().getName(), "onCreate: activity menu created");

        //Get components
        buttonStart =findViewById(R.id.buttonStart);
        buttonSetting =findViewById(R.id.buttonSetting);
        buttonSound =findViewById(R.id.buttonSound);
        buttonLists =findViewById(R.id.buttonLists);
        textViewActiveList =findViewById(R.id.textViewActiveList);
        progressList=findViewById(R.id.progressList);
        buttonTrain=findViewById(R.id.buttonTrain);
        buttonQuiz=findViewById(R.id.buttonQuiz);

        setting=Setting.getInstance(getApplicationContext());

        //Load buttons click listener
        buttonStart.setOnClickListener((View view)-> {
            Log.i(getClass().getName(), "onCreate: clicked start button");
            if(setting.getActiveListInformationDatabase()!=null){
                AnimationtUtil.applyDefaultButtonEffect(view,true,true,false);
                AnimationtUtil.applyDefaultButtonEffect(buttonTrain,false,false,false);
                AnimationtUtil.applyDefaultButtonEffect(buttonQuiz,false,false,false);
            }
        });

        buttonSound.setOnClickListener((View view)-> {

            Log.i(getClass().getName(), "onCreate: clicked sound button");

            if(setting.isSound()){
                setting.changeAndSaveSound();
                buttonSound.setImageResource(R.drawable.sound_off);
            }else{
                setting.changeAndSaveSound();
                buttonSound.setImageResource(R.drawable.sound_on);
            }

        });

        buttonSetting.setOnClickListener((View view)-> {
            if(intent==null){
                Log.i(getClass().getName(), "onCreate: clicked setting button");
                intent=new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonLists.setOnClickListener((View view)-> {
            if(intent==null){
                Log.i(getClass().getName(), "onCreate: clicked lists button");
                intent=new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        buttonTrain.setOnClickListener((View view)->{
            if(intent==null){
                Log.i(getClass().getName(), "onCreate: clicked train button");
                intent=new Intent(getApplicationContext(),TrainActivity.class);
                startActivity(intent);
            }

        });

        buttonQuiz.setOnClickListener((View view)->{
            if(intent==null){
                Log.i(getClass().getName(), "onCreate: clicked quiz button");
                intent=new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(intent);
            }
        });

        buttonTrain.setVisibility(View.INVISIBLE);
        buttonQuiz.setVisibility(View.INVISIBLE);
        buttonTrain.setClickable(false);
        buttonQuiz.setClickable(false);

    }

    @Override
    protected void onStart() {
        super.onStart();

        intent=null;

        setSettingsOfComponents();
        setSettingsOfComponentsRegardingSettings();
    }


    private void setSettingsOfComponentsRegardingSettings(){
        Log.i(getClass().getName(), "setSettingsOfComponentsRegardingSettings: components set");
        if(!setting.isSound()){
            buttonSound.setImageResource(R.drawable.sound_off);
        }
        if(setting.getActiveListName()!=null){
            textViewActiveList.setText(setting.getActiveListName());
        }else{
            textViewActiveList.setText("");
        }
    }

    private void setSettingsOfComponents(){

        Log.i(getClass().getName(), "setSettingsOfComponents: components set");
        AnimationtUtil.applyDefaultButtonEffect(buttonStart,true,false,true);
        if(buttonTrain.getVisibility()==View.VISIBLE){
            AnimationtUtil.applyDefaultButtonEffect(buttonTrain,false,true,false);
        }
        if(buttonQuiz.getVisibility()==View.VISIBLE){
            AnimationtUtil.applyDefaultButtonEffect(buttonQuiz,false,true,false);
        }


    }


}
