package com.pcgs.core.java.pocs.collections.queue;

import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Min-Heap (default): smallest element has highest priority
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(20);

        System.out.println("Min-Heap:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // 10 20 30
        }
        System.out.println();

        // Max-Heap: largest element has highest priority
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(20);

        System.out.println("Max-Heap:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // 30 20 10
        }
        System.out.println();

        // Custom Priority with Objects
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("Low priority task", 3));
        taskQueue.offer(new Task("High priority task", 1));
        taskQueue.offer(new Task("Medium priority task", 2));

        System.out.println("\nTask Priority Queue:");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }
}

class Task implements Comparable<Task> {
    String name;
    int priority; // Lower number = higher priority

    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}
