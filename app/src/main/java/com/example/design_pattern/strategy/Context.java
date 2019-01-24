package com.example.design_pattern.strategy;

public class Context {
    private FightingStrategy fightingStrategy;
    public Context(FightingStrategy fightingStrategy){
        this.fightingStrategy=fightingStrategy;
    }
    public void fighting(){
        fightingStrategy.fighting();
    }
}
