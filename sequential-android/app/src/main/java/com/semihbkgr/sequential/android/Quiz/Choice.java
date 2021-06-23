package com.semihbkgr.sequential.android.Quiz;

import java.util.Objects;

public class Choice{

    private String word;
    private String meaning;


    Choice(String word,String meaning){
        this.word=word;
        this.meaning=meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Choice)) return false;
        Choice choice = (Choice) o;
        return Objects.equals(getWord(), choice.getWord()) &&
                Objects.equals(getMeaning(), choice.getMeaning());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord(), getMeaning());
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

}
