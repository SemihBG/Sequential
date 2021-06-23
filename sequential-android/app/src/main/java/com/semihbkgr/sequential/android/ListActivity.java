package com.semihbkgr.sequential.android;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import androidx.viewpager.widget.ViewPager;

import com.semihbkgr.sequential.android.fragment.ListsType;
import com.semihbkgr.sequential.android.adapter.ListsPageAdapter;

public class ListActivity  extends FragmentActivity {

    private ViewPager viewPager;
    private Button allListsButton,downloadedListsButton;

    private ListsPageAdapter listsPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        Log.i(getClass().getName(), "onCreate: list activity created");

        viewPager=findViewById(R.id.viewPager);
        allListsButton=findViewById(R.id.allListsButton);
        downloadedListsButton=findViewById(R.id.downloadedListsButton);

        listsPageAdapter =new ListsPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(listsPageAdapter);

        allListsButton.setBackgroundColor(getResources().getColor(R.color.buttonActive,null));
        downloadedListsButton.setBackgroundColor(getResources().getColor(R.color.buttonInactive,null));

        allListsButton.setOnClickListener((view)->{
            if(viewPager.getCurrentItem()==0){
                return;
            }else{
                viewPager.setCurrentItem(0,true);
            }
        });

        downloadedListsButton.setOnClickListener((view)->{
            if(viewPager.getCurrentItem()==1){
                return;
            }else{
                viewPager.setCurrentItem(1,true);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                Log.i(getClass().getName(), "onPageSelected: page changed, position = "+position );
                if(position==0){
                    allListsButton.setBackgroundColor(getResources().getColor(R.color.buttonActive,null));
                    downloadedListsButton.setBackgroundColor(getResources().getColor(R.color.buttonInactive,null));
                }else if(position==1){
                    downloadedListsButton.setBackgroundColor(getResources().getColor(R.color.buttonActive,null));
                    allListsButton.setBackgroundColor(getResources().getColor(R.color.buttonInactive,null));
                    listsPageAdapter.reloadList(ListsType.downloadLists);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

}
