package com.pcgs.core.java.pocs.MapSearch;

import java.util.*;

public class BinarySearchExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(2, 5, 8, 12, 16, 23, 38, 45, 56, 67, 78));

        // List MUST be sorted for binary search
        int index = Collections.binarySearch(list, 23);
        System.out.println("Found 23 at index: " + index); // 5

        // Searching for non-existent element
        int notFound = Collections.binarySearch(list, 25);
        System.out.println("Search for 25: " + notFound); // Negative value: -(insertion point) - 1

        // With custom comparator for objects
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
        int idx = Collections.binarySearch(names, "Charlie");
        System.out.println("Found Charlie at: " + idx); // 2
    }
}
