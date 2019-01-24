package com.example.design_pattern.decorator;
/**
 * 组件具体实现类
 * 也就是被装饰的具体对象。
 */
public class YangGuo extends Swordsman{
    @Override
    public void attackMagic() {
        System.out.println("杨过使用全真剑法");
    }
}
