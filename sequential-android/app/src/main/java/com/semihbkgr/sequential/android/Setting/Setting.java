package com.semihbkgr.sequential.android.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.semihbkgr.sequential.android.database.DatabaseFacade;
import com.semihbkgr.sequential.android.database.InformationDatabaseEntity;


public class Setting {

    public static final int QUIZ_QUESTION_NUMBER=25;

    //Setting properties
    private boolean sound;
    private int speed;
    private String activeListName;

    private InformationDatabaseEntity activeListInformationDatabase;

    private int deleteSleep,writeSleep,waitSleep;

    private Context context;

    //Shared Preferences
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static Setting setting;


    private Setting(Context context){
        activeListInformationDatabase =new InformationDatabaseEntity();
        this.context=context;
    }

    public static Setting getInstance(Context context){
        if (setting==null){
            setting=new Setting(context);
        }
        return setting;
    }

    public void loadSharedPreference(SharedPreferences preferences){
        this.preferences=preferences;
        editor=preferences.edit();
        Log.i(getClass().getName(), "loadSharedPreference: preferences of setting was loaded");
        setting.loadProperties();
    }


    //Loads all properties
    private void loadProperties(){
        sound=preferences.getBoolean(ContractSetting.SHARED_PREFERENCES_SOUND,true);
        speed=preferences.getInt(ContractSetting.SHARED_PREFERENCES_SPEED,50);
        activeListName =preferences.getString(ContractSetting.SHARED_PREFERENCES_ACTIVE_LIST,null);
        Log.i(getClass().getName(), "loadProperties: properties loaded");
        reloadInformationDatabaseEntity();
        reloadSleepTimes();
    }

    public void changeAndSaveSound(){
        sound=!sound;
        editor.putBoolean(ContractSetting.SHARED_PREFERENCES_SOUND,sound);
        editor.apply();
        Log.i(getClass().getName(), "changeAndSaveSound: sound changed, sound = "+sound);
    }

    public void setAndSaveSpeed(int newSpeed){
        speed=newSpeed;
        editor.putInt(ContractSetting.SHARED_PREFERENCES_SPEED,speed);
        editor.apply();
        Log.i(getClass().getName(), "setAndSaveSpeed: speed set, speed = "+speed);
        reloadSleepTimes();
    }

    public void setAndSaveActiveList(String newActiveListName){
        activeListName = newActiveListName;
        editor.putString(ContractSetting.SHARED_PREFERENCES_ACTIVE_LIST, activeListName);
        editor.apply();
        Log.i(getClass().getName(), "setAndSaveActiveList: active list set, active list = "+ activeListName);
        reloadInformationDatabaseEntity();
    }

    private void reloadInformationDatabaseEntity(){
        if(activeListName !=null){
            activeListInformationDatabase = DatabaseFacade.getInformationDatabaseEntity(context, activeListName);
            Log.i(getClass().getName(), "reloadInformationDatabaseEntityByName: information database entity reloaded, list index = "+
                    activeListInformationDatabase.getIndex());
        }else{
            activeListInformationDatabase=null;
        }
    }

    private void reloadSleepTimes(){

        writeSleep=120-speed;
        waitSleep=1500-(speed*10);
        deleteSleep=5;

    }

    public void updateIndexOfCurrentListInformation(int index){
        activeListInformationDatabase.setIndex(index);
    }

    public boolean isSound() {
        return sound;
    }

    public int getSpeed() {
        return speed;
    }

    public String getActiveListName() {
        return activeListName;
    }

    public InformationDatabaseEntity getActiveListInformationDatabase(){
        return activeListInformationDatabase;
    }

    public int getDeleteSleep() {
        return deleteSleep;
    }

    public int getWriteSleep() {
        return writeSleep;
    }

    public int getWaitSleep() {
        return waitSleep;
    }
}
