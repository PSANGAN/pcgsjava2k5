package com.pcgs.core.java.pocs.interfaces.functionalinterface;

import java.util.function.Function;

public class FunctionPoC {
    public static void main(String[] args) {

        //Declaration
        Function<Integer, Integer> square = (x) -> x * x;
        Function<Integer, Integer> add = (x) -> x + (x * x);

        //Invocation(s)
        System.out.println(square.apply(10));
        System.out.println(add.apply(10));

        Function<String, String> noChange = Function.identity();

        System.out.println(noChange.apply("Saravanan")); // Saravanan

        // First Add then followed by andThen ((7 +(49) * 7 +(49))
        System.out.println(add.andThen(square).apply(7));

        // First Compose then followed by Add (7 * 7) +) 49 + (49 * 49)
        System.out.println(add.compose(square).apply(7));

    }
}
