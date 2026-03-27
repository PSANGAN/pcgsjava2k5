package com.pcgs.core.java.pocs.collections;

public class TestPOC {

    public static void main(String[] args) {
//       System.out.println(checkEquality(12, 8));
//        System.out.println(checkEquality(10.5, 10.5));
//        System.out.println(checkEquality("Joe", "Joe"));

        SampleWorker<String, String> worker = new SampleWorker("Hello", "World");
        worker.doWork();
        System.out.println(worker.toString());
        worker.print("PCGS");
        worker.anotherPrint(45.56f);
    }

    public static <T> boolean checkEquality(T item1, T Item2){
        return item1.equals(Item2);
        // return (item1 == Item2); Wrong bcs == checks for reference not value
    }
}
