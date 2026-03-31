package com.pcgs.core.java.pocs.threads;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.*;
public class ForkNJoinPoC {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11};

        ForkJoinPool pool = new ForkJoinPool();

        int result = pool.invoke(new SumTask(arr, 0, arr.length));

        System.out.println(result);
    }
}


class SumTask extends RecursiveTask<Integer> {
    int[] arr;
    int start, end;

    SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {

        // Step 1: Base condition (small task)
        if (end - start <= 2) {
            int sum = 0;
            for (int i = start; i < end; i++)
                sum += arr[i];
            return sum;
        }

        // Step 2: Split task
        int mid = (start + end) / 2;

        SumTask left = new SumTask(arr, start, mid);
        SumTask right = new SumTask(arr, mid, end);

        // Step 3: Fork (run in parallel)
        left.fork();

        // Step 4: Compute right side
        int rightResult = right.compute();

        // Step 5: Join results
        int leftResult = left.join();

        return leftResult + rightResult;
    }
}