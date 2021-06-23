package com.semihbkgr.sequential.android.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.semihbkgr.sequential.android.database.DatabaseFacade;
import com.semihbkgr.sequential.android.database.InformationDatabaseEntity;
import com.semihbkgr.sequential.android.entity.Vocabulary;
import com.semihbkgr.sequential.android.setting.Setting;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class VocabularyEffect extends AsyncTask<Vocabulary,String,Integer> {

    private InformationDatabaseEntity informationDatabaseEntity;
    private Context context;

    private Random random;

    private boolean getBack;
    private int index;
    private CountDownLatch latch;

    private Setting setting;

    private TextView lineTextView;

    private MediaPlayer writingPlayer;
    private MediaPlayer deletingPlayer;

    public VocabularyEffect(Context context,TextView lineTextView){
        setting=Setting.getInstance(context);
        this.informationDatabaseEntity=setting.getActiveListInformationDatabase();
        this.context=context;
        this.lineTextView=lineTextView;
        index=informationDatabaseEntity.getIndex();
        random=new Random();
        getBack=false;
        latch=new CountDownLatch(0);
        Log.i(getClass().getName(), "VocabularyEffect: effect object created");
    }

    public VocabularyEffect(Context context, TextView lineTextView,MediaPlayer writingPlayer,MediaPlayer deletingPlayer){
        setting=Setting.getInstance(context);
        this.informationDatabaseEntity=setting.getActiveListInformationDatabase();
        this.context=context;
        this.lineTextView=lineTextView;
        this.writingPlayer=writingPlayer;
        this.deletingPlayer=deletingPlayer;
        index=informationDatabaseEntity.getIndex();
        getBack=false;
        latch=new CountDownLatch(0);
        random=new Random();
        Log.i(getClass().getName(), "VocabularyEffect: effect object created");
    }


    @Override
    protected Integer doInBackground(Vocabulary ... vocabularies) {

        String listName=informationDatabaseEntity.getName();

        main:
        while(informationDatabaseEntity.getSize()>index){

            Vocabulary vocabulary=DatabaseFacade.getVocabularyById(context,listName,index);

            String line=vocabulary.getEng()+" = "+vocabulary.getTr();
            int size=line.length();

            if (setting.isSound()){
                startWritingPlayer();
            }

            for(int i=size;i>=0;i--){

                String currentLine=line.substring(0,size-i)+"|";

                for(int j=0;j<i;j++){
                    currentLine+=" ";
                }

                publishProgress(currentLine);

                if(isCancelled()){
                   return index;
                }

                try{
                    latch.await();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(getBack){
                    getBack=false;
                    index--;
                    continue main;
                }

                try{
                    Thread.sleep(setting.getWriteSleep());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if(setting.isSound()){
                stopWritingPlayer();
            }

            if(isCancelled()){
                return index;
            }

            try{
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getBack){
                getBack=false;
                index--;
                continue main;
            }

            try{
                Thread.sleep(setting.getWaitSleep());
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if(isCancelled()){
                return index;
            }

            try{
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getBack){
                getBack=false;
                index--;
                continue main;
            }

            if(setting.isSound()){
                startDeletingPlayer();
            }

            for(int i=size;i>0;i--){

                String currentLine=line.substring(0,i-1)+"|";

                for(int j=i;j<=size;j++){
                    currentLine+="";
                }

                publishProgress(currentLine);

                if(isCancelled()){
                    return index;
                }

                try{
                    latch.await();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(getBack){
                    getBack=false;
                    index--;
                    continue main;
                }

                try{
                    Thread.sleep(setting.getDeleteSleep());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            if (setting.isSound()){
                stopDeletingPlayer();
            }

            if(isCancelled()){
                return index;
            }

            try{
                latch.await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(getBack){
                getBack=false;
                index--;
                continue main;
            }

            index++;

        }

        return index;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        lineTextView.setText(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        DatabaseFacade.updateInformationIndex(context,informationDatabaseEntity.getName(),index);
        setting.updateIndexOfCurrentListInformation(index);
        Log.i(getClass().getName(), "onCancelled: on cancelled, index = "+index);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
        DatabaseFacade.updateInformationIndex(context,informationDatabaseEntity.getName(),integer);
        setting.updateIndexOfCurrentListInformation(index);
        Log.i(getClass().getName(), "onCancelled: on cancelled, index = "+index);
    }

    public void pause(){
        latch=new CountDownLatch(1);
        Log.i(getClass().getName(), "pause: paused effect");
    }

    public void resume(){
        latch.countDown();
        Log.i(getClass().getName(), "resume: resumed effect");
    }

    public void getBack(){
        if(index>1){
            getBack=true;
        }
    }

    private void startWritingPlayer(){
        if(writingPlayer!=null){
            if(writingPlayer.getCurrentPosition()>2000){
                int position=random.nextInt(501);
                writingPlayer.seekTo(position);
            }
            writingPlayer.start();
        }
    }

    private void stopWritingPlayer(){
        if(writingPlayer!=null){
            writingPlayer.stop();
        }
    }

    private void startDeletingPlayer(){
        if(deletingPlayer!=null){
            deletingPlayer.start();
        }
    }

    private void stopDeletingPlayer(){
        if(deletingPlayer!=null){
            deletingPlayer.stop();
        }
    }

}
