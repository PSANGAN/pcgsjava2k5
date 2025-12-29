package com.pcgs.core.java.pocs.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

class IDequePoC {
    public static void main(String[] args) {
        System.out.println("IDequePoC");

        Deque<String> deque = new ArrayDeque<>();
        deque.add("Dummy");
        deque.add("Rummy");
        deque.add("Mummy");

        System.out.println(deque); // [Dummy, Rummy, Mummy]

        Deque<Integer> integerDeque = new ArrayDeque<>();
        integerDeque.add(123);
        integerDeque.addFirst(132);
        integerDeque.add(231);
        integerDeque.add(213);
        integerDeque.add(312);
        integerDeque.add(321);

        System.out.println(integerDeque); // [132, 123, 231, 213, 312, 321]

        integerDeque.pop();
        System.out.println(integerDeque); // [123, 231, 213, 312, 321]

        integerDeque.removeFirst();
        System.out.println(integerDeque); // [231, 213, 312, 321]

        integerDeque.removeLast();
        System.out.println(integerDeque); // [231, 213, 312]

        for (Iterator<Integer> itr = integerDeque.descendingIterator();  itr.hasNext();) {
            System.out.print(itr.next() + " ");
        }


    }
}
