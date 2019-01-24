package com.example.design_pattern.observer;

/**
 * 抽象被观察者，提供了attach，detach，notify三个方法
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notify(String message);
}
