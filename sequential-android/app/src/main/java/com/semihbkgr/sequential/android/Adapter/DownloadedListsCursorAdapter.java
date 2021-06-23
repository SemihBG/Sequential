package com.semihbkgr.sequential.android.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.semihbkgr.sequential.android.Database.DatabaseContracts;
import com.semihbkgr.sequential.android.R;
import com.semihbkgr.sequential.android.Setting.Setting;

import java.util.ArrayList;

public class DownloadedListsCursorAdapter extends CursorAdapter {

    private ImageButton active;
    private ArrayList<ImageButton> buttons;
    private Setting setting;

    public DownloadedListsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        buttons=new ArrayList<>();
        setting=Setting.getInstance(context);
        Log.i(getClass().getName(), "DownloadedListsCursorAdapter: adapter object created");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.downloaded_lists_one_line,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String name=cursor.getString(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_COLUMN_NAME));
        int size=cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_SIZE_COLUMN_NAME));
        int index=cursor.getInt(cursor.getColumnIndex(DatabaseContracts.DATABASE_INFORMATION_TABLE_INDEX_COLUMN_NAME));

        ((TextView)view.findViewById(R.id.listInfoTextView)).setText(name+"-"+index+"-"+((100/size)*index)+"%");
        ((ProgressBar)view.findViewById(R.id.listProgress)).setProgress(((100/size)*index),true);
        ImageButton button=view.findViewById(R.id.activationButton);

        if(name.equals(setting.getActiveListName())){
            button.setImageResource(R.drawable.ic_star_black_24dp);
            active=button;
        }else{
            button.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        buttons.add(button);

        button.setOnClickListener((View v)-> {
            if (button!=active){
                active=button;
                setAllButtonsImageRegardingActive();
                setting.setAndSaveActiveList(name);
                Log.i(getClass().getName(), "bindView: clicked to select active list, list name = "+name);
            }
        });


    }
    private void setAllButtonsImageRegardingActive(){

        for(ImageButton button:buttons){
            if(active!=button){
                button.setImageResource(R.drawable.ic_star_border_black_24dp);
            }else{
                button.setImageResource(R.drawable.ic_star_black_24dp);
            }
        }

    }


}
