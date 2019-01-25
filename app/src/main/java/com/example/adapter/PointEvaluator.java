package com.example.adapter;

import android.animation.TypeEvaluator;

import com.example.bean.Point;

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x=startValue.getX()+(endValue.getX()-startValue.getX())*fraction;
        float y=startValue.getY()+(endValue.getY()-startValue.getY())*fraction;
        Point interpolation=new Point(x,y);
        return interpolation;
    }
}
