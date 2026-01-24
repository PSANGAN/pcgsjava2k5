package com.pcgs.core.java.pocs.heanque;

import java.util.*;

public class HeapDemo {

    // Custom Min Heap Implementation
    static class MinHeap {
        private ArrayList<Integer> heap;

        public MinHeap() {
            heap = new ArrayList<>();
        }

        // Get parent index
        private int parent(int i) {
            return (i - 1) / 2;
        }

        // Get left child index
        private int leftChild(int i) {
            return 2 * i + 1;
        }

        // Get right child index
        private int rightChild(int i) {
            return 2 * i + 2;
        }

        // Insert element
        public void insert(int value) {
            heap.add(value);
            int current = heap.size() - 1;

            // Heapify up
            while (current > 0 && heap.get(current) < heap.get(parent(current))) {
                swap(current, parent(current));
                current = parent(current);
            }
            System.out.println("Inserted: " + value);
        }

        // Extract minimum (root)
        public int extractMin() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }

            int min = heap.get(0);
            int lastElement = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                heapifyDown(0);
            }

            return min;
        }

        // Get minimum without removing
        public int peek() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }
            return heap.get(0);
        }

        // Heapify down
        private void heapifyDown(int i) {
            int smallest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest != i) {
                swap(i, smallest);
                heapifyDown(smallest);
            }
        }

        // Swap elements
        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public int size() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public void display() {
            System.out.println("Heap: " + heap);
        }
    }

    // Custom Max Heap Implementation
    static class MaxHeap {
        private ArrayList<Integer> heap;

        public MaxHeap() {
            heap = new ArrayList<>();
        }

        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return 2 * i + 1; }
        private int rightChild(int i) { return 2 * i + 2; }

        public void insert(int value) {
            heap.add(value);
            int current = heap.size() - 1;

            // Heapify up (max heap)
            while (current > 0 && heap.get(current) > heap.get(parent(current))) {
                swap(current, parent(current));
                current = parent(current);
            }
            System.out.println("Inserted: " + value);
        }

        public int extractMax() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }

            int max = heap.get(0);
            int lastElement = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, lastElement);
                heapifyDown(0);
            }

            return max;
        }

        public int peek() {
            if (heap.isEmpty()) {
                throw new IllegalStateException("Heap is empty");
            }
            return heap.get(0);
        }

        private void heapifyDown(int i) {
            int largest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < heap.size() && heap.get(left) > heap.get(largest)) {
                largest = left;
            }

            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
                largest = right;
            }

            if (largest != i) {
                swap(i, largest);
                heapifyDown(largest);
            }
        }

        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public void display() {
            System.out.println("Heap: " + heap);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Min Heap Implementation ===\n");
        MinHeap minHeap = new MinHeap();

        minHeap.insert(15);
        minHeap.insert(10);
        minHeap.insert(20);
        minHeap.insert(8);
        minHeap.insert(25);
        minHeap.display();

        System.out.println("\nMinimum element: " + minHeap.peek());
        System.out.println("Extracted min: " + minHeap.extractMin());
        minHeap.display();

        System.out.println("\n=== Max Heap Implementation ===\n");
        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(15);
        maxHeap.insert(10);
        maxHeap.insert(20);
        maxHeap.insert(8);
        maxHeap.insert(25);
        maxHeap.display();

        System.out.println("\nMaximum element: " + maxHeap.peek());
        System.out.println("Extracted max: " + maxHeap.extractMax());
        maxHeap.display();

        System.out.println("\n=== Heap Sort Example ===\n");
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(arr));

        MinHeap sortHeap = new MinHeap();
        for (int num : arr) {
            sortHeap.insert(num);
        }

        System.out.print("Sorted array (using Min Heap): ");
        while (!sortHeap.isEmpty()) {
            System.out.print(sortHeap.extractMin() + " ");
        }
        System.out.println();
    }
}
