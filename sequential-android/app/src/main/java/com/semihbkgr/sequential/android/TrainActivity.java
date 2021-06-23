package com.semihbkgr.sequential.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.semihbkgr.sequential.android.database.DatabaseFacade;
import com.semihbkgr.sequential.android.entity.Vocabulary;
import com.semihbkgr.sequential.android.setting.Setting;
import com.semihbkgr.sequential.android.util.DoubleFirstSecondClickListener;
import com.semihbkgr.sequential.android.util.Util;
import com.semihbkgr.sequential.android.util.VocabularyEffect;

import java.util.ArrayList;

public class TrainActivity extends AppCompatActivity {


    private ConstraintLayout dynamicLayout;
    private TextView vocabularyTextView;

    private Setting setting;

    private VocabularyEffect vocabularyEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        Log.i(getClass().getName(), "onCreate: activity created");

        dynamicLayout=findViewById(R.id.dynamicLayout);
        vocabularyTextView=findViewById(R.id.vocabulary);

        setting=Setting.getInstance(getApplicationContext());

        vocabularyEffect=new VocabularyEffect(getApplicationContext(),vocabularyTextView,
                MediaPlayer.create(getApplicationContext(),R.raw.keyboard_sound),
                MediaPlayer.create(getApplicationContext(),R.raw.delete_sound));

        dynamicLayout.setOnClickListener(new DoubleFirstSecondClickListener() {
            @Override
            public void onDoubleClick() {
                vocabularyEffect.getBack();
            }

            @Override
            public void onFirstClick() {
                vocabularyEffect.pause();
            }

            @Override
            public void onSecondClick() {
                vocabularyEffect.resume();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Cursor cursor=DatabaseFacade.getListCursorByIdFromIndex(getApplicationContext(),
                setting.getActiveListInformationDatabase().getId(),setting.getActiveListInformationDatabase().getIndex());

        ArrayList<Vocabulary> vocabularies= Util.convertVocabularyCursorToArrayList(cursor);

        vocabularyEffect.execute(vocabularies.toArray(new Vocabulary[]{}));

    }

    @Override
    protected void onPause() {
        super.onPause();
        vocabularyEffect.cancel(true);
    }
}
