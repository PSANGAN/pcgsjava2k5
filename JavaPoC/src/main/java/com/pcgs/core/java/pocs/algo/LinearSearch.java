package com.pcgs.core.java.pocs.algo;

public class LinearSearch {

    /**
     * Performs linear search on an integer array
     * @param arr The array to search in
     * @param target The element to search for
     * @return Index of target element if found, -1 otherwise
     */
    public static int linearSearch(int[] arr, int target) {
        // Iterate through each element in the array
        for (int i = 0; i < arr.length; i++) {
            // Check if current element matches target
            if (arr[i] == target) {
                return i; // Return index where element is found
            }
        }
        // Element not found in array
        return -1;
    }

    /**
     * Generic linear search that works with any object type
     */
    public static <T> int linearSearch(T[] arr, T target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // Example 1: Search in integer array
        int[] numbers = {12, 45, 23, 67, 89, 34, 56};
        int targetNumber = 67;

        int result = linearSearch(numbers, targetNumber);

        if (result != -1) {
            System.out.println("Element " + targetNumber + " found at index: " + result);
        } else {
            System.out.println("Element " + targetNumber + " not found in array");
        }

        // Example 2: Search for element not in array
        int missingNumber = 100;
        result = linearSearch(numbers, missingNumber);

        if (result != -1) {
            System.out.println("Element " + missingNumber + " found at index: " + result);
        } else {
            System.out.println("Element " + missingNumber + " not found in array");
        }

        // Example 3: Generic search with Strings
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        String targetName = "Charlie";

        result = linearSearch(names, targetName);

        if (result != -1) {
            System.out.println("Name " + targetName + " found at index: " + result);
        } else {
            System.out.println("Name " + targetName + " not found in array");
        }
    }
}