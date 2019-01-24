package com.example.design_pattern.singleton;

import java.io.ObjectStreamException;

/**
 * 枚举单例<p></p>
 * 默认枚举示例的创建是线程安全的，并且在任何情况下都是单例。
 * 枚举单例的优点是简单，但是大部分应用开发很少用枚举，其可读性并不是很高。
 */
public enum Singleton6 {
    INSTANCE;
    public void doSomething(){

    }

}
/*
 * 在上面讲的几种单例模式实现中，有一种情况下其会重新创建对象，那就是反序列化：
 * 将一个单例实例对象写到硬盘再读回来，从而获得一个实例。
 * 反序列化操作提供了readResolve方法，这个方法可以让开发人员控制对象的反序列化。
 * 在上述几个方法示例中，如果要杜绝单例对象被反序列化时重新生成对象，就必须加入readresolve方法
 *
 *  private Object readResolve() throws ObjectStreamException{
        return instance;
    }
 */