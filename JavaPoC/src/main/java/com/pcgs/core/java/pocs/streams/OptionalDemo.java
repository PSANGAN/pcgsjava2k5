package com.pcgs.core.java.pocs.streams;

import java.util.*;
import java.util.stream.*;

public class OptionalDemo {

    public static void main(String[] args) {
        System.out.println("=== OPTIONAL CREATION ===");
        optionalCreation();

        System.out.println("\n=== OPTIONAL METHODS ===");
        optionalMethods();

        System.out.println("\n=== OPTIONAL WITH STREAMS ===");
        optionalWithStreams();

        System.out.println("\n=== PRACTICAL EXAMPLES ===");
        practicalExamples();
    }

    // 1. Creating Optional instances
    static void optionalCreation() {
        // Empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("Empty: " + empty);

        // Optional with non-null value
        Optional<String> present = Optional.of("Hello");
        System.out.println("Present: " + present);

        // Optional that may be null
        String nullableValue = null;
        Optional<String> nullable = Optional.ofNullable(nullableValue);
        System.out.println("Nullable: " + nullable);

        // This would throw NullPointerException
        // Optional<String> error = Optional.of(null);
    }

    // 2. Common Optional methods
    static void optionalMethods() {
        Optional<String> value = Optional.of("Java");
        Optional<String> empty = Optional.empty();

        // isPresent() - check if value exists
        System.out.println("Value present: " + value.isPresent());
        System.out.println("Empty present: " + empty.isPresent());

        // isEmpty() - check if empty (Java 11+)
        System.out.println("Value empty: " + value.isEmpty());

        // get() - retrieve value (throws if empty)
        System.out.println("Get value: " + value.get());

        // orElse() - provide default value
        System.out.println("Empty orElse: " + empty.orElse("Default"));

        // orElseGet() - provide default via Supplier
        System.out.println("Empty orElseGet: " +
                empty.orElseGet(() -> "Computed Default"));

        // orElseThrow() - throw exception if empty
        try {
            empty.orElseThrow(() -> new RuntimeException("No value!"));
        } catch (RuntimeException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // ifPresent() - execute action if present
        value.ifPresent(v -> System.out.println("Value exists: " + v));

        // ifPresentOrElse() - execute action or else (Java 9+)
        empty.ifPresentOrElse(
                v -> System.out.println("Has value: " + v),
                () -> System.out.println("No value found")
        );

        // map() - transform value if present
        Optional<Integer> length = value.map(String::length);
        System.out.println("Mapped length: " + length.get());

        // flatMap() - transform to another Optional
        Optional<String> upper = value.flatMap(v ->
                Optional.of(v.toUpperCase()));
        System.out.println("FlatMapped: " + upper.get());

        // filter() - filter based on predicate
        Optional<String> filtered = value.filter(v -> v.length() > 3);
        System.out.println("Filtered: " + filtered);

        // or() - provide alternative Optional (Java 9+)
        Optional<String> alternative = empty.or(() ->
                Optional.of("Alternative"));
        System.out.println("Alternative: " + alternative.get());
    }

    // 3. Optional with Streams
    static void optionalWithStreams() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie",
                "David", "Eve");

        // findFirst() - returns Optional
        Optional<String> first = names.stream()
                .filter(n -> n.startsWith("C"))
                .findFirst();
        System.out.println("First starting with C: " +
                first.orElse("Not found"));

        // findAny() - returns Optional
        Optional<String> any = names.stream()
                .filter(n -> n.length() > 4)
                .findAny();
        System.out.println("Any with length > 4: " + any.get());

        // max() and min() - return Optional
        Optional<String> longest = names.stream()
                .max(Comparator.comparing(String::length));
        System.out.println("Longest name: " + longest.get());

        // reduce() - returns Optional
        Optional<String> concatenated = names.stream()
                .reduce((a, b) -> a + ", " + b);
        System.out.println("Concatenated: " + concatenated.get());

        // Stream of Optionals to Stream of values (Java 9+)
        List<Optional<String>> optionals = Arrays.asList(
                Optional.of("One"),
                Optional.empty(),
                Optional.of("Three"),
                Optional.empty(),
                Optional.of("Five")
        );

        List<String> values = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("Filtered optionals: " + values);

        // Using Optional in stream operations
        List<String> uppercased = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        Optional<String> result = uppercased.stream()
                .filter(n -> n.contains("CHARLIE"))
                .findFirst();
        result.ifPresent(r -> System.out.println("Found: " + r));
    }

    // 4. Practical examples
    static void practicalExamples() {
        // Example 1: User lookup
        User user = findUserById(1);
        Optional<User> userOpt = Optional.ofNullable(user);

        String email = userOpt
                .map(User::getEmail)
                .orElse("no-email@example.com");
        System.out.println("User email: " + email);

        // Example 2: Chaining operations
        Optional<String> domain = userOpt
                .map(User::getEmail)
                .filter(e -> e.contains("@"))
                .map(e -> e.substring(e.indexOf("@") + 1));
        System.out.println("Email domain: " +
                domain.orElse("unknown"));

        // Example 3: Nested Optionals with flatMap
        Optional<Address> address = userOpt
                .flatMap(User::getAddress);
        String city = address
                .map(Address::getCity)
                .orElse("Unknown City");
        System.out.println("User city: " + city);

        // Example 4: Stream processing with Optional
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        Optional<Integer> firstEvenGreaterThanFive = numbers.stream()
                .filter(n -> n % 2 == 0)
                .filter(n -> n > 5)
                .findFirst();

        firstEvenGreaterThanFive.ifPresentOrElse(
                n -> System.out.println("Found: " + n),
                () -> System.out.println("Not found")
        );
    }

    // Helper methods and classes
    static User findUserById(int id) {
        return new User(id, "alice@example.com",
                new Address("123 Main St", "Springfield"));
    }
}

// Supporting classes
class User {
    private int id;
    private String email;
    private Address address;

    public User(int id, String email, Address address) {
        this.id = id;
        this.email = email;
        this.address = address;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

class Address {
    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
}
