package com.example.design_pattern.proxy;

/**
 * 真实主题类
 */
public class Ale implements IShop{
    @Override
    public void buy() {
        System.out.println("ale buy");
    }
}
