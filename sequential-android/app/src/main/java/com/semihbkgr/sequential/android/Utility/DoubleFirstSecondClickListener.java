package com.semihbkgr.sequential.android.Utility;

import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DoubleFirstSecondClickListener implements View.OnClickListener {

    //This class doesn't work on UI Thread
    //Abstract methods have to be configured regarding this

    private static final long DEFAULT_CLICK_TIME_INTERVAL=200;
    private final long doubleClickTimeInterval;
    private volatile boolean isFirstClick;
    private AtomicBoolean isClicked;

    private volatile boolean threadRun;

    public DoubleFirstSecondClickListener(){
        doubleClickTimeInterval =DEFAULT_CLICK_TIME_INTERVAL;
        setDefaultFields();
    }

    public DoubleFirstSecondClickListener(long doubleClickTimeInterval){
        this.doubleClickTimeInterval = doubleClickTimeInterval;
        setDefaultFields();
    }

    private void setDefaultFields(){
        isFirstClick=false;
        isClicked=new AtomicBoolean(false);
        threadRun=true;
        startThread();
    }

    private void startThread(){

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(threadRun){
                    if(isClicked.get()){
                        //FirstClick
                        if(isFirstClick){
                            isClicked.set(false);
                            try {
                                Thread.sleep(doubleClickTimeInterval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //Double Clicked
                            if(isClicked.get()){
                                isClicked.set(false);
                                onDoubleClick();
                            }
                            //One Click
                            else{
                                onFirstClick();
                            }
                        }
                        //Second Click
                        else{
                            isClicked.set(false);
                            try {
                                Thread.sleep(doubleClickTimeInterval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //Double Clicked
                            if(isClicked.get()){
                                isClicked.set(false);
                                onDoubleClick();
                            }
                            //One Click
                            else{
                                onSecondClick();
                            }
                        }
                    }
                }
            }
        },"DoubleFirstSecondClickListenerTimeControlThread");

        thread.start();

    }

    @Override
    public void onClick(View v) {
        if(isFirstClick){
            isFirstClick=false;

        }else{
            isFirstClick=true;
        }
        isClicked.set(true);
    }

    public abstract void onDoubleClick();
    public abstract void onFirstClick();
    public abstract void onSecondClick();


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        threadRun=false;
    }


}
