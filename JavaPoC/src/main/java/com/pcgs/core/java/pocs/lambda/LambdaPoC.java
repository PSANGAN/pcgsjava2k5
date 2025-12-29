package com.pcgs.core.java.pocs.lambda;

import java.util.function.Function;

public class LambdaPoC {
    public static void main(String[] args) {
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> increment = x -> x + 1;

        // compose: increment first, then square
        Function<Integer, Integer> composed = square.compose(increment);
        System.out.println(composed.apply(4)); // (4 + 1)^2 = 25

        // andThen: square first, then increment
        Function<Integer, Integer> chained = square.andThen(increment);
        System.out.println(chained.apply(4)); // (4^2) + 1 = 17

        // identity
        Function<String, String> id = Function.identity();
        System.out.println(id.apply("Hello")); // Hello


    }
}
