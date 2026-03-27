package com.pcgs.core.java.pocs.oops;

import java.util.Objects;

public class EqualsPoC {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        System.out.println(a == b);

        // Compile Time Error
        //System.out.println(a.equals(b));

        Person p1 = new Person("John", 25);
        Person p2 = new Person("John", 25);
        //True - bcs References are same!
        System.out.println(p1 == p2);
        // True - Value like equals/hashcode return same value
        System.out.println(p1.equals(p2));

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        Person p3 = p2;
        // True - bcs References are same!
        System.out.println(p3 == p2);
        // False - bcs References are different
        System.out.println(p3 == p1);
        // True - Value like equals/hashcode return same value
        System.out.println(p3.equals(p2));
        // True - Value like equals/hashcode return same value
        System.out.println(p3.equals(p1));

        String strOne ="Aa";
        String strTwo ="Aa";
        // True - bcs References are same due to String Pool
        System.out.println(strOne == strTwo);
        // True - Both Strings have same values
        System.out.println(strOne.equals(strTwo));

        strOne = strTwo;
        // True - bcs References are same due to String Pool
        System.out.println(strOne == strTwo);
        // True - Both Strings have same values
        System.out.println(strOne.equals(strTwo));

        String strThree = new String("Aa");
        // False - bcs References are different
        System.out.println(strOne == strThree);
        // True - Both Strings have same values
        System.out.println(strOne.equals(strThree));

        String strFour = new String("Aa");
        // False - bcs References are different
        System.out.println(strThree == strFour);
        // True - Both Strings have same values
        System.out.println(strFour.equals(strThree));

        Integer i1 = 1000;
        Integer i2 = 1000;
        // False - bcs References are different
        System.out.println(i1 == i2);
        // True - Both Integer have same values
        System.out.println(i1.equals(i2));

        Integer i3 = i1;
        // True - Both Integer have same reference
        System.out.println(i3 == i1);
        // True - Both Integer have same values
        System.out.println(i3.equals(i1));
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
