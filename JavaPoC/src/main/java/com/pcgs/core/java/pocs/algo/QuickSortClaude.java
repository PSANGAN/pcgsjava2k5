package com.pcgs.core.java.pocs.algo;

public class QuickSortClaude {

    // ========================================
    // Method 1: Last Element as Pivot (Lomuto Partition)
    // ========================================

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSortHelper(arr, low, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, high);
        }
    }

    // Lomuto partition scheme - pivot is last element
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choose last element as pivot
        int i = low - 1;        // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap arr[i+1] and arr[high] (put pivot in correct position)
        swap(arr, i + 1, high);

        return i + 1;  // Return pivot index
    }

    // ========================================
    // Method 2: Middle Element as Pivot (Hoare Partition)
    // ========================================

    public static void quickSortHoare(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortHoareHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHoareHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionHoare(arr, low, high);
            quickSortHoareHelper(arr, low, pivotIndex);
            quickSortHoareHelper(arr, pivotIndex + 1, high);
        }
    }

    // Hoare partition scheme - more efficient
    private static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2];  // Middle element as pivot
        int i = low - 1;
        int j = high + 1;

        while (true) {
            // Find element on left that should be on right
            do {
                i++;
            } while (arr[i] < pivot);

            // Find element on right that should be on left
            do {
                j--;
            } while (arr[j] > pivot);

            // If pointers crossed, partition is complete
            if (i >= j) {
                return j;
            }

            // Swap elements
            swap(arr, i, j);
        }
    }

    // ========================================
    // Method 3: Random Pivot (Randomized Quick Sort)
    // ========================================

    public static void quickSortRandom(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSortRandomHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortRandomHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionRandom(arr, low, high);
            quickSortRandomHelper(arr, low, pivotIndex - 1);
            quickSortRandomHelper(arr, pivotIndex + 1, high);
        }
    }

    private static int partitionRandom(int[] arr, int low, int high) {
        // Choose random pivot and swap with last element
        int randomIndex = low + (int)(Math.random() * (high - low + 1));
        swap(arr, randomIndex, high);

        // Use standard partition with last element as pivot
        return partition(arr, low, high);
    }

    // ========================================
    // Detailed Version with Visualization
    // ========================================

    public static void quickSortDetailed(int[] arr, int low, int high, int depth) {
        if (low < high) {
            String indent = "  ".repeat(depth);
            System.out.println(indent + "Sorting: " + arrayToString(arr, low, high));

            int pivotIndex = partitionDetailed(arr, low, high, depth);

            System.out.println(indent + "After partition (pivot=" + arr[pivotIndex] +
                    " at index " + pivotIndex + "): " +
                    arrayToString(arr, low, high));

            // Sort left partition
            if (low < pivotIndex - 1) {
                System.out.println(indent + "Sorting left partition:");
                quickSortDetailed(arr, low, pivotIndex - 1, depth + 1);
            }

            // Sort right partition
            if (pivotIndex + 1 < high) {
                System.out.println(indent + "Sorting right partition:");
                quickSortDetailed(arr, pivotIndex + 1, high, depth + 1);
            }
        }
    }

    private static int partitionDetailed(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        int pivot = arr[high];
        System.out.println(indent + "  Pivot: " + pivot + " (at index " + high + ")");

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                if (i != j) {
                    System.out.println(indent + "  Swapping " + arr[i] +
                            " and " + arr[j]);
                    swap(arr, i, j);
                }
            }
        }

        swap(arr, i + 1, high);
        System.out.println(indent + "  Placing pivot at index " + (i + 1));

        return i + 1;
    }

    // ========================================
    // Utility Methods
    // ========================================

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static String arrayToString(int[] arr) {
        return arrayToString(arr, 0, arr.length - 1);
    }

    private static String arrayToString(int[] arr, int low, int high) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < low || i > high) {
                sb.append("_");
            } else {
                sb.append(arr[i]);
            }
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================
    // Main Method with Examples
    // ========================================

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("QUICK SORT DEMONSTRATION");
        System.out.println("========================================\n");

        // Example 1: Basic Quick Sort
        System.out.println("Example 1: Basic Quick Sort (Last Element as Pivot)");
        System.out.println("--------------------------------------------------");
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + arrayToString(arr1));
        quickSort(arr1);
        System.out.println("Sorted:   " + arrayToString(arr1));

        // Example 2: Detailed Step-by-Step
        System.out.println("\n========================================");
        System.out.println("Example 2: Step-by-Step Visualization");
        System.out.println("========================================\n");
        int[] arr2 = {10, 7, 8, 9, 1, 5};
        System.out.println("Original: " + arrayToString(arr2));
        System.out.println("\nPartitioning Process:");
        quickSortDetailed(arr2, 0, arr2.length - 1, 0);
        System.out.println("\nFinal sorted: " + arrayToString(arr2));

        // Example 3: Hoare Partition
        System.out.println("\n========================================");
        System.out.println("Example 3: Hoare Partition Scheme");
        System.out.println("========================================\n");
        int[] arr3 = {3, 7, 8, 5, 2, 1, 9, 5, 4};
        System.out.println("Original: " + arrayToString(arr3));
        quickSortHoare(arr3);
        System.out.println("Sorted:   " + arrayToString(arr3));

        // Example 4: Already Sorted Array
        System.out.println("\n========================================");
        System.out.println("Example 4: Already Sorted (Worst Case)");
        System.out.println("========================================\n");
        int[] arr4 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original: " + arrayToString(arr4));
        quickSort(arr4);
        System.out.println("Sorted:   " + arrayToString(arr4));

        // Example 5: Random Pivot
        System.out.println("\n========================================");
        System.out.println("Example 5: Randomized Quick Sort");
        System.out.println("========================================\n");
        int[] arr5 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original: " + arrayToString(arr5));
        quickSortRandom(arr5);
        System.out.println("Sorted:   " + arrayToString(arr5));

        // Example 6: Array with Duplicates
        System.out.println("\n========================================");
        System.out.println("Example 6: Array with Duplicates");
        System.out.println("========================================\n");
        int[] arr6 = {5, 2, 8, 2, 9, 1, 5, 5};
        System.out.println("Original: " + arrayToString(arr6));
        quickSort(arr6);
        System.out.println("Sorted:   " + arrayToString(arr6));

        // Performance Comparison
        System.out.println("\n========================================");
        System.out.println("Performance Comparison");
        System.out.println("========================================\n");

        int[] sizes = {1000, 10000, 100000};
        for (int size : sizes) {
            // Random array
            int[] randomArr = new int[size];
            for (int i = 0; i < size; i++) {
                randomArr[i] = (int)(Math.random() * size);
            }

            long startTime = System.nanoTime();
            quickSort(randomArr.clone());
            long endTime = System.nanoTime();
            double lomutoTime = (endTime - startTime) / 1_000_000.0;

            startTime = System.nanoTime();
            quickSortHoare(randomArr.clone());
            endTime = System.nanoTime();
            double hoareTime = (endTime - startTime) / 1_000_000.0;

            startTime = System.nanoTime();
            quickSortRandom(randomArr.clone());
            endTime = System.nanoTime();
            double randomTime = (endTime - startTime) / 1_000_000.0;

            System.out.printf("Array size: %,d elements%n", size);
            System.out.printf("  Lomuto partition:    %.2f ms%n", lomutoTime);
            System.out.printf("  Hoare partition:     %.2f ms%n", hoareTime);
            System.out.printf("  Random pivot:        %.2f ms%n", randomTime);
            System.out.println();
        }
    }
}

// ========================================
// Three-Way Quick Sort (for many duplicates)
// ========================================
class ThreeWayQuickSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = arr[low];
        int lt = low;      // arr[low..lt-1] < pivot
        int gt = high;     // arr[gt+1..high] > pivot
        int i = low + 1;   // arr[lt..i-1] == pivot

        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }

        // arr[low..lt-1] < pivot = arr[lt..gt] < arr[gt+1..high]
        sort(arr, low, lt - 1);
        sort(arr, gt + 1, high);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
