package com.pcgs.core.java.pocs.Compare;

public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Person o) {
       if(o == null) {
           throw new NullPointerException();
       }

       int ageComparsion = Integer.compare(this.age, o.age);
       if(ageComparsion != 0)
           return ageComparsion;

       return this.name.compareTo(o.name);
    }
}
