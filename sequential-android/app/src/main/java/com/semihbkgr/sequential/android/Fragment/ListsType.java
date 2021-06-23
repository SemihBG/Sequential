package com.semihbkgr.sequential.android.Fragment;

public enum ListsType {

    allLists(0),
    downloadLists(1);

    private int id;

    ListsType(int id){
        this.id=id;
    }

    public static ListsType getListTypeById(int id){
        for(ListsType listsType:ListsType.values()){
            if(listsType.id==id){
                return listsType;
            }
        }
        throw new IllegalArgumentException();
    }

}
