package com.pcgs.core.java.pocs;

import com.pcgs.core.java.pocs.Compare.AgeComparator;
import com.pcgs.core.java.pocs.Compare.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 30));

        //Collections.sort(people);
        Collections.sort(people, new AgeComparator());

        Collections.sort(people, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p2.getName().compareTo(p1.getName());  // Descending order
            }
        });

        Comparator<Person> comparator = Comparator.comparing(Person::getAge)  // Primary: age ascending
                .thenComparing(Person::getName);    // Secondary: name ascending

        Collections.sort(people, comparator);  // For equal ages, sorts by name
    }

    private void dummy(){

        List<Object> objs = Stream.of(2, 3.14, "four")
                .collect(Collectors.toCollection(ArrayList::new));
        List<Integer> ints = List.of(5, 6);
        Collections.copy(objs, ints);

        Collections.<Object>copy(objs, ints);
        Collections.<Number>copy(objs, ints);
        Collections.<Integer>copy(objs, ints);

        assert objs.equals(List.of(5, 6, "four"));

        List<?> list1 = GenericPoC.factory();
        List<Object> list2 = GenericPoC.<Object>factory();
    }


}
