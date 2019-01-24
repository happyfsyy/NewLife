package com.example.design_pattern.factory;

import com.example.design_pattern.simple_factory.AsusComputer;
import com.example.design_pattern.simple_factory.HpComputer;
import com.example.design_pattern.simple_factory.LenovoComputer;

/**
 * 客户端调用，客户端创建了GDComputerFactory，并分别生产了联想、惠普和华硕计算机
 */
public class Client {
    public static void main(String[] args){
        ComputerFactory computerFactory=new GDComputerFactory();
        LenovoComputer lenovoComputer=computerFactory.createComputer(LenovoComputer.class);
        lenovoComputer.start();
        HpComputer hpComputer=computerFactory.createComputer(HpComputer.class);
        hpComputer.start();
        AsusComputer asusComputer=computerFactory.createComputer(AsusComputer.class);
        asusComputer.start();
    }
}
