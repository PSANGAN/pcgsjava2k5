package com.pcgs.core.java.pocs.MapSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class RecursiveBinarySearch {

    public static int binarySearch(ArrayList<Integer> list, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: not found
        }

        int mid = left + (right - left) / 2;

        if (list.get(mid) == target) {
            return mid;
        } else if (list.get(mid) < target) {
            return binarySearch(list, target, mid + 1, right); // Search right
        } else {
            return binarySearch(list, target, left, mid - 1); // Search left
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 4, 7, 10, 15, 20, 28, 35));

        int result = binarySearch(numbers, 15, 0, numbers.size() - 1);
        System.out.println("Index of 15: " + result); // 4
    }
}