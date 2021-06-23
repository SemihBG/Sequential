package com.semihbkgr.sequential.android.connection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.semihbkgr.sequential.android.connection.cache.InformationCache;
import com.semihbkgr.sequential.android.entity.Information;
import com.semihbkgr.sequential.android.entity.Vocabulary;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionFacade {

    private ConnectionFacade(){}

    //Send request to take information, and populate given list with response
    public static void getInformation(ArrayList<Information> informationList,boolean getCachedData){

        if(getCachedData && InformationCache.isCached()){
            informationList=InformationCache.getCache();
            Log.i(ConnectionFacade.class.getName(), "getInformation: cached data retrieved");
            return;
        }

        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<Information[]> info=apiService.getInformation();

        info.enqueue(new PopulateListCallbackAdapter<Information>(informationList,null,null));

        InformationCache.cache(informationList);

    }

    //Send request to take information, and return the list populated with response
    public static ArrayList<Information> getInformation(boolean getCachedData){

        if(getCachedData && InformationCache.isCached()){
            Log.i(ConnectionFacade.class.getName(), "getInformation: cached data retrieved");
            return InformationCache.getCache();
        }

        ArrayList<Information> informationList = new ArrayList<>();

        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<Information[]> info=apiService.getInformation();

        info.enqueue(new PopulateListCallbackAdapter<Information>(informationList,null,null));

        InformationCache.cache(informationList);

        return informationList;

    }

    //Send request to take list according to tha given information
    //Populate given list populated taken vocabularies
    public static void getList(ArrayList<Vocabulary> vocabularyList,Information information){

        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService=retrofit.create(ApiService.class);

        Call<Vocabulary[]> vocbList=apiService.getList(information.getListName());

        vocbList.enqueue(new PopulateListCallbackAdapter<Vocabulary>(vocabularyList,null,null));


    }

    //Send request to take list according to tha given information
    //Return list populated taken vocabularies
    public static ArrayList<Vocabulary> getList(Information information){

        ArrayList<Vocabulary> vocabularyList=new ArrayList<>();

        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUrl.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        ApiService apiService=retrofit.create(ApiService.class);

        System.out.println("--------------------------");
        System.out.println(information.getListId());
        System.out.println(information.getListName());
        System.out.println(information.getListSize());
        System.out.println("--------------------------");

        Call<Vocabulary[]> vocbList=apiService.getList(information.getListName());

        vocbList.enqueue(new PopulateListCallbackAdapter<Vocabulary>(vocabularyList,null,null));

        return vocabularyList;

    }




}
