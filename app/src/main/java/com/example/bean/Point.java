package com.example.bean;

/**
 * 描述自定义View中坐标点的类
 */
public class Point {
    /** 在当前view中的x坐标*/
    private float x;
    /** 在当前View中的Y坐标*/
    private float y;
    public Point(float x,float y){
        this.x=x;
        this.y=y;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
}
