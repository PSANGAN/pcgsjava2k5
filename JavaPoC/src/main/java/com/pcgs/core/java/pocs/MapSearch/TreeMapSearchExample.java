package com.pcgs.core.java.pocs.MapSearch;

import java.util.*;

public class TreeMapSearchExample {
    public static void main(String[] args) {
        // TreeSet maintains sorted order automatically
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList(15, 10, 20, 8, 12, 25, 6));

        // Search operations (internally uses tree search, not binary search on array)
        System.out.println("Contains 12? " + treeSet.contains(12)); // O(log n)

        // Range queries (leveraging BST properties)
        System.out.println("Values >= 12: " + treeSet.tailSet(12)); // [12, 15, 20, 25]
        System.out.println("Values < 15: " + treeSet.headSet(15));  // [6, 8, 10, 12]

        // TreeMap example with key-value pairs
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "five");
        treeMap.put(2, "two");
        treeMap.put(8, "eight");
        treeMap.put(1, "one");

        // Automatically sorted by keys
        System.out.println("Sorted entries: " + treeMap); // {1=one, 2=two, 5=five, 8=eight}

        // Efficient lookups
        System.out.println("Get key 5: " + treeMap.get(5)); // "five"

        // Floor and ceiling operations
        System.out.println("Floor of 6: " + treeMap.floorKey(6));     // 5
        System.out.println("Ceiling of 6: " + treeMap.ceilingKey(6)); // 8
    }
}