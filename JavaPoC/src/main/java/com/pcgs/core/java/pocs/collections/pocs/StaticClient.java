package com.pcgs.core.java.pocs.collections.pocs;

import java.util.*;

public class StaticClient {
    public static void main(String[] args){
        System.out.println("Hello World");

        List<String> stringArrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        stringArrayList.add("Apple");
        stringArrayList.add("Banana");
        stringArrayList.add("Mango");

        linkedList.add("Dog");
        linkedList.add("Cat");
        linkedList.add("Elephant");

        doWorkOnArrayList(stringArrayList);


    }

    static void doWorkOnArrayList(List<String> list){
        System.out.println(list.get(0));
        list.set(0, "Orange");
        System.out.println(list.get(0));
        list.remove(0);
        for(String temp: list){
            System.out.println(temp);
        }

        Collections.sort(list);
        for(String temp: list){
            System.out.println(temp);
        }

        Collections.reverse(list);
        for(String temp: list){
            System.out.println(temp);
        }

        Collections.shuffle(list);
        for(String temp: list){
            System.out.println(temp);
        }

        Collections.sort(list);
        System.out.println(Collections.binarySearch(list, "Mango"));

        List<String> newCopyList = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        Collections.fill(newCopyList, "X");
        for(String temp:newCopyList)
        {
            System.out.println(temp);
        }

        List<String> copyNewList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collections.copy(newCopyList, copyNewList);
        for(String temp:newCopyList)
        {
            System.out.println(temp);
        }


    }


}
