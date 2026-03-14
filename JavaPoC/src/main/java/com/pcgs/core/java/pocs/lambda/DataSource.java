package com.pcgs.core.java.pocs.lambda;

import java.time.LocalDate;
import java.util.List;

public class DataSource {

    public static List<Person> people() {
        return List.of(
                new Person(1, "Alice", 28, new Address("Austin", "TX", "USA")),
                new Person(2, "Bob", 35, new Address("Dallas", "TX", "USA")),
                new Person(3, "Charlie", 22, new Address("Orlando", "FL", "USA")),
                new Person(4, "Diana", 42, new Address("Tampa", "FL", "USA")),
                new Person(5, "Eve", 31, new Address("Seattle", "WA", "USA"))
        );
    }

    public static List<Product> products() {
        return List.of(
                new Product(1, "Laptop", 1200, "Electronics"),
                new Product(2, "Mouse", 25, "Electronics"),
                new Product(3, "Keyboard", 45, "Electronics"),
                new Product(4, "Desk", 200, "Furniture"),
                new Product(5, "Chair", 150, "Furniture")
        );
    }

    public static List<Order> orders() {
        return List.of(
                new Order(1, 1, List.of(products().get(0), products().get(1)), LocalDate.now().minusDays(10)),
                new Order(2, 2, List.of(products().get(3)), LocalDate.now().minusDays(5)),
                new Order(3, 1, List.of(products().get(4), products().get(2)), LocalDate.now().minusDays(2)),
                new Order(4, 3, List.of(products().get(1)), LocalDate.now().minusDays(1))
        );
    }
}
