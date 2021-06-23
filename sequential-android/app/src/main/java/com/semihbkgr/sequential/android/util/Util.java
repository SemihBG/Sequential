package com.semihbkgr.sequential.android.util;

import android.database.Cursor;

import com.semihbkgr.sequential.android.database.DatabaseContracts;
import com.semihbkgr.sequential.android.entity.Vocabulary;

import java.util.ArrayList;

public class Util {

    private Util(){}

    public static ArrayList<Vocabulary> convertVocabularyCursorToArrayList(Cursor cursor){

        if(cursor==null){
            throw new IllegalArgumentException();
        }

        ArrayList<Vocabulary> result=new ArrayList<>();

        while(cursor.moveToNext()){

            Vocabulary vocabulary=new Vocabulary();

            vocabulary.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME)));
            vocabulary.setEng(cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ENG_COLUMN_NAME)));
            vocabulary.setTr(cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_TR_COLUMN_NAME)));

            result.add(vocabulary);
        }
        cursor.close();
        return result;

    }


}
