package com.example.design_pattern.simple_factory;

/**
 * 抽象产品类<p></p>
 * 我们创建一个计算机的抽象产品类，其有一个抽象方法用于启动计算机。
 */
public abstract class Computer {
    /**
     *  产品的抽象方法，由具体的产品类实现
     */
    public abstract void start();
}
