package com.pcgs.core.java.pocs.Generics;

public class Pair<k,V> {
    private k key;
    private V value;

    public Pair(k key, V value) {
        this.key = key;
        this.value = value;
    }

    public k getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}
