package com.pcgs.core.java.pocs.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PermitiveStreams {
    public static void main(String[] args){

//        int[] intArray = {1,2,3,4,5,6,7,8,9,10};
//        Arrays.stream(intArray).filter(i -> i > 5).map(i -> i * 2).forEach(System.out::println);
        // Arrays.stream(intArray).forEach(System.out::println);
        // // Arrays.stream(intArray).iterate(1, n -> n + 1).limit(10).forEach(System.out::println);

        // System.out.println("\n");
        // IntStream.range(1, 11).forEach(System.out::println);
        // System.out.println("\n");
        // IntStream.of(intArray).forEach(System.out::println);
        // System.out.println("\n");
        // IntStream.builder().add(10).add(20).add(30).build().forEach(System.out::println);

        // System.out.println("\n");
        // IntStream.iterate(1, n -> n + 1).limit(10).forEach(System.out::println);


        // String[] stringArray = new String[] {"Apple", "banana", "cat", "dog", "egg"};
        // // Print each string in the array
        // Arrays.stream(stringArray).forEach(System.out::println);
        // System.out.println(System.lineSeparator());
        //
        // // Print each string in reverse order
        // Arrays.stream(stringArray).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        // System.out.println(System.lineSeparator());
        //
        // // Print each string with length greater than 3
        // Arrays.stream(stringArray).filter(input -> input.length() >3).forEach(System.out::println);

        Integer[] intArray = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        List<Integer> integerList = Arrays.asList(intArray);
        // intArray.stream().filter(i -> i > 5).map(i -> i * 2).forEach(System.out::println);
        integerList.stream().filter(i -> i > 5).map(i -> i * 2).forEach(System.out::println);


    }
}
