package com.example.design_pattern.decorator;
/**
 * 装饰者模式
 * 装饰模式是结构性设计模式之一，其在不必改变类文件和使用继承的情况下，动态地扩展一个对象的功能，是继承的替代方案之一。
 * 它通过创建一个包装对象，也就是装饰来包裹真实的对象。
 * 定义：动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更加灵活。
 * 装饰模式中有如下角色：
 * <li>Component：抽象组件，可以是接口或抽象类，被装饰的最原始的对象。</li>
 * <li>ConcreteComponent：组件具体实现类。Component的具体实现类，被装饰的具体对象。</li>
 * <li>Decorator：抽象装饰者，从外类来扩展Component类的功能，但对于Component
 * 来说无须知道Decorator的存在。再它的属性中必然有一个private变量指向Component抽象组件。</li>
 * <li>ConcreteDecorator：装饰者的具体实现类。</li>
 */
public class Description {
}
