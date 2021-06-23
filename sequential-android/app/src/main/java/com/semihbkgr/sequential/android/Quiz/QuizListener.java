package com.semihbkgr.sequential.android.Quiz;

public interface QuizListener {

    public void nextQuestion();
    public void selection();
    public void trueAnswer();
    public void falseAnswer();
    public void quizDone();

}