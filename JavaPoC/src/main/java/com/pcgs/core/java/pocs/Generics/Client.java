package com.pcgs.core.java.pocs.Generics;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
       System.out.println(MathUtil.min(10, 20));
        System.out.println(MathUtil.min(20, 20));
        System.out.println(MathUtil.min(30, 20));

       var myList = Client.<String>createList();
       myList.add("Hello");
       myList.add("World");

        var myIntList = Client.createList();
        myIntList.add(101);
        myIntList.add(201);
    }


    public static <T> List<T> createList(){
        return new ArrayList<T>();
    }

}

