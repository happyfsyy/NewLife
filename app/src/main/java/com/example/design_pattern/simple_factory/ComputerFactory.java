package com.example.design_pattern.simple_factory;

/**
 * 这就是一个工厂类，提供了一个静态方法createComputer用来生产计算机。
 * 你只需要传入你自己想生产的计算机的品牌，它就会实例化相应品牌的计算机对象。
 */
public class ComputerFactory {
    public static Computer createComputer(String type){
        Computer mComputer=null;
        switch (type){
            case "lenovo":
                mComputer=new LenovoComputer();
                break;
            case "hp":
                mComputer=new HpComputer();
                break;
            case "asus":
                mComputer=new AsusComputer();
                break;
        }
        return mComputer;
    }
}
