package com.pcgs.core.java.pocs.MapSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class ManualBinarySearch {

    public static int binarySearch(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoids integer overflow

            if (list.get(mid) == target) {
                return mid; // Found
            } else if (list.get(mid) < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }

        return -1; // Not found
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(3, 7, 12, 18, 24, 31, 45, 59));

        System.out.println("Index of 18: " + binarySearch(numbers, 18)); // 3
        System.out.println("Index of 50: " + binarySearch(numbers, 50)); // -1
    }
}
