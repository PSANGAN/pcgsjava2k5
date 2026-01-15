package com.pcgs.core.java.pocs.algo;

public class RadixSortClaude {

    // ========================================
    // MAIN RADIX SORT (LSD - Least Significant Digit)
    // ========================================

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find the maximum number to determine number of digits
        int max = getMax(arr);

        // Do counting sort for every digit
        // exp is 10^i where i is current digit position (1, 10, 100, ...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    // Counting sort based on digit represented by exp
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // Digits 0-9

        // Store count of occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] to contain actual position of this digit in output
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (go from right to left for stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output array back to arr
        System.arraycopy(output, 0, arr, 0, n);
    }

    // ========================================
    // DETAILED RADIX SORT WITH VISUALIZATION
    // ========================================

    public static void radixSortDetailed(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        System.out.println("Original array: " + arrayToString(arr));

        int max = getMax(arr);
        System.out.println("Maximum value: " + max);

        int numDigits = String.valueOf(max).length();
        System.out.println("Number of digits: " + numDigits);
        System.out.println();

        int pass = 1;
        for (int exp = 1; max / exp > 0; exp *= 10) {
            String digitPlace = getDigitPlaceName(exp);
            System.out.println("═══════════════════════════════════════════════════");
            System.out.println("PASS " + pass + ": Sorting by " + digitPlace.toUpperCase());
            System.out.println("═══════════════════════════════════════════════════");

            // Show which digit we're looking at
            System.out.println("\nExtracting " + digitPlace + " from each number:");
            for (int num : arr) {
                int digit = (num / exp) % 10;
                System.out.printf("  %d → digit = %d%n", num, digit);
            }

            countingSortByDigitDetailed(arr, exp, digitPlace);

            System.out.println("\nAfter Pass " + pass + ": " + arrayToString(arr));
            System.out.println();

            pass++;
        }

        System.out.println("✓ Final sorted array: " + arrayToString(arr));
    }

    private static void countingSortByDigitDetailed(int[] arr, int exp, String digitPlace) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        System.out.println("\nFrequency of each digit:");
        System.out.print("Digit:     ");
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%d] ", i);
        }
        System.out.println();
        System.out.print("Count:     ");
        for (int i = 0; i < 10; i++) {
            System.out.printf(" %d  ", count[i]);
        }
        System.out.println();

        // Cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        System.out.print("Cumulative: ");
        for (int i = 0; i < 10; i++) {
            System.out.printf(" %d  ", count[i]);
        }
        System.out.println();

        // Build output
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // ========================================
    // RADIX SORT FOR STRINGS
    // ========================================

    public static void radixSortStrings(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find maximum length
        int maxLen = 0;
        for (String s : arr) {
            maxLen = Math.max(maxLen, s.length());
        }

        // Sort from rightmost character to leftmost
        for (int pos = maxLen - 1; pos >= 0; pos--) {
            countingSortByChar(arr, pos);
        }
    }

    private static void countingSortByChar(String[] arr, int pos) {
        int n = arr.length;
        String[] output = new String[n];
        int[] count = new int[256]; // ASCII characters

        // Count occurrences
        for (String s : arr) {
            char ch = pos < s.length() ? s.charAt(pos) : 0;
            count[ch]++;
        }

        // Cumulative count
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Build output
        for (int i = n - 1; i >= 0; i--) {
            char ch = pos < arr[i].length() ? arr[i].charAt(pos) : 0;
            output[count[ch] - 1] = arr[i];
            count[ch]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    // ========================================
    // RADIX SORT WITH NEGATIVE NUMBERS
    // ========================================

    public static void radixSortWithNegatives(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Separate positive and negative numbers
        int[] negative = new int[arr.length];
        int[] positive = new int[arr.length];
        int negCount = 0, posCount = 0;

        for (int num : arr) {
            if (num < 0) {
                negative[negCount++] = -num; // Store absolute value
            } else {
                positive[posCount++] = num;
            }
        }

        // Sort both arrays
        if (negCount > 0) {
            int[] negArray = java.util.Arrays.copyOf(negative, negCount);
            radixSort(negArray);
            // Reverse and negate
            for (int i = 0; i < negCount; i++) {
                arr[i] = -negArray[negCount - 1 - i];
            }
        }

        if (posCount > 0) {
            int[] posArray = java.util.Arrays.copyOf(positive, posCount);
            radixSort(posArray);
            System.arraycopy(posArray, 0, arr, negCount, posCount);
        }
    }

    // ========================================
    // HELPER METHODS
    // ========================================

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    private static String getDigitPlaceName(int exp) {
        if (exp == 1) return "ones place";
        if (exp == 10) return "tens place";
        if (exp == 100) return "hundreds place";
        if (exp == 1000) return "thousands place";
        return exp + "'s place";
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

    private static String arrayToString(String[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // ========================================
    // PERFORMANCE COMPARISON
    // ========================================

    public static void performanceComparison() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("      PERFORMANCE COMPARISON");
        System.out.println("═══════════════════════════════════════════════════\n");

        int[] sizes = {1000, 10000, 100000};

        for (int size : sizes) {
            System.out.println("Array size: " + size + " elements");
            System.out.println("───────────────────────────────────────────────────");

            // Small range (0-999)
            int[] arr1 = new int[size];
            for (int i = 0; i < size; i++) {
                arr1[i] = (int)(Math.random() * 1000);
            }

            long start = System.nanoTime();
            radixSort(arr1.clone());
            long radixTime = System.nanoTime() - start;

            start = System.nanoTime();
            java.util.Arrays.sort(arr1.clone());
            long arraysTime = System.nanoTime() - start;

            System.out.printf("Range 0-999:%n");
            System.out.printf("  Radix Sort:  %8.2f ms%n", radixTime / 1_000_000.0);
            System.out.printf("  Arrays.sort: %8.2f ms%n", arraysTime / 1_000_000.0);

            if (radixTime < arraysTime) {
                System.out.printf("  → Radix is %.2fx faster%n",
                        (double)arraysTime / radixTime);
            } else {
                System.out.printf("  → Arrays.sort is %.2fx faster%n",
                        (double)radixTime / arraysTime);
            }

            System.out.println();
        }
    }

    // ========================================
    // MAIN METHOD WITH EXAMPLES
    // ========================================

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("      RADIX SORT DEMONSTRATION");
        System.out.println("═══════════════════════════════════════════════════\n");

        // Example 1: Basic Radix Sort
        System.out.println("Example 1: Basic Radix Sort");
        System.out.println("───────────────────────────────────────────────────");
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Original: " + arrayToString(arr1));
        radixSort(arr1);
        System.out.println("Sorted:   " + arrayToString(arr1));

        // Example 2: Detailed Step-by-Step
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("Example 2: Step-by-Step Visualization");
        System.out.println("═══════════════════════════════════════════════════\n");
        int[] arr2 = {329, 457, 657, 839, 436, 720, 355};
        radixSortDetailed(arr2);

        // Example 3: Same-length numbers
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("Example 3: Three-digit Numbers");
        System.out.println("═══════════════════════════════════════════════════");
        int[] arr3 = {543, 986, 217, 765, 329};
        System.out.println("Original: " + arrayToString(arr3));
        radixSort(arr3);
        System.out.println("Sorted:   " + arrayToString(arr3));

        // Example 4: Sorting Strings
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("Example 4: Sorting Strings");
        System.out.println("═══════════════════════════════════════════════════");
        String[] words = {"dog", "cat", "ant", "bat", "egg", "fox"};
        System.out.println("Original: " + arrayToString(words));
        radixSortStrings(words);
        System.out.println("Sorted:   " + arrayToString(words));

        // Example 5: With Negative Numbers
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("Example 5: Array with Negative Numbers");
        System.out.println("═══════════════════════════════════════════════════");
        int[] arr4 = {-170, 45, -75, 90, -24, 2, 66};
        System.out.println("Original: " + arrayToString(arr4));
        radixSortWithNegatives(arr4);
        System.out.println("Sorted:   " + arrayToString(arr4));

        // Example 6: Large numbers
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("Example 6: Large Numbers");
        System.out.println("═══════════════════════════════════════════════════");
        int[] arr5 = {123456, 789, 45678, 9012, 345, 67890};
        System.out.println("Original: " + arrayToString(arr5));
        radixSort(arr5);
        System.out.println("Sorted:   " + arrayToString(arr5));

        // Performance Comparison
        performanceComparison();

        // Summary
        printSummary();
    }

    private static void printSummary() {
        System.out.println("\n═══════════════════════════════════════════════════");
        System.out.println("      KEY TAKEAWAYS");
        System.out.println("═══════════════════════════════════════════════════\n");

        System.out.println("HOW RADIX SORT WORKS:");
        System.out.println("  • Sorts digit by digit (rightmost to leftmost)");
        System.out.println("  • Uses Counting Sort for each digit");
        System.out.println("  • Non-comparison based algorithm");
        System.out.println("  • Stable sorting (maintains order of equal elements)");
        System.out.println();

        System.out.println("COMPLEXITY:");
        System.out.println("  Time:  O(d × (n + k))");
        System.out.println("         where d = number of digits");
        System.out.println("               n = number of elements");
        System.out.println("               k = range of digit values (0-9 = 10)");
        System.out.println("  Space: O(n + k)");
        System.out.println();

        System.out.println("WHEN TO USE:");
        System.out.println("  ✓ Sorting integers with fixed number of digits");
        System.out.println("  ✓ Sorting strings of similar length");
        System.out.println("  ✓ When range of digits is small (like 0-9)");
        System.out.println("  ✓ Large datasets with small digit count");
        System.out.println();

        System.out.println("WHEN NOT TO USE:");
        System.out.println("  ✗ Floating-point numbers");
        System.out.println("  ✗ Numbers with vastly different digit counts");
        System.out.println("  ✗ When comparison-based sort is more intuitive");
        System.out.println();

        System.out.println("ADVANTAGES:");
        System.out.println("  • Linear time for fixed-length integers: O(n)");
        System.out.println("  • Stable sorting algorithm");
        System.out.println("  • Faster than O(n log n) comparison sorts");
        System.out.println("  • Predictable performance");
        System.out.println();

        System.out.println("DISADVANTAGES:");
        System.out.println("  • Only works with integers or fixed-length data");
        System.out.println("  • Requires extra space");
        System.out.println("  • Performance depends on number of digits");
        System.out.println("  • Not cache-friendly for large digit counts");
    }
}