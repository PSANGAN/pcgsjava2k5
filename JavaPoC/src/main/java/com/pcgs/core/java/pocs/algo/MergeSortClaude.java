package com.pcgs.core.java.pocs.algo;

public class MergeSortClaude {

    // Main merge sort method
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    // Recursive helper method
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point (avoids overflow)
            int mid = left + (right - left) / 2;

            // Sort first half
            mergeSortHelper(arr, left, mid);

            // Sort second half
            mergeSortHelper(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Method to merge two sorted subarrays
    private static void merge(int[] arr, int left, int mid, int right) {
        // Calculate sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays back into arr[left...right]
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr[] if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr[] if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Alternative implementation using Arrays.copyOfRange
    public static void mergeSortAlternative(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;

        // Divide into two halves
        int[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        int[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort both halves
        mergeSortAlternative(left);
        mergeSortAlternative(right);

        // Merge the sorted halves
        mergeAlternative(arr, left, right);
    }

    private static void mergeAlternative(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Detailed version with visualization
    public static void mergeSortDetailed(int[] arr, int depth) {
        if (arr.length < 2) {
            return;
        }

        String indent = "  ".repeat(depth);
        System.out.println(indent + "Dividing: " + arrayToString(arr));

        int mid = arr.length / 2;
        int[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        int[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        System.out.println(indent + "  Left:  " + arrayToString(left));
        System.out.println(indent + "  Right: " + arrayToString(right));

        mergeSortDetailed(left, depth + 1);
        mergeSortDetailed(right, depth + 1);

        mergeAlternative(arr, left, right);
        System.out.println(indent + "Merged:  " + arrayToString(arr));
    }

    // Utility method to print array
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Method to print array
    public static void printArray(int[] arr) {
        System.out.println(arrayToString(arr));
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("MERGE SORT DEMONSTRATION");
        System.out.println("========================================\n");

        // Example 1: Basic usage
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Example 1: Basic Merge Sort");
        System.out.println("Original array: " + arrayToString(arr1));
        mergeSort(arr1);
        System.out.println("Sorted array:   " + arrayToString(arr1));

        // Example 2: Detailed visualization
        System.out.println("\n========================================");
        System.out.println("Example 2: Step-by-Step Visualization");
        System.out.println("========================================\n");
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + arrayToString(arr2));
        System.out.println("\nDivide and Conquer Process:");
        mergeSortDetailed(arr2, 0);

        // Example 3: Already sorted array
        System.out.println("\n========================================");
        System.out.println("Example 3: Already Sorted Array");
        System.out.println("========================================\n");
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Original: " + arrayToString(arr3));
        mergeSort(arr3);
        System.out.println("Sorted:   " + arrayToString(arr3));

        // Example 4: Reverse sorted array
        System.out.println("\n========================================");
        System.out.println("Example 4: Reverse Sorted Array");
        System.out.println("========================================\n");
        int[] arr4 = {5, 4, 3, 2, 1};
        System.out.println("Original: " + arrayToString(arr4));
        mergeSort(arr4);
        System.out.println("Sorted:   " + arrayToString(arr4));

        // Example 5: Array with duplicates
        System.out.println("\n========================================");
        System.out.println("Example 5: Array with Duplicates");
        System.out.println("========================================\n");
        int[] arr5 = {5, 2, 8, 2, 9, 1, 5, 5};
        System.out.println("Original: " + arrayToString(arr5));
        mergeSort(arr5);
        System.out.println("Sorted:   " + arrayToString(arr5));

        // Performance test
        System.out.println("\n========================================");
        System.out.println("Performance Test");
        System.out.println("========================================\n");

        int[] sizes = {1000, 10000, 100000};
        for (int size : sizes) {
            int[] largeArray = new int[size];
            for (int i = 0; i < size; i++) {
                largeArray[i] = (int)(Math.random() * size);
            }

            long startTime = System.nanoTime();
            mergeSort(largeArray);
            long endTime = System.nanoTime();

            System.out.printf("Sorting %,d elements: %.2f ms%n",
                    size, (endTime - startTime) / 1_000_000.0);
        }
    }
}

// Generic Merge Sort for any Comparable type
class GenericMergeSort {

    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        T[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        T[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] left, T[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}