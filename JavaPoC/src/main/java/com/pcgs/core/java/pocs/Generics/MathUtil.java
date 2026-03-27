package com.pcgs.core.java.pocs.Generics;

import java.util.List;

public class MathUtil {

    public static <T extends Comparable<T>> T min(T item1, T item2) {
       return (item1.compareTo(item2) < 0)? item1 : item2;
    }

    public static <T extends Number> double add(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static <T extends Number> T sum(T num1, T num2) {
        Double sum = num1.doubleValue() + num2.doubleValue();
        return (T) sum;   // unchecked cast
    }

    public static <T extends Comparable<T>> int
    countGreater(List<T> list, T item) {
        int count = 0;
        for (T element : list) {
            if (element.compareTo(item) > 0) {
                count++;
            }
        }
        return count;
    }

}
