package com.smh.sequential.Database;

public class DatabaseContracts {

    public static final String DATABASE_NAME="database.db";

    public static final String DATABASE_INFORMATION_TABLE_NAME ="information";

    public static final String DATABASE_INFORMATION_TABLE_ID_COLUMN_NAME ="_id";
    public static final String DATABASE_INFORMATION_TABLE_COLUMN_NAME ="name";
    public static final String DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME ="size";
    public static final String DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME ="current_index";

    public static final String DATABASE_LIST_TABLE_ID_COLUMN_NAME ="_id";
    public static final String DATABASE_LIST_TABLE_ENG_COLUMN_NAME ="eng";
    public static final String DATABASE_LIST_TABLE_TR_COLUMN_NAME ="tr";

    private static final String BEGIN_OF_CREATE_LIST_TABLE_QUERY="CREATE TABLE IF NOT EXISTS ";
    private static final String REST_OF_CREATE_LIST_TABLE_QUERY =
            " ("+DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            " "+DatabaseContracts.DATABASE_LIST_TABLE_ENG_COLUMN_NAME +" TEXT NOT NULL,"+
            " "+DatabaseContracts.DATABASE_LIST_TABLE_TR_COLUMN_NAME +" TEXT NOT NULL)";

    static String GET_CREATE_LIST_TABLE_QUERY(final String LIST_TABLE_NAME){
        return BEGIN_OF_CREATE_LIST_TABLE_QUERY+LIST_TABLE_NAME+ REST_OF_CREATE_LIST_TABLE_QUERY;
    }


    private static final String BEGIN_OF_DELETE_VOCABULARY_TABLE_QUERY= "DROP TABLE IF EXISTS ";
    static String GET_DELETE_LIST_TABLE_QUERY(final String LIST_TABLE_NAME){
        return BEGIN_OF_DELETE_VOCABULARY_TABLE_QUERY+LIST_TABLE_NAME;
    }

}
