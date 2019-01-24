package com.example.design_pattern.proxy;

/**
 * 抽象主题类
 * 抽象主题类具有真实主题类和代理的共同接口方法，共同方法就是购买。
 */
public interface IShop {
    void buy();
}
