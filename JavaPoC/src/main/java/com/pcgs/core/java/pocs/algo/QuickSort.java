package com.pcgs.core.java.pocs.algo;

public class QuickSort {

    // Main sorting method
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);  // Sort left sub-array
            quickSort(arr, pivotIndex + 1, high); // Sort right sub-array
        }
    }

    // Partition method - places pivot in correct position
    private static int partition(int[] arr, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = arr[high];

        // Index of smaller element (indicates correct position of pivot)
        int i = low - 1;

        // Traverse through all elements
        // Compare each element with pivot
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element
                swap(arr, i, j);
            }
        }

        // Place pivot in its correct position
        swap(arr, i + 1, high);

        return i + 1; // Return the pivot index
    }

    // Helper method to swap two elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Utility method to print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main method to test
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original array:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
