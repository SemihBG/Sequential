package com.smh.sequential.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.smh.sequential.Entity.Information;
import com.smh.sequential.Entity.Vocabulary;

import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DatabaseFacade {


    private DatabaseFacade(){}

    //Save given info
    //Create new list table
    //Add given vocabularies to table
    //Throws exception if there is such table
    //Apply all these steps on executor thread service's worker thread
    public static void addNewList(Context context, Information information, List<Vocabulary> vocabularies,boolean isListReady){

        if(DatabaseUtil.checkTableIfExistByName(context,information.getListName())){
            throw new IllegalStateException();
        }

        Log.i(DatabaseFacade.class.getName(), "addNewList: adding new list process is being started");
        DatabaseTransaction.saveInformation(context,information);
        DatabaseTransaction.createListTable(information);
        DatabaseTransaction.saveList(context,information,vocabularies);
        Log.i(DatabaseFacade.class.getName(), "addNewList: adding new list process is done");

    }

    //Delete information row from information table
    //Drop table
    //Throws exception if there is no such table with given name
    //Apply all these steps on executor thread service's worker thread
    public static void deleteList(Context context,Information information){

        if(!DatabaseUtil.checkTableIfExistByName(context,information.getListName())){
            throw new IllegalStateException();
        }

        Log.i(DatabaseFacade.class.getName(), "deleteList: deleting list process is being started");
        DatabaseTransaction.deleteInformation(context,information);
        DatabaseTransaction.deleteListTable(information);
        Log.i(DatabaseFacade.class.getName(), "deleteList: deleting list process is done");

    }

    //Returns information table cursor
    public static Cursor getInformationTableCursor(Context context){
        return context.getContentResolver().query(Provider.CONTENT_URI,null,null,null,null);
    }

    //Returns size of given name of list
    //Throws exception if there is no such table with given name
    public static int getSizeOfList(Context context,String listName){

        if(!DatabaseUtil.checkTableIfExistByName(context,listName)){
            throw new IllegalStateException();
        }

        Cursor cursor=context.getContentResolver().query(Provider.CONTENT_URI,new String[]{DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME},
                DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME +"=?",new String[]{listName},
                null,null);

        if (cursor!=null & cursor.moveToNext()){
            int size=cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME));
            cursor.close();
            return size;
        }else{
            throw new IllegalStateException();
        }

    }

    //Returns vocabulary from given list at given id
    //Throws exception if there is no such table with given name
    //Throws exception if there is no vocabulary at list with given id
    public static Vocabulary getVocabularyById(Context context,String listName,int id){

        if(!DatabaseUtil.checkTableIfExistByName(context,listName)){
            throw new IllegalStateException();
        }

        Cursor cursor=context.getContentResolver().query(DatabaseUtil.generateUriByTableName(listName),
                null, DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME+"=?",new String[]{""+id},null);

        if(cursor!=null && cursor.moveToNext()){
            Vocabulary vocabulary=new Vocabulary(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ENG_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_TR_COLUMN_NAME)));
            cursor.close();
            return vocabulary;
        }else{
            throw new IllegalStateException();
        }

    }

    //Returns true if there is list with given name
    public static boolean checkIfListExistByName(Context context,String listName){
        return DatabaseUtil.checkTableIfExistByName(context,listName);
    }

    //Returns cursor of given table id
    //Returns null if cursor is null
    public static Cursor getListCursorById(Context context,int id){

        String listName=DatabaseUtil.getListNameById(context,id);

        Uri uri=DatabaseUtil.generateUriByTableName(listName);

        Cursor cursor=context.getContentResolver().query(
                uri,null,null,null,null);

        if(cursor==null){
            throw new IllegalStateException();
        }

        return cursor;

    }

    //Returns cursor of given table id starting from given index
    //Returns null if cursor is null
    public static Cursor getListCursorByIdFromIndex(Context context,int id,int index){

        String listName=DatabaseUtil.getListNameById(context,id);

        Uri uri=DatabaseUtil.generateUriByTableName(listName);

        Cursor cursor=context.getContentResolver().query(
                uri,null,DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME+">=?",new String[]{""+index},null);

        if(cursor==null){
            throw new IllegalStateException();
        }

        return cursor;

    }


    //Returns last list name(last item of information table)
    //Returns null if there is no last downloaded list
    public static String getLastDownloadedListName(Context context){

        Cursor cursor=context.getContentResolver().query(Provider.CONTENT_URI,new String[]{DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME},null,null,null);

        if(cursor.moveToLast()){
            String result= cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME));
            cursor.close();
            return result;
        }

        return null;

    }

    //Returns information database entity of given list name
    //Throws if there is no such list name
    public static InformationDatabaseEntity getInformationDatabaseEntity(Context context,String listName){

        Cursor cursor=context.getContentResolver().query(Provider.CONTENT_URI,null,
                DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME+"=?",new String[]{listName},null);

        if(cursor==null){
            throw new IllegalArgumentException();
        }

        if(cursor.moveToNext()){
            InformationDatabaseEntity informationDatabaseEntity=new InformationDatabaseEntity();
            informationDatabaseEntity.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_ID_COLUMN_NAME)));
            informationDatabaseEntity.setIndex(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME)));
            informationDatabaseEntity.setName(cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME)));
            informationDatabaseEntity.setSize(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME)));
            cursor.close();
            return informationDatabaseEntity;
        }

        throw new IllegalStateException();

    }


    public static void updateInformationIndex(Context context,String listName,Integer index) {

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseContracts.DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME,index);

        context.getContentResolver().update(Provider.CONTENT_URI,contentValues,
                DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME+"=?",
                new String[]{listName});

    }
}
