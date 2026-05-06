package com.pcgs.core.java.pocs.interfaces.functionalinterface;

import java.util.function.Predicate;

public class PredicatePoC {
    public static void main(String[] args) {

        Predicate<Integer> isEven = (x) -> x % 2 == 0;
        Predicate<Integer> isDivisibleBy5 = (x) -> ((x % 5) == 0);

        System.out.println(isEven.test(10));
        System.out.println(isDivisibleBy5.test(10));

        //Opposite (negate) of isDivisibleBy5 so for 11 we get reminder as 1
        System.out.println(isEven.negate().test(11));

        // Predicate to check if a number is even and divisible by 5 using and
        System.out.println(isEven.and(isDivisibleBy5).test(10));

        // Predicate to check if either a number is even or not divisible by 5 using or
        System.out.println(isEven.or(isDivisibleBy5.negate()).test(11));

    }
}
