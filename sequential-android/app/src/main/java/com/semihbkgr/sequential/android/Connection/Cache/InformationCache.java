package com.semihbkgr.sequential.android.Connection.Cache;


import android.util.Log;

import com.semihbkgr.sequential.android.Entity.Information;

import java.util.ArrayList;

public class InformationCache {

    private static ArrayList<Information> informationList;
    private static boolean isCached;

    static {
        informationList=new ArrayList<>();
        isCached=false;
    }

    public static void cache(ArrayList<Information> informationList){
        InformationCache.informationList=informationList;
        isCached=true;
        Log.i(InformationCache.class.getName(), "cache: information data cached, size = "+informationList.size());
    }

    public static boolean isCached(){
        return InformationCache.isCached;
    }

    public static ArrayList<Information> getCache(){
        return InformationCache.informationList;
    }

}
