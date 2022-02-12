package com.mehta.demo.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.util.List;

public abstract class BaseIndicatorController {


    private View mTarget;

    public List<Animator> mAnimators;


    public void setTarget(View target){
        this.mTarget=target;
    }

    public View getTarget(){
        return mTarget;
    }


    public int getWidth(){
        return mTarget.getWidth();
    }

    public int getHeight(){
        return mTarget.getHeight();
    }

    public void postInvalidate(){
        mTarget.postInvalidate();
    }

    public abstract void draw(Canvas canvas,Paint paint);

    public abstract List<Animator> createAnimation();

    public void initAnimation(){
        mAnimators=createAnimation();

    }


    public void setAnimationStatus(AnimStatus animStatus){
        if (mAnimators==null){
            return;
        }
        int count=mAnimators.size();
        for (int i = 0; i < count; i++) {
            Animator animator=mAnimators.get(i);
            boolean isRunning=animator.isRunning();
            switch (animStatus){
                case START:
                    if (!isRunning){
                        animator.start();
                    }
                    break;
                case END:
                    if (isRunning){
                        animator.end();
                    }
                    break;
                case CANCEL:
                    if (isRunning){
                        animator.cancel();
                    }
                    break;
            }
        }
    }


    public enum AnimStatus{
        START,END,CANCEL
    }



}
