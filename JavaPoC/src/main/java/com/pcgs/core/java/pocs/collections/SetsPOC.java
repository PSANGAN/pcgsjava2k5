package com.pcgs.core.java.pocs.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetsPOC {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Dummy");
        set.add("Rummy");
        set.add("Mummy");
        set.add("Dummy");
        // Null Always Allowed and print first only in hashset
        set.add(null);
        doPrint(set);

        System.out.println(System.lineSeparator());

        set.remove("Rummy");
        doPrint(set);

        System.out.println( set.contains("Dummy"));
        System.out.println(System.lineSeparator());
        set.clear();
        doPrint(set);

        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(1000);
        linkedHashSet.add(100);
        linkedHashSet.add(null);
        linkedHashSet.add(10);
        linkedHashSet.add(1);
        doPrint(linkedHashSet);
        System.out.println(System.lineSeparator());
        Set<Integer> TresSet = new TreeSet<>();
        TresSet.add(1000);
        TresSet.add(100);
        //TresSet.add(null); TreeSet do not allow null
        TresSet.add(10);
        TresSet.add(1);
        doPrint(TresSet);
    }

    static void doPrint(Iterable<?> input)
    {
        for(Object temp:input)
        {
            System.out.println(temp);
        }
    }
}
