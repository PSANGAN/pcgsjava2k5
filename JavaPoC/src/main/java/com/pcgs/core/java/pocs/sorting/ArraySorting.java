package com.pcgs.core.java.pocs.sorting;

import java.util.Arrays;

public class ArraySorting {
    public static void main(String[] args) {

        int[] intArrays= new int[] {-9,-7,-5,0,20, 20, 10, 50, 49};

        Arrays.sort(intArrays);

        for(int i=0; i<intArrays.length; i++)
        {
            System.out.println(intArrays[i]);
        }

        System.out.println(System.lineSeparator());

        for(int i= intArrays.length-1; i >=0 ;i -- ){
            System.out.println(intArrays[i]);
        }
    }
}
