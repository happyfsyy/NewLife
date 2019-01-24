package com.example.design_pattern.builder;

/**
 * 客户端调用导演类
 * 最后商家用导演类组装计算机。我们只需要提供自己想要的CPU、主板和内存就可以了，
 * 至于商家是怎样组装计算机的，我们无需知道。
 */
public class Client {
    public static void main(String[] args){
        Builder builder=new MoonComputerBuilder();
        Director director=new Director(builder);
        Computer computer=director.createComputer("i5","xxs","ddr4");

//        Computer computer=new Computer.ComputerBuilder()
//                .buildCpu("i5")
//                .buildMainBoard("xxs")
//                .buildRam("ddr6")
//                .createComputer();
    }
}
