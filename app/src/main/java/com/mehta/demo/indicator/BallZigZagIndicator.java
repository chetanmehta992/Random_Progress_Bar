package com.mehta.demo.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/10/19.
 */
public class BallZigZagIndicator extends BaseIndicatorController {

    float[] translateX=new float[2],translateY=new float[2];


    @Override
    public void draw(Canvas canvas, Paint paint) {
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, getWidth() / 10f, paint);
            canvas.restore();
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators=new ArrayList<>();
        float startX=getWidth()/6f;
        float startY=getWidth()/6f;
        for (int i = 0; i < 2; i++) {
            final int index=i;
            ValueAnimator translateXAnim=ValueAnimator.ofFloat(startX,getWidth()-startX,getWidth()/2f,startX);
            if (i==1){
                translateXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()/2f,getWidth()-startX);
            }
            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,startY,getHeight()/2f,startY);
            if (i==1){
                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,getHeight()/2f,getHeight()-startY);
            }

            translateXAnim.setDuration(1000);
            translateXAnim.setInterpolator(new LinearInterpolator());
            translateXAnim.setRepeatCount(-1);
            translateXAnim.addUpdateListener(animation -> {
                translateX[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });
            translateXAnim.start();

            translateYAnim.setDuration(1000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            translateYAnim.addUpdateListener(animation -> {
                translateY[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });
            translateYAnim.start();
            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }

}
