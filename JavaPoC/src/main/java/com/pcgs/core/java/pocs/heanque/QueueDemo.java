package com.pcgs.core.java.pocs.heanque;

import java.util.*;

public class QueueDemo {

    // Custom Queue implementation using Array
    static class CustomQueue {
        private int[] arr;
        private int front;
        private int rear;
        private int capacity;
        private int size;

        public CustomQueue(int capacity) {
            this.capacity = capacity;
            this.arr = new int[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void enqueue(int item) {
            if (isFull()) {
                throw new IllegalStateException("Queue is full");
            }
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
            System.out.println("Enqueued: " + item);
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            int item = arr[front];
            front = (front + 1) % capacity;
            size--;
            System.out.println("Dequeued: " + item);
            return item;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }
            return arr[front];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public int getSize() {
            return size;
        }

        public void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            System.out.print("Queue: ");
            for (int i = 0; i < size; i++) {
                System.out.print(arr[(front + i) % capacity] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Custom Queue Implementation ===\n");
        CustomQueue cq = new CustomQueue(5);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();

        System.out.println("Front element: " + cq.peek());
        cq.dequeue();
        cq.display();

        System.out.println("\n=== Java LinkedList as Queue ===\n");
        Queue<String> queue = new LinkedList<>();

        // Add elements
        queue.offer("Alice");
        queue.offer("Bob");
        queue.offer("Charlie");
        queue.offer("David");
        System.out.println("Queue: " + queue);

        // Peek at front
        System.out.println("Front element (peek): " + queue.peek());

        // Remove elements
        System.out.println("Removed: " + queue.poll());
        System.out.println("Removed: " + queue.poll());
        System.out.println("Queue after removals: " + queue);

        // Check size
        System.out.println("Size: " + queue.size());

        System.out.println("\n=== ArrayDeque as Queue (Better Performance) ===\n");
        Queue<Integer> deque = new ArrayDeque<>();

        deque.offer(100);
        deque.offer(200);
        deque.offer(300);
        System.out.println("Deque: " + deque);

        while (!deque.isEmpty()) {
            System.out.println("Processing: " + deque.poll());
        }

        System.out.println("\n=== Real-World Example: Task Processing ===\n");
        Queue<String> taskQueue = new LinkedList<>();

        // Add tasks
        taskQueue.offer("Process Payment");
        taskQueue.offer("Send Email");
        taskQueue.offer("Update Database");
        taskQueue.offer("Generate Report");

        System.out.println("Tasks in queue: " + taskQueue.size());

        // Process tasks in FIFO order
        while (!taskQueue.isEmpty()) {
            String task = taskQueue.poll();
            System.out.println("Executing task: " + task);
        }
    }
}
