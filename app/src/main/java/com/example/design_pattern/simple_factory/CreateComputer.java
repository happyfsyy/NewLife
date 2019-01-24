package com.example.design_pattern.simple_factory;

/**
 * 客户端调用工厂类，传入'hp"生产出惠普计算机，并调用该计算机对象的start方法。
 */
public class CreateComputer {
    public static void main(String[]args){
        ComputerFactory.createComputer("hp").start();
    }
}
