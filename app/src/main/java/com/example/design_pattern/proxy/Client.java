package com.example.design_pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 客户端类的代码就是代理类包含了真实主题类（被代理者），最终调用的都是真是主题类（被代理者）实现的方法。
 */
public class Client {
    public static void main(String[] args){
        //静态代理
//        IShop ale=new Ale();
//        IShop purchasing=new Purchasing(ale);
//        purchasing.buy();

        //动态代理
        IShop ale=new Ale();
        DynamicPurchasing dynamicPurchasing=new DynamicPurchasing(ale);
        ClassLoader loader=ale.getClass().getClassLoader();
        IShop purchasing=(IShop) Proxy.newProxyInstance(loader,new Class[]{IShop.class},dynamicPurchasing);
        purchasing.buy();

    }
}
