package com.pcgs.core.java.pocs.interfaces;

@FunctionalInterface
public interface SampleFunInterface {
    void doSomething();
    default String doPrint(String str) {
        this.doSomethingPrivate();
        return str;
    }
    static void doSomethingStatic() {
        System.out.println("Doing something static");
    }

    private void doSomethingPrivate() {
        System.out.println("Doing something private");
    }
}

@FunctionalInterface
 interface AnotherSampleFunInterface extends SampleFunInterface {
}

class ClientOfFunctionalInterface {
    public static void main(String[] args) {
        SampleFunInterface sampleFunInterface = () -> System.out.println("Doing something");

        // No — a lambda expression cannot invoke a functional interface’s
        // static method as part of the lambda mechanism
        // The same rule applies to default methods — you cannot invoke them via a lambda instance.
       //  SampleFunInterface sampleFunInterfaceStatic = (str) -> "Hello " + str;

        AnotherSampleFunInterface anotherSampleFunInterface = () -> System.out.println("Doing something");
        anotherSampleFunInterface.doSomething();
        // anotherSampleFunInterface.doSomethingStatic();
        anotherSampleFunInterface.doPrint("Hello");

    }
}