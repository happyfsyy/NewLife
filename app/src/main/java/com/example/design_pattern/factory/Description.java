package com.example.design_pattern.factory;

import android.app.AlertDialog;

/**
 * 工厂方法模式<p></p>
 * 定义：定义一个用于创建对象的接口，让子类决定实例化哪个类。工厂方法使一个类的实例化延迟到其子类。
 *
 * 工厂方法模式中有如下角色。
 * <li>Product：抽象产品类。</li>
 * <li>ConcreteProduct：具体产品类，实现Product接口。</li>
 * <li>Factory：抽象工厂类，该方法返回一个Product产品类型的对象。</li>
 * <li>ConcreteFactory：具体工厂类，返回ConcreteProduct实例。</li>
 *
 * 对于简单工厂模式，如果我们想要增加产品，生产苹果计算机，就需要在工厂类中添加一个case分支，这违背了开放封闭原则。
 * 在工厂模式中，如果我们想要生产苹果计算机，无须修改工厂类，直接创建产品即可。
 */
public class Description {
    private Description(){
        AlertDialog dialog=new AlertDialog.Builder(null)
                .setTitle("").create();
    }
}
