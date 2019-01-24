package com.example.design_pattern.template;

/**
 * 创建抽象类，定义算法框架
 */
public abstract class AbstractSwordsman {
    //该方法为final，防止算法框架被覆写
    public final void fighting(){
        neigong();
        meridian();
        if(hasWeapons()){
            weapons();
        }
        moves();
        hook();
    }
    //钩子方法，空实现方法，子类视情况决定是否覆盖它
    protected void hook(){};
    protected abstract void neigong();
    protected abstract void weapons();
    protected abstract void moves();
    protected void meridian(){
        System.out.println("开启正经与奇经");
    }
    //钩子方法，一般用于对某个条件进行判断
    protected boolean hasWeapons(){
        return true;
    }
}
