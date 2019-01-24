package com.example.design_pattern.decorator;
/**
 * 装饰者具体实现类
 */
public class HongQiGong extends Master{
    public HongQiGong(Swordsman swordsman) {
        super(swordsman);
    }
    private void teachAttachMagic(){
        System.out.println("洪七公教杨过打狗棒法");
    }
    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttachMagic();
    }
}
