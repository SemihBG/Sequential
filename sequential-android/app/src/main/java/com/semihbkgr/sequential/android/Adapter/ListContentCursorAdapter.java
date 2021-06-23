package com.semihbkgr.sequential.android.Adapter;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.semihbkgr.sequential.android.Database.DatabaseContracts;
import com.semihbkgr.sequential.android.R;

public class ListContentCursorAdapter extends CursorAdapter {


    public ListContentCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
        Log.i(getClass().getName(), "ListContentArrayListAdapter: list content cursor adapter object created, cursor size = "+c.getCount());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_content_one_line,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        StringBuffer line=new StringBuffer();

        line.append(cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ID_COLUMN_NAME))+". ");
        line.append(cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_ENG_COLUMN_NAME))+" = ");
        line.append(cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_LIST_TABLE_TR_COLUMN_NAME)));

        ((TextView)view.findViewById(R.id.vocabularyTextView)).setText(line.toString());

    }
}
