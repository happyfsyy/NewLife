package com.example.design_pattern.singleton;

/**
 * 饿汉模式<p></p>
 * 这种方式在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快。
 * 这种方式基于类加载机制，避免了多线程的同步问题。在类加载的时候完成了实例化，没有达到懒加载的效果。
 * 如果从始至终未使用过这个示例，则会造成内存的浪费。
 */
public class Singleton1 {
    private static Singleton1 instance=new Singleton1();
    private Singleton1(){
    }
    public static Singleton1 getInstance(){
        return instance;
    }
}
