package com.smh.sequential;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.smh.sequential.Adapter.ListContentCursorAdapter;
import com.smh.sequential.Database.DatabaseFacade;

public class ListContentActivity extends AppCompatActivity {

    private ListView listViewListContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_content);

        Log.i(getClass().getName(), "onCreate: list content activity created");
        int id=getIntent().getIntExtra(Contracts.BUNDLE_SELECTED_LIST_ID,0);
        Log.i(getClass().getName(), "onCreate: passes id = "+id);

        listViewListContent =findViewById(R.id.lists);

        ListContentCursorAdapter adapter=new ListContentCursorAdapter(getApplicationContext(),
                DatabaseFacade.getListCursorById(getApplicationContext(),id));

        listViewListContent.setAdapter(adapter);

    }

}
