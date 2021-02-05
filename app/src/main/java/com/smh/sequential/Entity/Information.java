package com.smh.sequential.Entity;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Information implements Entity,Serializable {

    private int listId;
    private String listName;
    private int listSize;

    public Information(){}

    public Information(int listId, String listName, int listSize) {
        this.listId = listId;
        this.listName = listName;
        this.listSize = listSize;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    @NonNull
    @Override
    public String toString() {
        return "listId = "+listId+" , listName = "+listName+" , listSize = "+listSize;
    }
}
