package com.example.design_pattern.builder;

public class MoonComputerBuilder extends Builder{
    private Computer computer=new Computer();
    @Override
    public void buildCpu(String cpu) {
        computer.setmCpu(cpu);
    }
    @Override
    public void buildMainBoard(String mainBoard) {
        computer.setmMainBoard(mainBoard);
    }
    @Override
    public void buildRam(String ram) {
        computer.setmRam(ram);
    }
    @Override
    public Computer create() {
        return computer;
    }
}
