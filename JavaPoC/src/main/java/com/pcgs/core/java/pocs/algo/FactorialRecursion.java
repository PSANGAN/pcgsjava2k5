package com.pcgs.core.java.pocs.algo;

public class FactorialRecursion {

    // Recursive method to calculate factorial
    public static int factorial(int n) {
        // Base case: factorial of 0 or 1 is 1
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursive case: n! = n * (n-1)!
        return n * factorial(n - 1);
    }

    // Main method to test the factorial function
    public static void main(String[] args) {
        // Test cases
        int[] testNumbers = {0, 1, 2, 5, 7, 10};

        System.out.println("Factorial Calculations:");
        System.out.println("----------------------");

        for (int num : testNumbers) {
            int result = factorial(num);
            System.out.println(num + "! = " + result);
        }

        // Example showing the recursive process
        System.out.println("\nRecursive Process for 5!:");
        System.out.println("5! = 5 × 4!");
        System.out.println("   = 5 × (4 × 3!)");
        System.out.println("   = 5 × (4 × (3 × 2!))");
        System.out.println("   = 5 × (4 × (3 × (2 × 1!)))");
        System.out.println("   = 5 × (4 × (3 × (2 × 1)))");
        System.out.println("   = 5 × (4 × (3 × 2))");
        System.out.println("   = 5 × (4 × 6)");
        System.out.println("   = 5 × 24");
        System.out.println("   = 120");
    }
}
