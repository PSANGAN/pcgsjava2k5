package com.pcgs.core.java.pocs.reflection;

public class ModelClass {
    private String name;
    private int age;

    public ModelClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


class Animal    {}

class SeaAnimal extends Animal    {}

class WildAnimal extends Animal {}

class Master extends Animal implements Marker {
    private Master(){}

    @Override
    public String toString() {
        return "Master{}";
    }
}

interface Marker {

}