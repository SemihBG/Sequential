package com.semihbkgr.sequential.android.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.semihbkgr.sequential.android.Connection.ConnectionFacade;
import com.semihbkgr.sequential.android.Database.DatabaseFacade;
import com.semihbkgr.sequential.android.Entity.Information;
import com.semihbkgr.sequential.android.Entity.Vocabulary;
import com.semihbkgr.sequential.android.R;
import com.semihbkgr.sequential.android.Setting.Setting;
import com.semihbkgr.sequential.android.Utility.ExecuteUtil;

import java.util.ArrayList;

public class AllListsArrayListAdapter extends ArrayAdapter<Information> {


    public AllListsArrayListAdapter(Context context, ArrayList<Information> information) {
        super(context,0,information);
        Log.i(getClass().getName(), "AllListsArrayListAdapter: adapter object created");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.all_lists_one_line,parent,false);
        }

        Information information =getItem(position);

        final ImageButton button=((ImageButton)convertView.findViewById(R.id.downloadButton));
        final TextView listName=convertView.findViewById(R.id.listInfo);

        listName.setText(information.getListName());

        if (DatabaseFacade.checkIfListExistByName(getContext(),information.getListName())){
            setButtonAsDeleteButton(button,information);
            Log.i(getClass().getName(), "getView: list that is downloaded, information = "+information);
        }else{
            setButtonAsDownloadButton(button,information);
            Log.i(getClass().getName(), "getView: list that is not downloaded, information = "+information);
        }

        return convertView;

    }


    private void setButtonAsDownloadButton(ImageButton button,Information information){

        button.setImageResource(R.drawable.ic_file_download_black_24dp);

        button.setOnClickListener((view)->{
            Log.i(getClass().getName(), "setButtonAsDownloadButton: clicked download, name = "+information.getListName());
            ArrayList<Vocabulary> vocabularies= ConnectionFacade.getList(information);
            button.setClickable(false);
            button.setVisibility(View.INVISIBLE);
            ExecuteUtil.executeWhenListReady(vocabularies,information.getListSize(),()->{
                DatabaseFacade.addNewList(getContext(),information,vocabularies,false);
                Setting.getInstance(getContext()).setAndSaveActiveList(information.getListName());
                button.post(()->{
                    Toast.makeText(getContext(),"Downloaded",Toast.LENGTH_SHORT).show();
                });
                button.setClickable(true);
                button.setVisibility(View.VISIBLE);
            });
            setButtonAsDeleteButton(button,information);
        });

    }

    private void setButtonAsDeleteButton(ImageButton button,Information information){

        button.setImageResource(R.drawable.delete);

        button.setOnClickListener((view)->{
            Log.i(getClass().getName(), "setButtonAsDownloadButton: clicked delete, name = "+information.getListName());
            DatabaseFacade.deleteList(getContext(),information);
            Setting.getInstance(getContext()).setAndSaveActiveList(DatabaseFacade.getLastDownloadedListName(getContext()));
            Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
            setButtonAsDownloadButton(button,information);
        });

    }


}
