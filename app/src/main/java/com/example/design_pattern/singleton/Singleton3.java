package com.example.design_pattern.singleton;
/**
 * 懒汉模式（线程安全）<p></p>
 * 这种写法能够在多线程中很好的工作，但是每次调用getInstance()方法时都需要进行同步。
 * 这会造成不必要的同步开销，而且大部分时候，我们是用不到同步的。
 */
public class Singleton3 {
    private static Singleton3 instance;
    private Singleton3(){
    }
    public static synchronized Singleton3 getInstance(){
        if(instance==null){
            instance=new Singleton3();
        }
        return instance;
    }
}
