package com.smh.sequential.Quiz;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.smh.sequential.Setting.Setting;

import javax.net.ssl.HandshakeCompletedEvent;

public class Quiz {

    private QuestionGenerator questionGenerator;
    private int trueAnswerNumber;
    private boolean answered;

    private Question currentQuestion;

    private QuizListener quizListener;

    private Context context;

    public Quiz(Context context,String listName){

        this.context=context;

        questionGenerator=new QuestionGenerator(context,listName, Setting.QUIZ_QUESTION_NUMBER);
        trueAnswerNumber=0;
        answered=false;

        currentQuestion=null;

        Log.i(getClass().getName(), "Quiz: quiz object created");

        currentQuestion=questionGenerator.generateQuestion(QuestionGenerator.QuestionType.random);


    }


    public void selection(int index){

        answered=true;

        if(quizListener!=null){
            quizListener.selection();
            if(currentQuestion.getChoiceByIndex(index).equals(currentQuestion.getTrueAnswer())){
                quizListener.trueAnswer();
                Log.i(getClass().getName(), "selection: true answer selected");
            }else{
                quizListener.falseAnswer();
                Log.i(getClass().getName(), "selection: false answer selected");
            }

        }else{
            if(currentQuestion.getChoiceByIndex(index).equals(currentQuestion.getTrueAnswer())){
                Log.i(getClass().getName(), "selection: true answer selected");
            }else{
                Log.i(getClass().getName(), "selection: false answer selected");
            }
            if(questionGenerator.checkForQuestionLimit()){
                currentQuestion=questionGenerator.generateQuestion(QuestionGenerator.QuestionType.random);
                Log.i(getClass().getName(), "nextQuestion: next question");
            }
        }

    }

    public void next(){

        answered=false;

        if(quizListener!=null){
            if(questionGenerator.checkForQuestionLimit()){
                currentQuestion=questionGenerator.generateQuestion(QuestionGenerator.QuestionType.random);
                quizListener.nextQuestion();
                Log.i(getClass().getName(), "nextQuestion: next question");
            }else{
                quizListener.quizDone();
            }
        }else{
            if(questionGenerator.checkForQuestionLimit()){
                currentQuestion=questionGenerator.generateQuestion(QuestionGenerator.QuestionType.random);
                Log.i(getClass().getName(), "nextQuestion: next question");
            }
        }
    }

    public void start(){
        next();
    }

    public Question getCurrentQuestion(){
        return currentQuestion;
    }

    public int getTrueAnswerNumber(){
        return trueAnswerNumber;
    }

    public void setQuizListener (QuizListener quizListener){
        this.quizListener=quizListener;
    }

    public boolean isAnswered(){
        return answered;
    }

}
