package com.semihbkgr.sequential.android.Database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


public class DatabaseUtil {

    private DatabaseUtil(){}

    //Generate Uri with given list name
    static Uri generateUriByTableName(String listName){
        return Uri.withAppendedPath(Provider.BASE_CONTENT_URI,listName);
    }

    //Check if there is table which is given
    static boolean checkTableIfExistByName (Context context,String listName){

        Cursor cursor= context.getContentResolver().query(generateUriByTableName(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME),
                new String[]{DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME},
                DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME +"=?",new String[]{listName},
                null);

        if(cursor!=null){
            if(cursor.moveToNext()){
                cursor.close();
                return true;
            }
            cursor.close();
            return false;
        }
        return false;

    }

    //Returns list table name by given uri
    static String getListTableNameByUri(Uri uri){

        String uriString=uri.toString();
        String[] parsed=uriString.split("/");
        String result=parsed[parsed.length-1];
        return result;

    }


    //Returns list name with given id
    //Throws exception if there is no table with given id
    static String getListNameById(Context context,int id){

        Cursor infoCursor=context.getContentResolver().query(
                DatabaseUtil.generateUriByTableName(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME),
                new String[]{DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME},
                DatabaseContracts.DATABASE_INFORMATION_TABLE_ID_COLUMN_NAME +"=?",
                new String[]{""+id},null);


        if(infoCursor==null || !infoCursor.moveToNext()){
            throw new IllegalStateException();
        }

        String result=infoCursor.getString(infoCursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME));

        infoCursor.close();

        return result;

    }

}
