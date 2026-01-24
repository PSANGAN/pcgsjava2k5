package com.pcgs.core.java.pocs.heanque;

import java.util.*;

public class PriorityQueueDemo {

    // Employee class for custom object example
    static class Employee implements Comparable<Employee> {
        int id;
        String name;
        int priority;

        public Employee(int id, String name, int priority) {
            this.id = id;
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Employee other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return String.format("Employee[ID=%d, Name=%s, Priority=%d]",
                    id, name, priority);
        }
    }

    // Task class for real-world example
    static class Task {
        String description;
        int urgency;

        public Task(String description, int urgency) {
            this.description = description;
            this.urgency = urgency;
        }

        @Override
        public String toString() {
            return String.format("Task[%s, Urgency=%d]", description, urgency);
        }
    }

    public static void main(String[] args) {

        // 1. Min Heap (Default) - Smallest element has highest priority
        System.out.println("=== PriorityQueue - Min Heap (Default) ===\n");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(15);
        minHeap.offer(10);
        minHeap.offer(20);
        minHeap.offer(8);
        minHeap.offer(25);

        System.out.println("PriorityQueue (min heap): " + minHeap);
        System.out.println("Peek (min element): " + minHeap.peek());

        System.out.println("\nProcessing in priority order:");
        while (!minHeap.isEmpty()) {
            System.out.println("Polled: " + minHeap.poll());
        }

        // 2. Max Heap - Using Comparator.reverseOrder()
        System.out.println("\n=== PriorityQueue - Max Heap ===\n");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        maxHeap.offer(15);
        maxHeap.offer(10);
        maxHeap.offer(20);
        maxHeap.offer(8);
        maxHeap.offer(25);

        System.out.println("PriorityQueue (max heap): " + maxHeap);
        System.out.println("Peek (max element): " + maxHeap.peek());

        System.out.println("\nProcessing in priority order:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Polled: " + maxHeap.poll());
        }

        // 3. Custom Objects with Natural Ordering
        System.out.println("\n=== PriorityQueue with Custom Objects (Natural Ordering) ===\n");
        PriorityQueue<Employee> empQueue = new PriorityQueue<>();

        empQueue.offer(new Employee(1, "Alice", 3));
        empQueue.offer(new Employee(2, "Bob", 1));
        empQueue.offer(new Employee(3, "Charlie", 2));
        empQueue.offer(new Employee(4, "David", 5));

        System.out.println("Processing employees by priority:");
        while (!empQueue.isEmpty()) {
            System.out.println(empQueue.poll());
        }

        // 4. Custom Comparator
        System.out.println("\n=== PriorityQueue with Custom Comparator ===\n");
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(
                (t1, t2) -> Integer.compare(t2.urgency, t1.urgency) // Higher urgency first
        );

        taskQueue.offer(new Task("Write report", 3));
        taskQueue.offer(new Task("Fix critical bug", 10));
        taskQueue.offer(new Task("Code review", 5));
        taskQueue.offer(new Task("Update documentation", 2));

        System.out.println("Processing tasks by urgency (highest first):");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }

        // 5. String PriorityQueue
        System.out.println("\n=== PriorityQueue with Strings ===\n");
        PriorityQueue<String> stringPQ = new PriorityQueue<>();

        stringPQ.offer("Zebra");
        stringPQ.offer("Apple");
        stringPQ.offer("Mango");
        stringPQ.offer("Banana");

        System.out.println("Strings in alphabetical order:");
        while (!stringPQ.isEmpty()) {
            System.out.println(stringPQ.poll());
        }

        // 6. Finding K Largest Elements
        System.out.println("\n=== Finding K Largest Elements ===\n");
        int[] numbers = {7, 10, 4, 3, 20, 15, 8, 12};
        int k = 3;

        PriorityQueue<Integer> kLargest = new PriorityQueue<>();

        for (int num : numbers) {
            kLargest.offer(num);
            if (kLargest.size() > k) {
                kLargest.poll(); // Remove smallest
            }
        }

        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println(k + " largest elements: " + kLargest);

        // 7. Finding K Smallest Elements
        System.out.println("\n=== Finding K Smallest Elements ===\n");
        PriorityQueue<Integer> kSmallest = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : numbers) {
            kSmallest.offer(num);
            if (kSmallest.size() > k) {
                kSmallest.poll(); // Remove largest
            }
        }

        System.out.println(k + " smallest elements: " + kSmallest);

        // 8. Real-World: Emergency Room Triage
        System.out.println("\n=== Real-World: Emergency Room Triage ===\n");
        PriorityQueue<Patient> erQueue = new PriorityQueue<>(
                (p1, p2) -> Integer.compare(p2.severity, p1.severity)
        );

        erQueue.offer(new Patient("John", 5));
        erQueue.offer(new Patient("Mary", 9));
        erQueue.offer(new Patient("Bob", 3));
        erQueue.offer(new Patient("Alice", 10));
        erQueue.offer(new Patient("Tom", 7));

        System.out.println("Treating patients by severity:");
        while (!erQueue.isEmpty()) {
            Patient p = erQueue.poll();
            System.out.println("Treating: " + p);
        }

        // 9. Common Operations
        System.out.println("\n=== Common PriorityQueue Operations ===\n");
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        System.out.println("isEmpty: " + pq.isEmpty());
        pq.offer(5);
        pq.offer(2);
        pq.offer(8);
        System.out.println("Size: " + pq.size());
        System.out.println("Peek: " + pq.peek());
        System.out.println("Contains 5: " + pq.contains(5));
        System.out.println("Remove 2: " + pq.remove(2));
        System.out.println("After removal: " + pq);
    }

    static class Patient {
        String name;
        int severity; // 1-10, 10 being most severe

        public Patient(String name, int severity) {
            this.name = name;
            this.severity = severity;
        }

        @Override
        public String toString() {
            return String.format("Patient[%s, Severity=%d]", name, severity);
        }
    }
}
