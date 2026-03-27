package com.pcgs.core.java.pocs.collections.pocs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        System.out.println("Add Operation");
        linkedList.add("A");
        linkedList.add("B");
        linkedList.addFirst("START");
        linkedList.addLast("END");
        linkedList.add(3,"C");

        doPrint(linkedList);

        System.out.println("Remove Operation");

        linkedList.remove("B");
        linkedList.removeFirst();
        linkedList.removeLast();

        doPrint(linkedList);


        linkedList.addFirst("START");
        linkedList.addLast("END");
        linkedList.add(3,"C");

        System.out.println("Read Operation");
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println("Index Of 'C' = " + linkedList.indexOf("C"));
        System.out.println("Last Item Index = " + linkedList.lastIndexOf("C"));

        System.out.println("Queue Operation");
        Queue<Integer> integerQueue = new LinkedList<>();
        integerQueue.offer(101);
        integerQueue.offer(201);
        integerQueue.offer(301);

        doPrint(integerQueue);

        System.out.println("Peek -" + integerQueue.peek());
        System.out.println("Poll -" + integerQueue.poll());

        doPrint(integerQueue);

        System.out.println("Stack Operation");
        Deque<Double> doubleStack = new LinkedList<>();
        doubleStack.push(123.25d);
        doubleStack.push(741.25);
        doubleStack.push(984562.25d);

        doPrint(doubleStack);
        System.out.println("Peek -" + doubleStack.peek());
        System.out.println("Poll -" + doubleStack.pop());
        doPrint(doubleStack);

    }

    static <T> void doPrint(Iterable<?> list)
    {
        for(Object temp:list)
        {
            System.out.println(temp);
        }
    }
}
