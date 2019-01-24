package com.example.design_pattern.factory;
import com.example.design_pattern.simple_factory.Computer;
/**
 * 广东代工厂是一个具体的工厂，其继承抽象工厂，通过反射来生产不同厂家的计算机
 */
public class GDComputerFactory extends ComputerFactory{
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer=null;
        String className=clz.getName();
        try{
            computer=(Computer)Class.forName(className).newInstance();
        }catch (ClassNotFoundException |IllegalAccessException |InstantiationException e){
            e.printStackTrace();
        }
        return (T)computer;
    }
}
