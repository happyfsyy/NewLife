package com.example.design_pattern.proxy;

/**
 * 代理模式
 * 定义：为其他对象提供一种代理以控制对这个对象的访问。
 * 在代理模式中有如下角色：
 * <li>Subject：抽象主题类，声明真实主题与代理的共同接口方法。</li>
 * <li>RealSubject：真实主题类，代理类所代表的真实主题。客户端通过代理类间接地调用真实主题类的的方法。</li>
 * <li>Proxy：代理类，持有对真实主题类的引用的，在其所实现的接口方法中调用真实主题类中的相应的接口方法执行。</li>
 * <li>Client：客户端类。</li>
 * 这里使用“委托朋友买东西”这个例子来讲解下。
 */
public class Description {
}
