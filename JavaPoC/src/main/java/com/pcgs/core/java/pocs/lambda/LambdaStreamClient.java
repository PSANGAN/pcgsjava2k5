package com.pcgs.core.java.pocs.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LambdaStreamClient {

    public static void main(String[] args){
        /*var listofPeople = DataSource.people().stream().filter( pepole -> pepole.age() > 30).toList();
        listofPeople.forEach( output -> System.out.println(output));

        var listOfNames = DataSource.people().stream().map( people -> people.name()).toList();
        listOfNames.forEach( output -> System.out.println(output));

        List<Person> persons = new ArrayList<>(DataSource.people());
        persons.sort( Comparator.comparingInt( person -> person.age()));
        persons.forEach( output -> System.out.println(output));

        DataSource.people().forEach( person -> System.out.println(person.address().city()) );

        DataSource.people().stream().map( person -> person.address().city()).forEach(System.out::println);

        DataSource.products().stream().map( product -> product.name().toUpperCase()).forEach(System.out::println);

        DataSource.orders().stream().filter( order -> order.orderDate()
                .isBefore(LocalDate.now().minusDays(7))).forEach(System.out::println);

        DataSource.orders().stream().filter( order -> order.orderDate()
                .isBefore(LocalDate.now().minusDays(7))).forEach(System.out::println);*/


        double totalRevenue = DataSource.orders().stream() .flatMap(order -> order.products().stream()).mapToDouble(Product::price).sum();
        System.out.println("Total Revenue = " + totalRevenue);
    }
}
