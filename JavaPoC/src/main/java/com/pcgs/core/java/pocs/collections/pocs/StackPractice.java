package com.pcgs.core.java.pocs.collections.pocs;

import java.util.Stack;

public class StackPractice {

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();

        integerStack.push(101);
        integerStack.push(201);
        integerStack.push(301);

        doPrint(integerStack);

        System.out.println("Top: " + integerStack.peek());

        // Pop
        System.out.println("Popped: " + integerStack.pop());
        System.out.println("After pop: " );
        doPrint(integerStack);

        // Search
        System.out.println("Position of A: " + integerStack.search(201));

    }

    static <T> void doPrint(Iterable<?> list)
    {
        for(Object temp:list)
        {
            System.out.println(temp);
        }
    }
}
