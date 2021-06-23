package com.semihbkgr.sequential.android.Utility;


import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class AnimationtUtil {

    private AnimationtUtil(){}


    //Swipe and alpha effect and set clickable, visibility
    //Default duration time is 500 millisecond
    //Default distance +-250
    public static void applyDefaultButtonEffect(View view,boolean moveEffect,boolean getInvisible,boolean reverse){

        int duration=500;

        if(getInvisible){
            view.setClickable(false);
        }else{
            view.setClickable(true);
        }


        if(moveEffect){

            if (reverse){

                int distance=0;

                ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",distance);
                animator.setDuration(duration);
                animator.start();


            }else{

                int distance=-250;
                ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",distance);
                animator.setDuration(duration);
                animator.start();

            }

        }


        Animation animation=null;

        if(getInvisible){
            animation=new AlphaAnimation(1f,0f);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setClickable(false);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }else{
            view.setVisibility(View.VISIBLE);
            view.setClickable(true);
            animation=new AlphaAnimation(0f,1f);
        }

        animation.setDuration(duration);
        view.startAnimation(animation);

    }


}
