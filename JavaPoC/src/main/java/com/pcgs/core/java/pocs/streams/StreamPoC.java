package com.pcgs.core.java.pocs.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPoC {
    public static void main(String[] args) {
//        Stream<String> stringStream = Stream.of("apple", "banana", "cherry");
//        stringStream.filter(s -> s.startsWith("a")).forEach(System.out::println);
//
//        Stream<Integer> nullableStream = Stream.ofNullable(null);
//        nullableStream.forEach(System.out::println);
//
//        Stream<Double> nullDoubleStream = Stream.ofNullable(12d);
//        nullDoubleStream.forEach(System.out::println);
//
//        IntStream integerStream = IntStream.range(1, 10);
//        integerStream.forEach(System.out::println);
//
//        Stream<String> stringStream2 = Stream.<String>builder()
//                .add("apple").add("banana").add("cherry").build();
//        stringStream2.forEach(System.out::println);
//
//        Stream.Builder<String> stringStream3 = Stream.builder();
//        Stream<String> temp = stringStream3.add("apple").add("banana").add("cherry").build();
//
//        temp.forEach(System.out::println);
//
//        // Creates an empty stream of strings
//        Stream<String> stream = Stream.empty();
//
//        // Creates an empty stream of integers
//        IntStream numbers = IntStream.empty();

         Stream<Integer>  intStream =   Stream.of(1, 2, 3);
         Stream<Stream<Integer>> temp = intStream.map( n ->Stream.of(n, n * 2));
         temp.forEach(System.out::println);

    }
}
