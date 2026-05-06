package com.pcgs.core.java.pocs.interfaces;

public interface iWork {
    void doWork();
    default void doSomething() {
        System.out.println("Doing something - iWork");
    }
}
