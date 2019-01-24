package com.example.design_pattern.builder;

/**
 * 用导演类来统一组装过程
 * 商家的导演类用来规范组装计算机的流程规范，先安装主板，再安装CPU，最后安装内存，并组装成计算机。
 */
public class Director {
    private Builder builder;
    public Director(Builder builder){
        this.builder=builder;
    }
    public Computer createComputer(String cpu,String mainboard,String ram){
        builder.buildMainBoard(mainboard);
        builder.buildCpu(cpu);
        builder.buildRam(ram);
        return builder.create();
    }
}
