package com.pcgs.core.java.pocs.MapSearch;

import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

class CustomObjectSearch {
    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 35));
        people.add(new Person("David", 40));

        // List must be sorted (already sorted by age)
        Person searchPerson = new Person("", 35);
        int index = Collections.binarySearch(people, searchPerson);

        if (index >= 0) {
            System.out.println("Found: " + people.get(index)); // Charlie (35)
        }

        // Search by name using custom comparator
        Collections.sort(people, Comparator.comparing(p -> p.name));
        int idx = Collections.binarySearch(people, new Person("Charlie", 0),
                Comparator.comparing(p -> p.name));
        System.out.println("Found by name: " + people.get(idx));
    }
}
