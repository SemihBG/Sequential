package com.semihbkgr.sequential.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class Database extends SQLiteOpenHelper {

    private static final int version=1;

    private final String createListsNameQuery="CREATE TABLE IF NOT EXISTS "+DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME +
            " ("+DatabaseContracts.DATABASE_INFORMATION_TABLE_ID_COLUMN_NAME +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            " "+DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME +" TEXT NOT NULL,"+
            " "+DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME +" INTEGER NOT NULL,"+
            " "+DatabaseContracts.DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME +" INTEGER NOT NULL)";

    private final String deleteListNameQuery="DROP TABLE IF EXISTS "+DatabaseContracts.DATABASE_INFORMATION_TABLE_NAME;

    public Database(@Nullable Context context) {
        super(context, DatabaseContracts.DATABASE_NAME, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createListsNameQuery);
        Log.i(getClass().getName(), "Database: database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(deleteListNameQuery);
    }

}
