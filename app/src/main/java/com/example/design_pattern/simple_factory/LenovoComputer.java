package com.example.design_pattern.simple_factory;

/**
 * 具体产品类<p></p>
 * 接着我们创建了各个品牌的计算机，其都继承了自己的父类Compute
 */
public class LenovoComputer extends Computer{
    @Override
    public void start() {
        System.out.println("联想计算机启动");
    }
}

