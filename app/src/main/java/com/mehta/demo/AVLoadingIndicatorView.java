package com.mehta.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;


import com.mehta.demo.indicator.BallBeatIndicator;
import com.mehta.demo.indicator.BallClipRotateMultipleIndicator;
import com.mehta.demo.indicator.BallClipRotatePulseIndicator;
import com.mehta.demo.indicator.BallGridPulseIndicator;
import com.mehta.demo.indicator.BallPulseIndicator;
import com.mehta.demo.indicator.BallRotateIndicator;
import com.mehta.demo.indicator.BallScaleIndicator;
import com.mehta.demo.indicator.BallScaleMultipleIndicator;
import com.mehta.demo.indicator.BallScaleRippleMultipleIndicator;
import com.mehta.demo.indicator.BallSpinFadeLoaderIndicator;
import com.mehta.demo.indicator.BallTrianglePathIndicator;
import com.mehta.demo.indicator.BallZigZagDeflectIndicator;
import com.mehta.demo.indicator.BaseIndicatorController;
import com.mehta.demo.indicator.CubeTransitionIndicator;
import com.mehta.demo.indicator.LineScaleIndicator;
import com.mehta.demo.indicator.LineScalePulseOutIndicator;
import com.mehta.demo.indicator.LineSpinFadeLoaderIndicator;
import com.mehta.demo.indicator.PacmanIndicator;
import com.mehta.demo.indicator.SemiCircleSpinIndicator;
import com.mehta.demo.indicator.SquareSpinIndicator;
import com.mehta.demo.indicator.TriangleSkewSpinIndicator;

import java.util.concurrent.ThreadLocalRandom;

public class AVLoadingIndicatorView extends View{


    public static final int BallPulse=0;
    public static final int BallGridPulse=1;
    public static final int BallClipRotatePulse=3;
    public static final int SquareSpin=4;
    public static final int BallClipRotateMultiple=5;
    public static final int BallRotate=7;
    public static final int CubeTransition=8;
    public static final int BallZigZagDeflect=10;
    public static final int BallTrianglePath=11;
    public static final int BallScale=12;
    public static final int LineScale=13;
    public static final int BallScaleMultiple=15;
    public static final int BallBeat=17;
    public static final int LineScalePulseOut=18;
    public static final int BallScaleRippleMultiple=21;
    public static final int BallSpinFadeLoader=22;
    public static final int LineSpinFadeLoader=23;
    public static final int TriangleSkewSpin=24;
    public static final int Pacman=25;
    public static final int SemiCircleSpin=27;

    public static final int DEFAULT_SIZE=45;

    public int mIndicatorId = 0 ;
    int mIndicatorColor;

    Paint mPaint;

    BaseIndicatorController mIndicatorController;

    private boolean mHasAnimation;


    public AVLoadingIndicatorView(Context context) {
        super(context);
        init(null, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyle) {
        if(defStyle>0)
            mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.END);
        if(defStyle>27)
            defStyle = 0;
        mIndicatorId = defStyle;
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AVLoadingIndicatorView);
        mIndicatorId=a.getInt(R.styleable.AVLoadingIndicatorView_indicator, defStyle);
        mIndicatorColor=a.getColor(R.styleable.AVLoadingIndicatorView_indicator_color,this.getResources().getColor(R.color.colorPrimary));
        a.recycle();
        mPaint=new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        applyIndicator();
        applyAnimation();
    }

    int random(int n)
    {
        int i = ThreadLocalRandom.current().nextInt(0, 27);
        while(i==16 || i==26 || i==14 || i==2 || i==6 || i==9 || i==19 || i==20 || i==n)
        {
            i = ThreadLocalRandom.current().nextInt(0, 27);
        }
        return i;
    }

    private void applyIndicator(){
        switch (mIndicatorId){
            case BallPulse:
                mIndicatorController=new BallPulseIndicator();
                break;
            case BallGridPulse:
                mIndicatorController=new BallGridPulseIndicator();
                break;
            case BallClipRotatePulse:
                mIndicatorController=new BallClipRotatePulseIndicator();
                break;
            case SquareSpin:
                mIndicatorController=new SquareSpinIndicator();
                break;
            case BallClipRotateMultiple:
                mIndicatorController=new BallClipRotateMultipleIndicator();
                break;
            case BallRotate:
                mIndicatorController=new BallRotateIndicator();
                break;
            case CubeTransition:
                mIndicatorController=new CubeTransitionIndicator();
                break;
            case BallZigZagDeflect:
                mIndicatorController=new BallZigZagDeflectIndicator();
                break;
            case BallTrianglePath:
                mIndicatorController=new BallTrianglePathIndicator();
                break;
            case BallScale:
                mIndicatorController=new BallScaleIndicator();
                break;
            case LineScale:
                mIndicatorController=new LineScaleIndicator();
                break;
            case BallScaleMultiple:
                mIndicatorController=new BallScaleMultipleIndicator();
                break;
            case BallBeat:
                mIndicatorController=new BallBeatIndicator();
                break;
            case LineScalePulseOut:
                mIndicatorController=new LineScalePulseOutIndicator();
                break;
            case BallScaleRippleMultiple:
                mIndicatorController=new BallScaleRippleMultipleIndicator();
                break;
            case BallSpinFadeLoader:
                mIndicatorController=new BallSpinFadeLoaderIndicator();
                break;
            case LineSpinFadeLoader:
                mIndicatorController=new LineSpinFadeLoaderIndicator();
                break;
            case TriangleSkewSpin:
                mIndicatorController=new TriangleSkewSpinIndicator();
                break;
            case Pacman:
                mIndicatorController=new PacmanIndicator();
                break;
            case SemiCircleSpin:
                mIndicatorController=new SemiCircleSpinIndicator();
                break;
        }
        mIndicatorController.setTarget(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width  = measureDimension(dp2px(), widthMeasureSpec);
        int height = measureDimension(dp2px(), heightMeasureSpec);
        setMeasuredDimension(width, height);

    }

    public int measureDimension(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        @SuppressLint("DrawAllocation")
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                init(null,random(mIndicatorId));
                handler.postDelayed(this, 1250);
            }
        },1250);
        if (!mHasAnimation){
            mHasAnimation=true;
            applyAnimation();
        }
    }

    @Override
    public void setVisibility(int v) {

        if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == GONE || v == INVISIBLE) {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.END);
            } else {
                mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.START);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mIndicatorController.setAnimationStatus(BaseIndicatorController.AnimStatus.CANCEL);
    }


    void drawIndicator(Canvas canvas){
        mIndicatorController.draw(canvas,mPaint);
    }

    public void applyAnimation(){
        mIndicatorController.initAnimation();
    }

    private int dp2px() {
        return (int) getContext().getResources().getDisplayMetrics().density * AVLoadingIndicatorView.DEFAULT_SIZE;
    }


}
