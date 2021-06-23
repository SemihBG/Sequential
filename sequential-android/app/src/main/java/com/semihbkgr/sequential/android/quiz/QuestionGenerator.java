package com.semihbkgr.sequential.android.quiz;

import android.content.Context;
import android.util.Log;

import com.semihbkgr.sequential.android.database.DatabaseFacade;
import com.semihbkgr.sequential.android.entity.Vocabulary;

import java.util.Random;

class QuestionGenerator {

    private Context context;

    private String listName;
    private int questionNumber;
    private int listSize;

    private int questionLimit;

    private Random random;

    enum QuestionType{
        random,
        ett,
        tte;
    }

    QuestionGenerator(Context context,String listName,int questionLimit){

        this.context=context;
        this.listName = listName;
        this.questionLimit=questionLimit;

        questionNumber =0;


        listSize = DatabaseFacade.getSizeOfList(context,listName);

        random=new Random();

    }

    //Generate question with given enum type
    //Throws if generator reached the question limit
    Question generateQuestion(QuestionType questionType){

        if(questionNumber>=questionLimit){
            throw new IllegalStateException();
        }

        Question question=new Question();

        questionNumber++;

        boolean EngToTr=random.nextBoolean();

        question.setId(questionNumber);

        boolean engToTr;

        if(questionType==QuestionType.random){
            engToTr=random.nextBoolean();
        }else {
            engToTr= questionType != QuestionType.ett;
        }

        for(int i=0;i<4;i++){

            int tableItemId=random.nextInt(listSize)+1;

            Vocabulary vocabulary=DatabaseFacade.getVocabularyById(context,listName,tableItemId);

            Choice choice=generateChoice(vocabulary,engToTr);

            if(question.checkDuplication(choice)) {
                i--;
                continue;
            }

            if(i==0){
                question.setTrueAnswer(choice);
            }

            question.addChoice(choice);

        }

        question.shuffleChoices();

        Log.i(getClass().getName(), "generateQuestion: question generated");
        return question;

    }

    //Returns true if not reached limit
    boolean checkForQuestionLimit(){
        return questionNumber<questionLimit;
    }

    private Choice generateChoice(Vocabulary vocabulary,boolean engToTr){
        Choice choice=null;
        if(engToTr){
            choice=new Choice(vocabulary.getEng(),vocabulary.getTr());
        }else{
            choice=new Choice(vocabulary.getTr(),vocabulary.getEng());
        }
        return choice;
    }

    int getQuestionNumber(){
        return questionNumber;
    }

}
