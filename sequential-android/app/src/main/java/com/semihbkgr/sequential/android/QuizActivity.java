package com.semihbkgr.sequential.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.semihbkgr.sequential.android.quiz.Choice;
import com.semihbkgr.sequential.android.quiz.Question;
import com.semihbkgr.sequential.android.quiz.Quiz;
import com.semihbkgr.sequential.android.quiz.QuizListener;
import com.semihbkgr.sequential.android.setting.Setting;


public class QuizActivity extends AppCompatActivity {

    private final int QUESTION_NUMBER=25;

    //Components
    private Button aButton,bButton,cButton,dButton;
    private TextView questionText,questionNumberText,activeListText;
    private View nextClickLayout;

    private Quiz quiz;

    private Setting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Log.i(getClass().getName(), "onCreate: quiz activity created");

        setting=Setting.getInstance(getApplicationContext());

        //Load components
        aButton=findViewById(R.id.aButton);
        bButton=findViewById(R.id.bButton);
        cButton=findViewById(R.id.cButton);
        dButton=findViewById(R.id.dButton);
        questionText=findViewById(R.id.questionTextView);
        questionNumberText=findViewById(R.id.questionNumberTextView);
        activeListText=findViewById(R.id.textViewActiveList);
        nextClickLayout=findViewById(R.id.backgroundLayout);

        quiz=new Quiz(getApplicationContext(), setting.getActiveListName());

        Button buttons[]=new Button[4];
        buttons[0]=aButton;
        buttons[1]=bButton;
        buttons[2]=cButton;
        buttons[3]=dButton;

        //Set components' attribute and event
        activeListText.setText(setting.getActiveListName());
        aButton.setOnClickListener((View view)->{
            if(quiz.isAnswered()){
                quiz.next();
            }else{
                quiz.selection(0);
            }
        });
        bButton.setOnClickListener((View view)->{
            if(quiz.isAnswered()){
                quiz.next();
            }else{
                quiz.selection(1);
            }
        });
        cButton.setOnClickListener((View view)->{
            if(quiz.isAnswered()){
                quiz.next();
            }else{
                quiz.selection(2);
            }
        });
        dButton.setOnClickListener((View view)->{
            if(quiz.isAnswered()){
                quiz.next();
            }else{
                quiz.selection(3);
            }
        });

        nextClickLayout.setOnClickListener((View view)->{
            if(quiz.isAnswered()){
                quiz.next();
            }
        });


        quiz.setQuizListener(new QuizListener() {

            @Override
            public void nextQuestion() {

                for(Button button:buttons){
                    button.setBackgroundColor(getResources().getColor(R.color.white,null));
                }

                Question question=quiz.getCurrentQuestion();
                questionText.setText(question.getTrueAnswer().getWord());

                aButton.setText(question.getChoiceByIndex(0).getMeaning());
                bButton.setText(question.getChoiceByIndex(1).getMeaning());
                cButton.setText(question.getChoiceByIndex(2).getMeaning());
                dButton.setText(question.getChoiceByIndex(3).getMeaning());

            }

            @Override
            public void selection() {

            }

            @Override
            public void trueAnswer() {
                buttons[quiz.getCurrentQuestion().getTrueAnswerIndex()].
                        setBackgroundColor(getResources().getColor(R.color.green,null));
            }

            @Override
            public void falseAnswer() {
                for(int i=0;i<buttons.length;i++){
                    if(i==quiz.getCurrentQuestion().getTrueAnswerIndex()){
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.green,null));
                    }else{
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.red,null));
                    }
                    Choice choice=quiz.getCurrentQuestion().getChoiceByIndex(i);
                    buttons[i].setText(choice.getMeaning()+" = "+choice.getWord());
                }
            }

            @Override
            public void quizDone() {

            }

        });

        quiz.start();


    }

}
