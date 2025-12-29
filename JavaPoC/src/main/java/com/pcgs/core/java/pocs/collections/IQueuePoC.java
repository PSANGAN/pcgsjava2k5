package com.pcgs.core.java.pocs.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class IQueuePoC {
    public static void main(String[] args) {
        System.out.println("IQueuePoC");

        Queue<Double> queue = new PriorityQueue<>();
        queue.add(101.0D);
        queue.add(201.0D);
        queue.add(301.0D);

        System.out.println(queue); // [101.0, 201.0, 301.0]
        queue.poll();
        System.out.println(queue); // [201.0, 301.0]

        System.out.println(queue.peek()); // 201.0
        System.out.println(queue.element()); // 201.0

        Iterator it = queue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
