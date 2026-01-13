package com.pcgs.core.java.pocs.algo;

public class FibonacciComparison {

    // ============================================
    // RECURSIVE APPROACH (Simple but inefficient)
    // ============================================
    public static long fibonacciRecursive(int n) {
        // Base cases
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // Recursive case: F(n) = F(n-1) + F(n-2)
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // ============================================
    // ITERATIVE APPROACH (Efficient)
    // ============================================
    public static long fibonacciIterative(int n) {
        // Handle base cases
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Initialize first two Fibonacci numbers
        long prev2 = 0;  // F(0)
        long prev1 = 1;  // F(1)
        long current = 0;

        // Calculate Fibonacci iteratively
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }

    // ============================================
    // OPTIMIZED RECURSIVE (with Memoization)
    // ============================================
    public static long fibonacciMemoization(int n, long[] memo) {
        // Check if already calculated
        if (memo[n] != -1) {
            return memo[n];
        }

        // Base cases
        if (n == 0) {
            memo[n] = 0;
            return 0;
        }
        if (n == 1) {
            memo[n] = 1;
            return 1;
        }

        // Calculate and store in memo
        memo[n] = fibonacciMemoization(n - 1, memo) +
                fibonacciMemoization(n - 2, memo);
        return memo[n];
    }

    // Helper method for memoization
    public static long fibonacciWithMemo(int n) {
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fibonacciMemoization(n, memo);
    }

    // ============================================
    // MAIN METHOD - TESTING AND COMPARISON
    // ============================================
    public static void main(String[] args) {
        int[] testCases = {0, 1, 5, 10, 15, 20};

        System.out.println("FIBONACCI SEQUENCE COMPARISON");
        System.out.println("==============================\n");

        // Display Fibonacci values using both methods
        System.out.println("Fibonacci Values:");
        System.out.println("n\tRecursive\tIterative");
        System.out.println("--\t---------\t---------");

        for (int n : testCases) {
            long recursive = fibonacciRecursive(n);
            long iterative = fibonacciIterative(n);
            System.out.println(n + "\t" + recursive + "\t\t" + iterative);
        }

        // Performance comparison
        System.out.println("\n\nPERFORMANCE COMPARISON (for n=35):");
        System.out.println("====================================");

        int n = 35;

        // Time recursive approach
        long startTime = System.nanoTime();
        long resultRecursive = fibonacciRecursive(n);
        long endTime = System.nanoTime();
        long recursiveTime = (endTime - startTime) / 1_000_000; // Convert to ms

        System.out.println("Recursive: " + resultRecursive);
        System.out.println("Time: " + recursiveTime + " ms");

        // Time iterative approach
        startTime = System.nanoTime();
        long resultIterative = fibonacciIterative(n);
        endTime = System.nanoTime();
        long iterativeTime = (endTime - startTime) / 1_000_000;

        System.out.println("\nIterative: " + resultIterative);
        System.out.println("Time: " + iterativeTime + " ms");

        // Time memoization approach
        startTime = System.nanoTime();
        long resultMemo = fibonacciWithMemo(n);
        endTime = System.nanoTime();
        long memoTime = (endTime - startTime) / 1_000_000;

        System.out.println("\nMemoization: " + resultMemo);
        System.out.println("Time: " + memoTime + " ms");

        System.out.println("\n\nSPEED IMPROVEMENT:");
        System.out.println("Iterative is " + (recursiveTime / Math.max(iterativeTime, 1)) + "x faster than Recursive");
        System.out.println("Memoization is " + (recursiveTime / Math.max(memoTime, 1)) + "x faster than Recursive");

        // Visual representation
        System.out.println("\n\nRECURSIVE CALL TREE for F(5):");
        System.out.println("                F(5)");
        System.out.println("              /      \\");
        System.out.println("           F(4)      F(3)");
        System.out.println("          /   \\      /   \\");
        System.out.println("       F(3)  F(2)  F(2)  F(1)");
        System.out.println("       / \\    / \\   / \\");
        System.out.println("     F(2) F(1) F(1) F(0) F(1) F(0)");
        System.out.println("     / \\");
        System.out.println("   F(1) F(0)");
        System.out.println("\nNotice: Many redundant calculations!");
    }
}
