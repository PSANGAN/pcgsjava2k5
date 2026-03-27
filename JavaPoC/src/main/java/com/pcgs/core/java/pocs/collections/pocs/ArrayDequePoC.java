package com.pcgs.core.java.pocs.collections.pocs;

import java.time.LocalDate;
import java.util.*;

public class ArrayDequePoC {
    public static void main(String[] args) {
        Queue<Integer> normalQueue = new ArrayDeque<>();
        normalQueue.add(301);
        normalQueue.add(201);
        normalQueue.add(301);

        doPrint(normalQueue);
        System.out.println("Queue size before processing: " + normalQueue.size());
        while (!normalQueue.isEmpty()) {
            Integer task = normalQueue.poll();   // FIFO
            System.out.println("Processing: " + task);
        }
        System.out.println("Queue size after processing: " + normalQueue.size());


        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.add("TASK");
        pq.add("task");
        pq.add("tAsK");

//        pq.offer(new Job(3, "Low Priority"));
//        pq.offer(new Job(1, "High Priority"));
//        pq.offer(new Job(2, "Medium Priority"));
//
        while (!pq.isEmpty()) {
            System.out.println("Executing: " + pq.poll());
        }

        PriorityQueue<Integer> pqInt = new PriorityQueue<>();
        pqInt.add(100);
        pqInt.add(10);
        pqInt.add(1000);


        while (!pqInt.isEmpty()) {
            System.out.println("Priority: " + pqInt.poll());
        }


        PriorityQueue<Job> pqJOb = new PriorityQueue<>(
                Comparator.comparingInt(j -> j.priority)
        );

       pqJOb.offer(new Job(3, "Low Priority"));
       pqJOb.offer(new Job(1, "High Priority"));
       pqJOb.offer(new Job(2, "Medium Priority"));

        while (!pqJOb.isEmpty()) {
            System.out.println("Executing: " + pqJOb.poll());
        }

//        Queue<Integer> taskQueue = new LinkedList<>();
//        taskQueue.offer(1);
//        taskQueue.offer(2);
//        taskQueue.offer(3);

    }

    static <T> void doPrint(Iterable<?> list)
    {
        for(Object temp:list)
        {
            System.out.println(temp);
        }
    }
}

class Job {
    int priority;
    String name;

    Job(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public String toString() {
        return priority + " - " + name;
    }
}
