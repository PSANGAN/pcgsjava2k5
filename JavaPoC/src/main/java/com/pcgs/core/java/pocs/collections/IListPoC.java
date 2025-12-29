package com.pcgs.core.java.pocs.collections;

import java.util.ArrayList;
import java.util.List;

class IListPoC {
    public static void main(String[] args) {
        System.out.println("IListPoC");

        List<String> list = new ArrayList<>();
        list.add("C#");
        list.add("Javascript");
        list.add("Java");
        list.add("Rust");
        list.add(3,"Python");
        System.out.println(list.get(3));

        list.set(0,"Java");
        list.set(2,"C#");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(101);
        integerList.add(201);
        integerList.add(301);
        integerList.add(101);
        integerList.add(201);

        System.out.println(integerList.indexOf(301));
        System.out.println(integerList.lastIndexOf(301));

        System.out.println(integerList.indexOf(101));
        System.out.println(integerList.lastIndexOf(201));

        // integerList.remove(201); IndexOutOfBoundsException
        integerList.remove(Integer.valueOf(201));

        for(Integer integer : integerList) {
            System.out.println(integer);
        }

    }
}
