package com.example.design_pattern.builder;

/**
 * 创建产品类
 * 我要组装一台计算机，计算机被抽象为Computer类，它有三个部件：CPU、主板和内存。
 * 并在里面提供了三个方法，分别用来设置CPU，主板和内存。
 */
public class Computer {
    private String mCpu;
    private String mMainBoard;
    private String mRam;
    public void setmCpu(String mCpu){
        this.mCpu=mCpu;
    }
    public void setmMainBoard(String mMainBoard){
        this.mMainBoard=mMainBoard;
    }
    public void setmRam(String mRam){
        this.mRam=mRam;
    }

    public static class ComputerBuilder{
        Computer computer=new Computer();
        public ComputerBuilder buildCpu(String cpu){
            computer.setmCpu(cpu);
            return this;
        }
        public ComputerBuilder buildMainBoard(String mainBoard){
            computer.setmMainBoard(mainBoard);
            return this;
        }
        public ComputerBuilder buildRam(String ram){
            computer.setmRam(ram);
            return this;
        }
        public Computer createComputer(){
            return computer;
        }
    }
}
