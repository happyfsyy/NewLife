package com.example.bean;

public class Day {
    private String name;
    private int resId;
    public Day(String name,int resId){
        this.name=name;
        this.resId=resId;
    }
    public String getName(){
        return name;
    }
    public int getResId() {
        return resId;
    }
}
