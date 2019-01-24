package com.example.design_pattern.decorator;

public class Client {
    public static void main(String[]args){
        YangGuo yangGuo=new YangGuo();
        HongQiGong hongQiGong=new HongQiGong(yangGuo);
        hongQiGong.attackMagic();
    }
}
