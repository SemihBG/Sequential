package com.semihbkgr.sequential.android.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Provider extends ContentProvider {

    static SQLiteDatabase database;

    private static final String CONTENT_AUTHORITY="com.semihbkgr.sequential.android.Database.Provider";
    private static final String PATH_LISTS_NAME=DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME;

    static final Uri BASE_CONTENT_URI=Uri.parse("content://"+CONTENT_AUTHORITY);

    static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LISTS_NAME);

    private static final UriMatcher matcher;

    static{
        matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(CONTENT_AUTHORITY,PATH_LISTS_NAME,1);
    }

    @Override
    public boolean onCreate() {
        database=new Database(getContext()).getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (matcher.match(uri)){

            case 1:
                return database.query(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME,projection,selection,selectionArgs,null,null,null);
            default:
                String name;
                if((name= DatabaseUtil.getListTableNameByUri(uri))!=null){
                    return database.query(name,projection,selection,selectionArgs,null,null,null);
                }

                throw new IllegalArgumentException("Unknown URI "+uri);

        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (matcher.match(uri)){
            case 1:
                long line=database.insert(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME,null,values);
                if(line>=1){
                    return ContentUris.withAppendedId(CONTENT_URI,line);
                }
                return null;
            default:

                String name;
                if((name= DatabaseUtil.getListTableNameByUri(uri))!=null){
                    long line1 = database.insert(name,null,values);
                    if(line1>=1){
                        return ContentUris.withAppendedId(CONTENT_URI,line1);
                    }
                    return null;
                }

                throw new IllegalArgumentException("Unknown URI "+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (matcher.match(uri)){
            case 1:
                return database.delete(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (matcher.match(uri)){
            case 1:
                return database.update(DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME,values,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
    }

}
