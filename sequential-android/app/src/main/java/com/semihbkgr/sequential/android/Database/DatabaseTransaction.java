package com.semihbkgr.sequential.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.semihbkgr.sequential.android.entity.Information;
import com.semihbkgr.sequential.android.entity.Vocabulary;

import java.util.List;

class DatabaseTransaction {

    private DatabaseTransaction(){}

    //Create list table which is given information name if it is not exists
    //This method is not use provider, it use database
    static void createListTable(Information information){
        Provider.database.execSQL(DatabaseContracts.GET_CREATE_LIST_TABLE_QUERY(information.getListName()));
        Log.i(DatabaseTransaction.class.getName(), "createListDatabase: table create query was executed");
    }

    //Delete list table which is given information name if it si exists
    //This method is not use provider, it use database
    static void deleteListTable(Information information){
        Provider.database.execSQL(DatabaseContracts.GET_DELETE_LIST_TABLE_QUERY(information.getListName()));
        Log.i(DatabaseTransaction.class.getName(), "deleteListTable: table delete query was executed");
    }

    //Save given information
    static void saveInformation(Context context,Information information){

        ContentValues cv=new ContentValues();
        cv.put(DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME, information.getListName());
        cv.put(DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME, information.getListSize());
        cv.put(DatabaseContracts.DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME,1);
        context.getContentResolver().insert(Provider.CONTENT_URI,cv);

        Log.i(DatabaseTransaction.class.getName(), "saveInformation: information save query executed, information = "+information);

    }

    //Delete given information
    static void deleteInformation(Context context,Information information){

        context.getContentResolver().delete(Provider.CONTENT_URI,
                DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME+"=?",new String[]{""+information.getListName()});

        Log.i(DatabaseTransaction.class.getName(), "deleteInformation: delete information query executed");

    }

    //Save given vocabularies to given list name table
    static void saveList(Context context, Information information, List<Vocabulary> vocabularies){

        Uri uri= DatabaseUtil.generateUriByTableName(information.getListName());
        for(Vocabulary vocabulary:vocabularies){
            ContentValues cv=new ContentValues();
            cv.put(DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME,vocabulary.getId());
            cv.put(DatabaseContracts.DATABASE_LIST_TABLE_ENG_COLUMN_NAME,vocabulary.getEng());
            cv.put(DatabaseContracts.DATABASE_LIST_TABLE_TR_COLUMN_NAME,vocabulary.getTr());
            context.getContentResolver().insert(uri,cv);
        }

        Log.i(DatabaseTransaction.class.getName(), "saveList: save list query executed, list name = "+information.getListName());

    }



}
