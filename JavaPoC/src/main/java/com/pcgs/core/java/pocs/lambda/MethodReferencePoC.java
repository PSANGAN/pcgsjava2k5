package com.pcgs.core.java.pocs.lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MethodReferencePoC {
    public static void main(String[] args) {

        // Declaration of lambda
        MyInterface reference = (input) -> System.out.println(input);
        reference.myMethod("input");

        // Declaration of Method reference using Interface Implementor
        MyInterface myClassRefernce = new MyClass();
        Consumer<String> consumerAccept = myClassRefernce::myMethod;
        consumerAccept.accept("input");

        // Declaration of Method reference using Interface Implementor
        Supplier<String> supplierGet = myClassRefernce::defaultMethod;
        System.out.println(supplierGet.get());




    }
}

class MyClass implements MyInterface {
    @Override
    public String defaultMethod() {
        return MyInterface.super.defaultMethod();
    }

    @Override
    public void myMethod(String input) {
        System.out.println("myClass - MyInterface - myMethod -" +  input);
    }

    public static void staticMethod() {
        System.out.println("Static method");
    }
}

@FunctionalInterface
interface MyInterface {
    void myMethod(String input);

    default String defaultMethod() {
        this.privateMethod();
        return "Default method";
    }

    private void privateMethod() {
        System.out.println("Private method");
    }

    static String staticMethod() {
        return "Static method";
    }
}