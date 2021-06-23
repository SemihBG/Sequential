package com.semihbkgr.sequential.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.semihbkgr.sequential.android.adapter.AllListsArrayListAdapter;
import com.semihbkgr.sequential.android.adapter.DownloadedListsCursorAdapter;
import com.semihbkgr.sequential.android.connection.cache.InformationCache;
import com.semihbkgr.sequential.android.connection.ConnectionFacade;
import com.semihbkgr.sequential.android.Contracts;
import com.semihbkgr.sequential.android.database.DatabaseFacade;
import com.semihbkgr.sequential.android.entity.Information;
import com.semihbkgr.sequential.android.ListContentActivity;
import com.semihbkgr.sequential.android.R;
import com.semihbkgr.sequential.android.util.ExecuteUtil;

import java.util.ArrayList;

public class ListsFragment extends Fragment {

    private ListsType listsType;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(getClass().getName(), "onCreateView: lists fragment created");
        return (ViewGroup)inflater.inflate(R.layout.fragment_lists,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView=view.findViewById(R.id.lists);

        listsType=ListsType.getListTypeById(getArguments().getInt(Contracts.BUNDLE_LISTS_TYPE_ID));

        Log.i(getClass().getName(), "onViewCreated: created lists fragment' lists type = "+listsType);

        if(listsType==ListsType.allLists){
            populateAllList();
        }else if(listsType==ListsType.downloadLists){
            populateDownloadedLists();
        }

    }

    private void populateAllList() {
        if(InformationCache.isCached()){
            listView.setAdapter(new AllListsArrayListAdapter(getContext(),InformationCache.getCache()));
        }else{
            ArrayList<Information> informationList = ConnectionFacade.getInformation(true);
            ExecuteUtil.executeWhenListReady(informationList,1,()->{
                listView.post(()->{
                    listView.setAdapter(new AllListsArrayListAdapter(getContext(), informationList));
                });
            });
        }
    }

    private void populateDownloadedLists() {

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.i(getClass().getName(), "onCreate: clicked item at position "+position+" , clicked item id = "+id);
            Intent intent=new Intent(getContext(), ListContentActivity.class);
            intent.putExtra(Contracts.BUNDLE_SELECTED_LIST_ID,(int)id);
            startActivity(intent);
        });

        DownloadedListsCursorAdapter adapter=new DownloadedListsCursorAdapter(getContext(),
                DatabaseFacade.getInformationTableCursor(getContext()));

        listView.setAdapter(adapter);

    }

    public void reloadListView(){
        if(listsType==ListsType.allLists){
            populateAllList();
        }else if(listsType==ListsType.downloadLists){
            populateDownloadedLists();
        }
    }


}
