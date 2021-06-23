package com.semihbkgr.sequential.android.Entity;

import java.io.Serializable;

public class Vocabulary implements Entity, Serializable {

    private int id;
    private String eng;
    private String tr;

    public Vocabulary(){}

    public Vocabulary(int id, String eng, String tr){
        this.id=id;
        this.eng=eng;
        this.tr=tr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

}
