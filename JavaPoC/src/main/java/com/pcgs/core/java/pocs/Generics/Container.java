package com.pcgs.core.java.pocs.Generics;

public interface Container<T> {
    void add(T item);
    T get(int index);
    int size();
}
