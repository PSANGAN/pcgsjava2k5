package com.pcgs.core.java.pocs.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CollectorArchitectureDemo {

    public static void main(String[] args) {
//        System.out.println("=== UNDERSTANDING THE ARCHITECTURE ===");
//        architectureOverview();
//
//        System.out.println("\n=== COLLECT() METHOD USAGE ===");
//        collectMethodExamples();

        System.out.println("\n=== COLLECTORS CLASS (FACTORY) ===");
        collectorsClassExamples();

//        System.out.println("\n=== CUSTOM COLLECTOR IMPLEMENTATION ===");
//        customCollectorExample();
//
//        System.out.println("\n=== ADVANCED COLLECTORS ===");
//        advancedCollectors();
//
//        System.out.println("\n=== COLLECTOR COMPOSITION ===");
//        collectorComposition();
    }

    static void architectureOverview() {
        System.out.println("ARCHITECTURE RELATIONSHIP:");
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  Stream.collect(Collector<T,A,R>)          │");
        System.out.println("│  - Terminal operation on Stream             │");
        System.out.println("│  - Takes a Collector as parameter           │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.println("                    ↓");
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  Collector<T,A,R> Interface                 │");
        System.out.println("│  T = Type of input elements                 │");
        System.out.println("│  A = Type of mutable accumulator            │");
        System.out.println("│  R = Type of final result                   │");
        System.out.println("│                                             │");
        System.out.println("│  Methods:                                   │");
        System.out.println("│  - supplier()      : Creates accumulator    │");
        System.out.println("│  - accumulator()   : Adds element to accum  │");
        System.out.println("│  - combiner()      : Merges accumulators    │");
        System.out.println("│  - finisher()      : Final transformation   │");
        System.out.println("│  - characteristics(): Optimization hints    │");
        System.out.println("└─────────────────────────────────────────────┘");
        System.out.println("                    ↑");
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  Collectors Class (Factory)                 │");
        System.out.println("│  - Provides pre-built Collector instances   │");
        System.out.println("│  - toList(), toSet(), groupingBy(), etc.    │");
        System.out.println("└─────────────────────────────────────────────┘");
    }

    // 1. COLLECT() METHOD - Terminal operation
    static void collectMethodExamples() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Using collect() with pre-built Collectors
        List<String> list = names.stream()
                .collect(Collectors.toList());
        System.out.println("Collected to List: " + list);

        // collect() has two forms:

        // Form 1: collect(Collector<T,A,R> collector)
        Set<String> set = names.stream()
                .collect(Collectors.toSet());
        System.out.println("Using Collector: " + set);

        // Form 2: collect(Supplier, Accumulator, Combiner) - manual
        ArrayList<String> manual = names.stream()
                .collect(
                        () -> new ArrayList<>(),           // Supplier
                        (list2, item) -> list2.add(item),  // Accumulator
                        (list1, list2) -> list1.addAll(list2) // Combiner
                );
        System.out.println("Manual collect: " + manual);

        // With method references
        ArrayList<String> manual2 = names.stream()
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        System.out.println("Manual with references: " + manual2);
    }

    // 2. COLLECTORS CLASS - Factory for common Collectors
    static void collectorsClassExamples() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "IT", 70000),
                new Person("Bob", 25, "HR", 50000),
                new Person("Charlie", 35, "IT", 80000),
                new Person("David", 28, "HR", 55000),
                new Person("Eve", 32, "IT", 75000)
        );

        // 1. toList() - collect to List
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("toList: " + names);

        // 2. toSet() - collect to Set
        Set<String> departments = people.stream()
                .map(Person::getDepartment)
                .collect(Collectors.toSet());
        System.out.println("toSet: " + departments);

        // 3. toCollection() - collect to specific collection
        TreeSet<String> sortedNames = people.stream()
                .map(Person::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("toCollection: " + sortedNames);

        // 4. toMap() - collect to Map
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(
                        Person::getName,
                        Person::getAge
                ));
        System.out.println("toMap: " + nameToAge);

        // 5. joining() - concatenate strings
        String joined = people.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joining: " + joined);

        // 6. counting() - count elements
        Long count = people.stream()
                .collect(Collectors.counting());
        System.out.println("counting: " + count);

        // 7. summingInt/Long/Double - sum values
        Integer totalAge = people.stream()
                .collect(Collectors.summingInt(Person::getAge));
        System.out.println("summingInt: " + totalAge);

        // 8. averagingInt/Long/Double - average values
        Double avgSalary = people.stream()
                .collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("averagingDouble: " + avgSalary);

        // 9. summarizingInt/Long/Double - statistics
        IntSummaryStatistics ageStats = people.stream()
                .collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("summarizingInt: " + ageStats);

        // 10. groupingBy() - group elements
        Map<String, List<Person>> byDept = people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("groupingBy: " + byDept);

        // 11. partitioningBy() - partition into true/false
        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 30));
        System.out.println("partitioningBy: " + partitioned);
    }

    // 3. COLLECTOR INTERFACE - Custom implementation
    static void customCollectorExample() {
        System.out.println("\n--- Custom Collector: ToImmutableList ---");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using our custom collector
        List<String> immutableList = names.stream()
                .collect(new ToImmutableListCollector<>());

        System.out.println("Result: " + immutableList);
        System.out.println("Class: " + immutableList.getClass().getName());

        // Try to modify (will throw exception)
        try {
            immutableList.add("David");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify: " + e.getClass().getSimpleName());
        }

        System.out.println("\n--- Custom Collector: Statistics ---");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Statistics stats = numbers.stream()
                .collect(new StatisticsCollector());

        System.out.println(stats);

        System.out.println("\n--- Custom Collector: StringJoiner ---");

        String result = names.stream()
                .collect(new CustomStringJoiner(", ", "[", "]"));
        System.out.println("Result: " + result);
    }

    // 4. ADVANCED COLLECTORS
    static void advancedCollectors() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "IT", 70000),
                new Person("Bob", 25, "HR", 50000),
                new Person("Charlie", 35, "IT", 80000),
                new Person("David", 28, "HR", 55000),
                new Person("Eve", 32, "IT", 75000)
        );

        // Nested grouping
        Map<String, Map<Boolean, List<Person>>> nestedGroup = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.partitioningBy(p -> p.getAge() > 30)
                ));
        System.out.println("Nested grouping: " + nestedGroup);

        // Grouping with counting
        Map<String, Long> deptCounts = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("Department counts: " + deptCounts);

        // Grouping with average
        Map<String, Double> avgSalaryByDept = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.averagingDouble(Person::getSalary)
                ));
        System.out.println("Avg salary by dept: " + avgSalaryByDept);

        // Mapping collector
        Map<String, List<String>> deptToNames = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.mapping(Person::getName, Collectors.toList())
                ));
        System.out.println("Dept to names: " + deptToNames);

        // collectingAndThen - post-process result
        List<String> unmodifiableList = people.stream()
                .map(Person::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
        System.out.println("Unmodifiable: " + unmodifiableList);
    }

    // 5. COLLECTOR COMPOSITION
    static void collectorComposition() {
        List<Person> people = Arrays.asList(
                new Person("Alice", 30, "IT", 70000),
                new Person("Bob", 25, "HR", 50000),
                new Person("Charlie", 35, "IT", 80000),
                new Person("David", 28, "HR", 55000),
                new Person("Eve", 32, "IT", 75000)
        );

        // Complex composition: Group by department,
        // get highest paid person in each department
        Map<String, Optional<Person>> highestPaidByDept = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Person::getSalary))
                ));
        System.out.println("Highest paid by dept: " + highestPaidByDept);

        // Get names of highest paid
        Map<String, String> highestPaidNames = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Person::getSalary)),
                                opt -> opt.map(Person::getName).orElse("None")
                        )
                ));
        System.out.println("Names of highest paid: " + highestPaidNames);

        // Multiple aggregations
        Map<String, DoubleSummaryStatistics> salaryStatsByDept = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getDepartment,
                        Collectors.summarizingDouble(Person::getSalary)
                ));
        System.out.println("Salary stats by dept: " + salaryStatsByDept);
    }
}

// ============= CUSTOM COLLECTOR IMPLEMENTATIONS =============

// Custom Collector 1: ToImmutableListCollector
class ToImmutableListCollector<T> implements Collector<T, List<T>, List<T>> {

    // Supplier: Creates the mutable accumulator (ArrayList)
    @Override
    public Supplier<List<T>> supplier() {
        System.out.println("  [supplier] Creating new ArrayList");
        return ArrayList::new;
    }

    // Accumulator: Adds element to accumulator
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> {
            System.out.println("  [accumulator] Adding: " + item);
            list.add(item);
        };
    }

    // Combiner: Merges two accumulators (for parallel streams)
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            System.out.println("  [combiner] Merging lists");
            list1.addAll(list2);
            return list1;
        };
    }

    // Finisher: Converts accumulator to final result
    @Override
    public Function<List<T>, List<T>> finisher() {
        return list -> {
            System.out.println("  [finisher] Creating unmodifiable list");
            return Collections.unmodifiableList(list);
        };
    }

    // Characteristics: Optimization hints
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
        // Could return: CONCURRENT, UNORDERED, IDENTITY_FINISH
    }
}

// Custom Collector 2: Statistics Collector
class StatisticsCollector implements Collector<Integer, Statistics, Statistics> {

    @Override
    public Supplier<Statistics> supplier() {
        return Statistics::new;
    }

    @Override
    public BiConsumer<Statistics, Integer> accumulator() {
        return Statistics::accept;
    }

    @Override
    public BinaryOperator<Statistics> combiner() {
        return Statistics::combine;
    }

    @Override
    public Function<Statistics, Statistics> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(
                Characteristics.IDENTITY_FINISH,
                Characteristics.UNORDERED
        );
    }
}

class Statistics {
    private int count = 0;
    private int sum = 0;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    public void accept(int value) {
        count++;
        sum += value;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public Statistics combine(Statistics other) {
        count += other.count;
        sum += other.sum;
        min = Math.min(min, other.min);
        max = Math.max(max, other.max);
        return this;
    }

    public double getAverage() {
        return count > 0 ? (double) sum / count : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "Statistics{count=%d, sum=%d, min=%d, max=%d, avg=%.2f}",
                count, sum, min, max, getAverage()
        );
    }
}

// Custom Collector 3: String Joiner
class CustomStringJoiner implements Collector<String, StringJoiner, String> {
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public CustomStringJoiner(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Supplier<StringJoiner> supplier() {
        return () -> new StringJoiner(delimiter, prefix, suffix);
    }

    @Override
    public BiConsumer<StringJoiner, String> accumulator() {
        return StringJoiner::add;
    }

    @Override
    public BinaryOperator<StringJoiner> combiner() {
        return StringJoiner::merge;
    }

    @Override
    public Function<StringJoiner, String> finisher() {
        return StringJoiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}

// Supporting class
class Person {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Person(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + "(" + age + "," + department + ",$" + salary + ")";
    }
}
