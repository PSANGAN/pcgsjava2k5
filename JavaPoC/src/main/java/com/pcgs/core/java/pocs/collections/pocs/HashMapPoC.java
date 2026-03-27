package com.pcgs.core.java.pocs.collections.pocs;

import java.util.HashMap;
import java.util.Objects;

public class HashMapPoC {
    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();

        Person p1 = new Person("John", 25);
        Person p2 = new Person("John", 25);

        map.put(p1, "First");
        map.put(p2, "Second");

        System.out.println(map.size());

        HashMap<PersonHash, String> mapHash = new HashMap<>();

        PersonHash ph1 = new PersonHash("John", 25);
        PersonHash ph2 = new PersonHash("John", 25);

        mapHash.put(ph1, "First");
        mapHash.put(ph2, "Second");

        System.out.println(mapHash.size()); // 2
        // What happens when you put a duplicate key into a Map
        // The Existing Value will be overwritten
        System.out.println(mapHash.get(ph1)); // Second
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class PersonHash {
    String name;
    int age;

    PersonHash(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonHash)) return false;

        PersonHash p = (PersonHash) o;
        return age == p.age && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}