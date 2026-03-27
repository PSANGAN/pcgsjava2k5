package com.pcgs.core.java.pocs.collections;

public class SampleWorker<T,U> {

    private T item1;
    private U item2;

    public SampleWorker(T item1, U item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public void doWork() {
        System.out.println("Item 1: " + item1);
        System.out.println("Item 2: " + item2);
    }

    public T getItem1(){
        return item1;
    }

    public U getItem2(){
        return item2;
    }

    public  void print(T item) {
        System.out.println(item);
    }

    public <X> void anotherPrint(X item) {
        System.out.println(item);
    }

    public <X, R> R convert(X input, Class<R> targetType) {
        return targetType.cast(input.toString());
    }


    @Override
    public String toString() {
        return "SampleWorker [item1=" + item1 + ", item2=" + item2 + "]";
    }
}
