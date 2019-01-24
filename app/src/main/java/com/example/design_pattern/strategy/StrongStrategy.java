package com.example.design_pattern.strategy;

public class StrongStrategy implements FightingStrategy{
    @Override
    public void fighting() {
        System.out.println("遇到了强大的对手，张无忌使用了乾坤大挪移");
    }
}
