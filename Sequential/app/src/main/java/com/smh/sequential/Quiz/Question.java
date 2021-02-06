package com.smh.sequential.Quiz;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    private int id;
    private Choice trueAnswer;
    private ArrayList<Choice> choices;

    Question(){
        choices=new ArrayList<>(4);
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public Choice getTrueAnswer() {
        return trueAnswer;
    }

    void setTrueAnswer(Choice trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public Choice getChoiceByIndex(int index){
        return choices.get(index);
    }

    public ArrayList<Choice> getChoices(){
        return choices;
    }

    void addChoice(Choice choice){
        choices.add(choice);
    }

    //Control for duplication
    //Returns true if choice is duplicated
    boolean checkDuplication(Choice choice){

        for(Choice c:choices){
            if(choice.equals(c)){
                return true;
            }
        }
        return false;

    }

    void shuffleChoices(){
        Collections.shuffle(choices);
    }

    //Returns choice with given meaning
    //Throws exception if there is no such choice
    Choice findChoiceByMeaning(String meaning){
        for(Choice choice:choices){
            if(choice.getMeaning().equals(meaning)){
                return choice;
            }
        }
        throw new IllegalStateException();
    }

    //Returns choice with given word
    //Throws exception if there is no such choice
    Choice findChoiceByWord(String word){
        for(Choice choice:choices){
            if(choice.getWord().equals(word)){
                return choice;
            }
        }
        throw new IllegalStateException();
    }

    public int getTrueAnswerIndex(){
        return choices.indexOf(trueAnswer);
    }

}
