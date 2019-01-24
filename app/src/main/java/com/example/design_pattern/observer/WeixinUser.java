package com.example.design_pattern.observer;

/**
 * 具体观察者
 * 微信用户是观察者，里面实现了更新的方法
 */
public class WeixinUser implements Observer{
    private String name;
    public WeixinUser(String name){
        this.name=name;
    }
    @Override
    public void update(String message) {
        System.out.println(name+"-"+message);
    }
}
