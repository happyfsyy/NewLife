package com.example.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;
import com.example.adapter.PointEvaluator;
import com.example.bean.Point;
import com.example.newlife.R;

import androidx.annotation.Nullable;

public class ValueAnimatorView extends View {
    private Paint paint;
    private Point curPoint;
    private final int RADIUS=50;
    private PointEvaluator pointEvaluator;
    private int color;
    public ValueAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.pink));
        paint.setStyle(Paint.Style.FILL);
        pointEvaluator=new PointEvaluator();
    }
    @Override
    protected void onDraw(final Canvas canvas) {
        if(curPoint==null){
            startAnimation0(canvas);
        }else {
            canvas.drawCircle(curPoint.getX(), curPoint.getY(), RADIUS, paint);
        }
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }
    private void startAnimation(Canvas canvas){
        curPoint=new Point(RADIUS,RADIUS);
        canvas.drawCircle(curPoint.getX(),curPoint.getY(),RADIUS,paint);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator animator=ValueAnimator.ofObject(pointEvaluator,curPoint,endPoint);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curPoint=(Point)animation.getAnimatedValue();
//                    invalidate();
            }
        });
        ObjectAnimator objectAnimator=ObjectAnimator.ofArgb(this,"color",0xff0000ff,0xffff0000);
        objectAnimator.setDuration(5000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(animator).with(objectAnimator);
        animatorSet.start();
    }
    private void startAnimation0(Canvas canvas){
        curPoint=new Point(RADIUS,RADIUS);
        canvas.drawCircle(curPoint.getX(),curPoint.getY(),RADIUS,paint);
        Point endPoint=new Point(getWidth()-RADIUS,getHeight()-RADIUS);
        ValueAnimator animator=ValueAnimator.ofObject(pointEvaluator,curPoint,endPoint);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curPoint=(Point)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
    class DecelerateAccelerateInterpolator implements TimeInterpolator {
        @Override
        public float getInterpolation(float input) {
            if(input<=0.5){
                return (float)Math.sin(input*Math.PI)/2;
            }else{
                return 1-(float)Math.sin(input*Math.PI)/2;
            }
        }
    }
}
