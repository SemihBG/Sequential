package com.semihbkgr.sequential.android.Utility;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecuteUtil {

    private static final Executor executor;

    static{
        executor= Executors.newSingleThreadExecutor();
    }

    private ExecuteUtil(){}

    public static <T> void executeWhenListReady(ArrayList<T> list,int size,Execute execute){

        executor.execute(()->{
            Log.i(ExecuteUtil.class.getName(), "executeWhenListReady: thread execution started");
            while(true){
                if(list.size()>=size){
                    execute.execute();
                    break;
                }else{
                    Thread.yield();
                }
            }
            Log.i(ExecuteUtil.class.getName(), "executeWhenListReady: thread execution done");
        });


    }

}
