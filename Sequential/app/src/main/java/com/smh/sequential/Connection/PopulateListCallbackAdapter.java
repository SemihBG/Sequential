package com.smh.sequential.Connection;

import android.util.Log;

import com.smh.sequential.Entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Populate list with taken entity response
//It is adapter of Callback<>
class PopulateListCallbackAdapter<T extends Entity> implements Callback<T[]> {

    private ArrayList<T> entityList;

    private String successLog,failLog;

    PopulateListCallbackAdapter(ArrayList<T> entityList,String successLog,String failLog){
        this.entityList=entityList;
        this.successLog=successLog;
        this.failLog=failLog;
    }

    PopulateListCallbackAdapter(String successLog,String failLog){
        entityList=new ArrayList<>();
        this.successLog=successLog;
        this.failLog=failLog;
    }

    public ArrayList<T> getEntityList(){
        return entityList;
    }

    @Override
    public void onResponse(Call<T[]> call, Response<T[]> response) {
        entityList.addAll(Arrays.asList(response.body()));
        if(successLog!=null){
            Log.i(getClass().getName(), "onResponse: "+successLog);
        }
    }

    @Override
    public void onFailure(Call<T[]> call, Throwable t) {
        Log.e(getClass().getName(), "onFailure: request failed, exception message = "+t.getMessage());
        if(failLog!=null){
            Log.i(getClass().getName(), "onFailure: "+failLog);
        }
    }

}
