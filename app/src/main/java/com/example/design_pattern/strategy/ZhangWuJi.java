package com.example.design_pattern.strategy;

public class ZhangWuJi {
    public static void main(String[]args){
        Context context;
        context=new Context(new WeakStrategy());
        context.fighting();
        context=new Context(new CommonStrategy());
        context.fighting();
        context=new Context(new StrongStrategy());
        context.fighting();
    }
}
