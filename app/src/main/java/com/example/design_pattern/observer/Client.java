package com.example.design_pattern.observer;

public class Client {
    public static void main(String[]args){
        SubscriptionSubject subscriptionSubject=new SubscriptionSubject();
        WeixinUser user1=new WeixinUser("xx");
        WeixinUser user2=new WeixinUser("xxs");
        WeixinUser user3=new WeixinUser("xxx");
        subscriptionSubject.attach(user1);
        subscriptionSubject.attach(user2);
        subscriptionSubject.attach(user3);
        subscriptionSubject.notify("message updated!!!!");
    }
}
