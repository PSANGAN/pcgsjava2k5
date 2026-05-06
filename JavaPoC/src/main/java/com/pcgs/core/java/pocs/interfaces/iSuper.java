package com.pcgs.core.java.pocs.interfaces;

public interface iSuper {
    void doSuper();
    default void doSomething() {
        System.out.println("Doing something - iSuper");
    }
}
