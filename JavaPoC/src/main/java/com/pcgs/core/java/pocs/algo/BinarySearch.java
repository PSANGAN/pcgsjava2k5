package com.pcgs.core.java.pocs.algo;

public class BinarySearch {

    /**
     * Iterative binary search implementation
     * @param arr Sorted array to search in
     * @param target Element to search for
     * @return Index of target element if found, -1 otherwise
     */
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            // Calculate middle index (avoids overflow)
            int mid = left + (right - left) / 2;

            // Check if target is at middle
            if (arr[mid] == target) {
                return mid;
            }

            // If target is greater, ignore left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        // Element not found
        return -1;
    }

    /**
     * Recursive binary search implementation
     * @param arr Sorted array to search in
     * @param target Element to search for
     * @param left Starting index
     * @param right Ending index
     * @return Index of target element if found, -1 otherwise
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }

        // Calculate middle index
        int mid = left + (right - left) / 2;

        // Check if target is at middle
        if (arr[mid] == target) {
            return mid;
        }

        // If target is smaller, search left half
        if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }

        // If target is greater, search right half
        return binarySearchRecursive(arr, target, mid + 1, right);
    }

    /**
     * Helper method for recursive search
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursive(arr, target, 0, arr.length - 1);
    }

    /**
     * Generic binary search for comparable objects
     */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = arr[mid].compareTo(target);

            if (comparison == 0) {
                return mid;
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // IMPORTANT: Array must be sorted for binary search!
        int[] sortedNumbers = {12, 23, 34, 45, 56, 67, 78, 89, 90};

        System.out.println("Array: " + java.util.Arrays.toString(sortedNumbers));
        System.out.println();

        // Example 1: Iterative search - element exists
        int target = 67;
        int result = binarySearchIterative(sortedNumbers, target);
        System.out.println("Iterative Search:");
        System.out.println("Searching for " + target + ": " +
                (result != -1 ? "Found at index " + result : "Not found"));

        // Example 2: Recursive search - element exists
        target = 23;
        result = binarySearchRecursive(sortedNumbers, target);
        System.out.println("\nRecursive Search:");
        System.out.println("Searching for " + target + ": " +
                (result != -1 ? "Found at index " + result : "Not found"));

        // Example 3: Element doesn't exist
        target = 50;
        result = binarySearchIterative(sortedNumbers, target);
        System.out.println("\nSearching for non-existent element:");
        System.out.println("Searching for " + target + ": " +
                (result != -1 ? "Found at index " + result : "Not found"));

        // Example 4: Generic search with Strings
        String[] sortedNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank"};
        String targetName = "David";
        result = binarySearch(sortedNames, targetName);
        System.out.println("\nGeneric Search with Strings:");
        System.out.println("Searching for " + targetName + ": " +
                (result != -1 ? "Found at index " + result : "Not found"));

        // Example 5: Comparison with linear search
        System.out.println("\n--- Performance Comparison ---");
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2;
        }

        target = 1500;

        long startTime = System.nanoTime();
        binarySearchIterative(largeArray, target);
        long binaryTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        linearSearch(largeArray, target);
        long linearTime = System.nanoTime() - startTime;

        System.out.println("Binary Search time: " + binaryTime + " ns");
        System.out.println("Linear Search time: " + linearTime + " ns");
        System.out.println("Binary search was " + (linearTime / binaryTime) + "x faster");
    }

    // Simple linear search for comparison
    private static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
