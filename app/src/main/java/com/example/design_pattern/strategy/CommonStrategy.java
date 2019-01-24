package com.example.design_pattern.strategy;

public class CommonStrategy implements FightingStrategy{
    @Override
    public void fighting() {
        System.out.println("遇到了普通的对手，张无忌使用了圣火令");
    }
}
