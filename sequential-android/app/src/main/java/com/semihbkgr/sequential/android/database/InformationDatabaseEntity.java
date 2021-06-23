package com.semihbkgr.sequential.android.database;

public class InformationDatabaseEntity {

    private int id;
    private String name;
    private int size;
    private int index;

    public InformationDatabaseEntity(){}

    public InformationDatabaseEntity(int id, String name, int size, int index) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
