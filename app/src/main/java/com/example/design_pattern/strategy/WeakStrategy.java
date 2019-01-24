package com.example.design_pattern.strategy;

/**
 * 具体策略实现
 * 定义了三个策略来实现策略接口，用来对付三个实力层次的对手
 */
public class WeakStrategy implements FightingStrategy{
    @Override
    public void fighting() {
        System.out.println("遇到了较弱的对手，张无忌使用了太极剑");
    }
}
