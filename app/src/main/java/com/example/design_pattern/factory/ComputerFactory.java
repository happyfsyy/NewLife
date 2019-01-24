package com.example.design_pattern.factory;

import com.example.design_pattern.simple_factory.Computer;

/**
 * 创建抽象工厂<p></p>
 * 抽象工厂里有一个createComputer方法，想生产哪个品牌的计算机就生产哪个品牌的。
 */
public abstract class ComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
