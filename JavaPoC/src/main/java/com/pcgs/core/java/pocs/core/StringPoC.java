package com.pcgs.core.java.pocs.core;

public class StringPoC {
    public static void main(String[] args) {
        System.out.println("This is the last word".lastIndexOf("word"));
        System.out.println(new String("This is the last word").lastIndexOf("word", 2));
    }
}
