package com.mehta.demo.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/10/20.
 */
public class BallSpinFadeLoaderIndicator extends BaseIndicatorController {

    public static final float SCALE=1.0f;

    public static final int ALPHA=255;

    float[] scaleFloats=new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE};

    int[] alphas=new int[]{ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA,
            ALPHA};


    @Override
    public void draw(Canvas canvas, Paint paint) {
        float radius=getWidth()/10f;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point=circleAt(getWidth(),getHeight(),getWidth()/2f-radius,i*(Math.PI/4));
            canvas.translate(point.x,point.y);
            canvas.scale(scaleFloats[i],scaleFloats[i]);
            paint.setAlpha(alphas[i]);
            canvas.drawCircle(0,0,radius,paint);
            canvas.restore();
        }
    }

    Point circleAt(int width,int height,float radius,double angle){
        float x= (float) (width/2+radius*(Math.cos(angle)));
        float y= (float) (height/2+radius*(Math.sin(angle)));
        return new Point(x, y);
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators=new ArrayList<>();
        int[] delays= {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();
                postInvalidate();
            });
            scaleAnim.start();

            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 77, 255);
            alphaAnim.setDuration(1000);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> {
                alphas[index] = (int) animation.getAnimatedValue();
                postInvalidate();
            });
            alphaAnim.start();
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

    static final class Point{
        public float x;
        public float y;

        public Point(float x, float y){
            this.x=x;
            this.y=y;
        }
    }


}
