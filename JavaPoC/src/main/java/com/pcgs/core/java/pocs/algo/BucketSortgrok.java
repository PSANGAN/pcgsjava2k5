package com.pcgs.core.java.pocs.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSortgrok {
    public static void bucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Step 1: Create n empty buckets
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Step 2: Distribute elements into buckets
        for (float num : arr) {
            int bucketIndex = (int) (n * num);  // For range [0,1)
            buckets[bucketIndex].add(num);
        }

        // Step 3: Sort each bucket individually
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Step 4: Concatenate sorted buckets back into arr
        int index = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] arr = {0.42f, 0.32f, 0.33f, 0.52f, 0.37f, 0.47f, 0.51f};
        System.out.println("Original array: " + Arrays.toString(arr));

        bucketSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
