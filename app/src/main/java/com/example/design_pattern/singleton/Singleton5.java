package com.example.design_pattern.singleton;
/**
 * 静态内部类单例模式<p></p>
 * 第一次加载Singleton类时并不会初始化instance，只有第一次调用getInstance方法时，
 * 虚拟机加载SingletonHolder并且初始化instance。这样不仅保证了线程安全，也能保证Singleton类的唯一性。
 * 推荐使用静态内部类单例模式。
 */
public class Singleton5 {
    private Singleton5(){
    }
    public static Singleton5 getInstance(){
        return Singleton5Holder.instance;
    }
    private static class Singleton5Holder{
        private static Singleton5 instance=new Singleton5();
    }
}
