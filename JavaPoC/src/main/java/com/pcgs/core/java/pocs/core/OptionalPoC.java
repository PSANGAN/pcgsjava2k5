package com.pcgs.core.java.pocs.core;

import java.util.Optional;

import static java.util.Optional.empty;

public class OptionalPoC {
    public static void main(String[] args) {
        String[] stringArray = new String[10];
        stringArray[7]= "Non-Empty";

      Optional<String> emptyTemp =  Optional.empty();
      System.out.println(emptyTemp);

        Optional<String> ofTemp = Optional.of(stringArray[7]);
        System.out.println(ofTemp);

        System.out.println(ofTemp.get().toUpperCase());
       // System.out.println(emptyTemp.get().toUpperCase()); // ERROR - NO VALUE

        Optional.of(stringArray[7]).filter(s -> s != null).ifPresent(s -> System.out.println(s.toUpperCase()));



    }
}
