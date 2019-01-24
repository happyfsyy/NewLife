package com.example.design_pattern.singleton;
/**
 * 双重检查模式（DCL）Double-Check Locking
 * 这种写法在getInstance方法中对Singleton进行了两次判空：
 * 第一次是为了不必要的同步，第二次是在Singleton等于null的情况下才创建实例。
 * 在这里使用volatile会或多或少影响性能，但考虑到程序的正确性，牺牲这点性能还是值得的。
 * DCL的优点是资源利用率高。第一次执行getInstance时单例对象才被初始化，效率高。
 * 其缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷。
 */
public class Singleton4 {
    private static volatile Singleton4 instance;
    private Singleton4(){
    }
    public static Singleton4 getInstance(){
        if(instance==null){
            synchronized (Singleton4.class){
                if(instance==null){
                    instance=new Singleton4();
                }
            }
        }
        return instance;
    }
}
