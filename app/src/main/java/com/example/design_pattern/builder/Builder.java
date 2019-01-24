package com.example.design_pattern.builder;

/**
 * 创建Builder类规范产品的组建。
 * 商家组装计算机有一套组装方法的模板，就是一个抽象的Builder类，
 * 其里面提供了安装CPU、主板和内存的方法，以及组装成计算机的create方法。
 */
public abstract class Builder {
    public abstract void buildCpu(String cpu);
    public abstract void buildMainBoard(String mainBoard);
    public abstract void buildRam(String ram);
    public abstract Computer create();
}
