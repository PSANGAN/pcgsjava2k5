package com.pcgs.core.java.pocs.algo;

public class CountingSortClaude {

    // ========================================
    // Basic Counting Sort (for positive integers)
    // ========================================

    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find the maximum element to determine count array size
        int max = findMax(arr);

        // Create count array
        int[] count = new int[max + 1];

        // Count occurrences of each element
        for (int num : arr) {
            count[num]++;
        }

        // Reconstruct the sorted array
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (count[i] > 0) {
                arr[index++] = i;
                count[i]--;
            }
        }
    }

    // ========================================
    // Stable Counting Sort (preserves order)
    // ========================================

    public static void stableCountingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int max = findMax(arr);
        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        // Step 1: Count occurrences
        for (int num : arr) {
            count[num]++;
        }

        // Step 2: Calculate cumulative count (positions)
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build output array (traverse from right to maintain stability)
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copy output back to original array
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // ========================================
    // Counting Sort for Range (handles negative numbers)
    // ========================================

    public static void countingSortWithRange(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find min and max
        int min = arr[0];
        int max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Count occurrences (adjust for negative numbers)
        for (int num : arr) {
            count[num - min]++;
        }

        // Cumulative count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build output array
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // ========================================
    // Detailed Version with Visualization
    // ========================================

    public static void countingSortDetailed(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        System.out.println("Original array: " + arrayToString(arr));

        int max = findMax(arr);
        System.out.println("Maximum element: " + max);

        int[] count = new int[max + 1];
        int[] output = new int[arr.length];

        // Step 1: Count occurrences
        System.out.println("\nStep 1: Counting occurrences");
        for (int num : arr) {
            count[num]++;
        }

        System.out.print("Count array: [");
        for (int i = 0; i <= max; i++) {
            if (count[i] > 0) {
                System.out.print(i + "→" + count[i] + " ");
            }
        }
        System.out.println("]");

        // Step 2: Cumulative count
        System.out.println("\nStep 2: Cumulative count (determining positions)");
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        System.out.print("Cumulative: [");
        for (int i = 0; i <= max; i++) {
            if (i == 0 || count[i] != count[i-1]) {
                System.out.print(i + "→" + count[i] + " ");
            }
        }
        System.out.println("]");

        // Step 3: Build output
        System.out.println("\nStep 3: Placing elements in sorted order");
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = arr[i];
            int position = count[value] - 1;
            output[position] = value;
            count[value]--;
            System.out.println("  Place " + value + " at position " + position);
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
        System.out.println("\nSorted array: " + arrayToString(arr));
    }

    // ========================================
    // Counting Sort for Objects (with key extraction)
    // ========================================

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }

    public static void countingSortObjects(Person[] people) {
        if (people == null || people.length <= 1) {
            return;
        }

        // Find max age
        int maxAge = 0;
        for (Person p : people) {
            if (p.age > maxAge) maxAge = p.age;
        }

        int[] count = new int[maxAge + 1];
        Person[] output = new Person[people.length];

        // Count occurrences
        for (Person p : people) {
            count[p.age]++;
        }

        // Cumulative count
        for (int i = 1; i <= maxAge; i++) {
            count[i] += count[i - 1];
        }

        // Build output (stable)
        for (int i = people.length - 1; i >= 0; i--) {
            output[count[people[i].age] - 1] = people[i];
            count[people[i].age]--;
        }

        System.arraycopy(output, 0, people, 0, people.length);
    }

    // ========================================
    // Helper Methods
    // ========================================

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================
    // Performance Comparison
    // ========================================

    public static void performanceComparison() {
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("      PERFORMANCE COMPARISON");
        System.out.println("════════════════════════════════════════════════\n");

        // Test 1: Small range, large array
        System.out.println("Test 1: Large array (100,000 elements), Small range (0-100)");
        int[] arr1 = new int[100000];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int)(Math.random() * 100);
        }

        long start = System.nanoTime();
        countingSort(arr1.clone());
        long countingTime = System.nanoTime() - start;

        start = System.nanoTime();
        java.util.Arrays.sort(arr1.clone()); // Uses Quick Sort
        long quickSortTime = System.nanoTime() - start;

        System.out.printf("  Counting Sort: %.2f ms%n", countingTime / 1_000_000.0);
        System.out.printf("  Arrays.sort:   %.2f ms%n", quickSortTime / 1_000_000.0);
        System.out.printf("  Speedup:       %.2fx faster%n",
                (double)quickSortTime / countingTime);

        // Test 2: Large range
        System.out.println("\nTest 2: Large array (100,000 elements), Large range (0-100,000)");
        int[] arr2 = new int[100000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int)(Math.random() * 100000);
        }

        start = System.nanoTime();
        countingSort(arr2.clone());
        countingTime = System.nanoTime() - start;

        start = System.nanoTime();
        java.util.Arrays.sort(arr2.clone());
        quickSortTime = System.nanoTime() - start;

        System.out.printf("  Counting Sort: %.2f ms%n", countingTime / 1_000_000.0);
        System.out.printf("  Arrays.sort:   %.2f ms%n", quickSortTime / 1_000_000.0);

        if (countingTime < quickSortTime) {
            System.out.printf("  Speedup:       %.2fx faster%n",
                    (double)quickSortTime / countingTime);
        } else {
            System.out.printf("  Slowdown:      %.2fx slower%n",
                    (double)countingTime / quickSortTime);
        }
    }

    // ========================================
    // Main Method with Examples
    // ========================================

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════════════");
        System.out.println("      COUNTING SORT DEMONSTRATION");
        System.out.println("════════════════════════════════════════════════\n");

        // Example 1: Basic Counting Sort
        System.out.println("Example 1: Basic Counting Sort");
        System.out.println("────────────────────────────────────────────────");
        int[] arr1 = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Original: " + arrayToString(arr1));
        countingSort(arr1);
        System.out.println("Sorted:   " + arrayToString(arr1));

        // Example 2: Detailed Step-by-Step
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("Example 2: Step-by-Step Visualization");
        System.out.println("════════════════════════════════════════════════");
        int[] arr2 = {6, 3, 9, 3, 1, 5};
        countingSortDetailed(arr2);

        // Example 3: Stable Counting Sort
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("Example 3: Stable Counting Sort");
        System.out.println("════════════════════════════════════════════════");
        int[] arr3 = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Original: " + arrayToString(arr3));
        stableCountingSort(arr3);
        System.out.println("Sorted:   " + arrayToString(arr3));

        // Example 4: With Negative Numbers
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("Example 4: Array with Negative Numbers");
        System.out.println("════════════════════════════════════════════════");
        int[] arr4 = {-5, -10, 0, -3, 8, 5, -1, 10};
        System.out.println("Original: " + arrayToString(arr4));
        countingSortWithRange(arr4);
        System.out.println("Sorted:   " + arrayToString(arr4));

        // Example 5: Sorting Objects
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("Example 5: Sorting Objects by Age");
        System.out.println("════════════════════════════════════════════════");
        Person[] people = {
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 25),
                new Person("Diana", 22),
                new Person("Eve", 30)
        };

        System.out.print("Original: ");
        for (Person p : people) System.out.print(p + " ");
        System.out.println();

        countingSortObjects(people);

        System.out.print("Sorted:   ");
        for (Person p : people) System.out.print(p + " ");
        System.out.println();
        System.out.println("(Notice: People with same age maintain their original order - STABLE)");

        // Example 6: Large Array with Small Range
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("Example 6: Large Array with Small Range");
        System.out.println("════════════════════════════════════════════════");
        int[] arr5 = new int[1000];
        for (int i = 0; i < arr5.length; i++) {
            arr5[i] = (int)(Math.random() * 10); // Only 0-9
        }
        System.out.println("Array size: 1,000 elements");
        System.out.println("Range: 0-9");

        long start = System.nanoTime();
        countingSort(arr5);
        long end = System.nanoTime();
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
        System.out.println("First 20 elements: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(arr5[i] + " ");
        }
        System.out.println("...");

        // Performance Comparison
        performanceComparison();

        // Summary
        printSummary();
    }

    private static void printSummary() {
        System.out.println("\n════════════════════════════════════════════════");
        System.out.println("      KEY TAKEAWAYS");
        System.out.println("════════════════════════════════════════════════\n");

        System.out.println("WHEN TO USE COUNTING SORT:");
        System.out.println("  ✓ Range of values (k) is small compared to n");
        System.out.println("  ✓ Integers or objects with integer keys");
        System.out.println("  ✓ Need stable sorting");
        System.out.println("  ✓ Need linear time O(n + k)");
        System.out.println();

        System.out.println("WHEN NOT TO USE:");
        System.out.println("  ✗ Large range (k >> n) - wastes memory");
        System.out.println("  ✗ Floating-point numbers");
        System.out.println("  ✗ Strings (use Radix Sort instead)");
        System.out.println("  ✗ Memory is limited");
        System.out.println();

        System.out.println("COMPLEXITY:");
        System.out.println("  Time:  O(n + k) where k is range");
        System.out.println("  Space: O(n + k)");
        System.out.println();

        System.out.println("ADVANTAGES:");
        System.out.println("  • Linear time complexity");
        System.out.println("  • Stable sorting algorithm");
        System.out.println("  • Simple to implement");
        System.out.println("  • Excellent for small integer ranges");
        System.out.println();

        System.out.println("DISADVANTAGES:");
        System.out.println("  • Only works with integers (or integer keys)");
        System.out.println("  • Space inefficient for large ranges");
        System.out.println("  • Not suitable for general-purpose sorting");
    }
}
