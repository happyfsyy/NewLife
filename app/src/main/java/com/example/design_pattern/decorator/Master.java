package com.example.design_pattern.decorator;
/**
 * 抽象装饰者保持了一个对抽象组件的引用，方便调用被装饰对象中的方法。
 */
public class Master extends Swordsman{
    private Swordsman swordsman;
    public Master(Swordsman swordsman){
        this.swordsman=swordsman;
    }
    @Override
    public void attackMagic() {
        swordsman.attackMagic();
    }
}
