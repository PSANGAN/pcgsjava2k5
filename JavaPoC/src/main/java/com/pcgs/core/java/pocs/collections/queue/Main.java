package com.pcgs.core.java.pocs.collections.queue;

public class Main {
    public static void main(String[] args) {
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        queue.enqueue("Charlie");
        queue.display(); // Queue: Alice Bob Charlie

        System.out.println("Dequeued: " + queue.dequeue()); // Alice
        System.out.println("Front: " + queue.peek()); // Bob

        ArrayQueue<Integer> aqueue = new ArrayQueue<>(5);
        aqueue.enqueue(10);
        aqueue.enqueue(20);
        aqueue.enqueue(30);
        aqueue.display(); // Queue: 10 20 30

        System.out.println("Dequeued: " + queue.dequeue()); // 10
        System.out.println("Front: " + queue.peek()); // 20
    }
}
