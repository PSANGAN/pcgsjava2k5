package com.pcgs.core.java.pocs.streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamInterviewGuide {

    public static void main(String[] args) {
        System.out.println("=== 1. COLLECTING WITH MAPS ===");
        collectingWithMaps();

        System.out.println("\n=== 2. JOINING STRINGS ===");
        joiningStrings();

        System.out.println("\n=== 3. GROUPING DATA ===");
        groupingData();

        System.out.println("\n=== 4. PARTITIONING DATA ===");
        partitioningData();

        System.out.println("\n=== 5. ADAPTING COLLECTOR RESULTS ===");
        adaptingCollectorResults();

        System.out.println("\n=== 6. FINDING AND MATCHING ===");
        findingAndMatching();
    }

    // ============= 1. COLLECTING WITH MAPS =============
    static void collectingWithMaps() {
        List<Employee> employees = getEmployees();

        System.out.println("--- Basic toMap() ---");
        // Basic: toMap(keyMapper, valueMapper)
        Map<String, Integer> nameToAge = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getAge
                ));
        System.out.println("Name to Age: " + nameToAge);

        System.out.println("\n--- Handling Duplicate Keys ---");
        List<Employee> withDuplicates = new ArrayList<>(employees);
        withDuplicates.add(new Employee("Alice", 35, "Finance", 65000));

        // When duplicate keys exist, use merge function
        Map<String, Integer> merged = withDuplicates.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getAge,
                        (existing, replacement) -> existing  // Keep existing
                        // (existing, replacement) -> replacement  // Keep new
                        // Integer::max  // Keep maximum
                ));
        System.out.println("With merge function: " + merged);

        System.out.println("\n--- Specifying Map Implementation ---");
        // toMap with specific Map type
        TreeMap<String, Integer> treeMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        Employee::getAge,
                        (e1, e2) -> e1,
                        TreeMap::new  // Specify TreeMap
                ));
        System.out.println("TreeMap (sorted): " + treeMap);

        System.out.println("\n--- Complex Value Mapping ---");
        // Map to objects
        Map<String, Employee> nameToEmployee = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        emp -> emp  // or Function.identity()
                ));
        System.out.println("Name to Employee: " + nameToEmployee);

        // Map with computed values
        Map<String, String> nameToInfo = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        emp -> emp.getDepartment() + "-$" + emp.getSalary()
                ));
        System.out.println("Name to Info: " + nameToInfo);

        System.out.println("\n--- Interview Edge Cases ---");
        // Edge Case 1: Empty stream
        Map<String, Integer> emptyMap = Collections.<Employee>emptyList()
                .stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getAge));
        System.out.println("Empty stream: " + emptyMap);

        // Edge Case 2: Null values (will throw NPE)
        try {
            List<Employee> withNull = Arrays.asList(
                    new Employee("Alice", 30, "IT", 70000),
                    new Employee(null, 25, "HR", 50000)  // null key
            );
            withNull.stream()
                    .collect(Collectors.toMap(Employee::getName, Employee::getAge));
        } catch (NullPointerException e) {
            System.out.println("NPE with null key: " + e.getClass().getSimpleName());
        }
    }

    // ============= 2. JOINING STRINGS =============
    static void joiningStrings() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        System.out.println("--- Basic Joining ---");
        // Simple joining
        String joined = names.stream()
                .collect(Collectors.joining());
        System.out.println("No delimiter: " + joined);

        // With delimiter
        String withComma = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("With comma: " + withComma);

        // With delimiter, prefix, and suffix
        String formatted = names.stream()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Formatted: " + formatted);

        System.out.println("\n--- Joining with Transformation ---");
        List<Employee> employees = getEmployees();

        // Join after mapping
        String employeeNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Employee names: " + employeeNames);

        // Complex formatting
        String details = employees.stream()
                .map(emp -> emp.getName() + "(" + emp.getAge() + ")")
                .collect(Collectors.joining(", ", "Employees: [", "]"));
        System.out.println("Details: " + details);

        System.out.println("\n--- Interview Scenarios ---");
        // Scenario 1: CSV generation
        String csv = employees.stream()
                .map(emp -> emp.getName() + "," + emp.getAge() + "," +
                        emp.getDepartment() + "," + emp.getSalary())
                .collect(Collectors.joining("\n", "Name,Age,Department,Salary\n", ""));
        System.out.println("CSV Output:\n" + csv);

        // Scenario 2: SQL IN clause
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
        String sqlIn = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "WHERE id IN (", ")"));
        System.out.println("SQL: " + sqlIn);

        // Scenario 3: Empty stream
        String empty = Collections.<String>emptyList().stream()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Empty stream: '" + empty + "'");
    }

    // ============= 3. GROUPING DATA =============
    static void groupingData() {
        List<Employee> employees = getEmployees();

        System.out.println("--- Simple Grouping ---");
        // Group by single field
        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("By Department: " + byDepartment);

        System.out.println("\n--- Grouping with Downstream Collectors ---");
        // Group and count
        Map<String, Long> deptCounts = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("Department counts: " + deptCounts);

        // Group and sum
        Map<String, Double> totalSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println("Total salary by dept: " + totalSalaryByDept);

        // Group and average
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Avg salary by dept: " + avgSalaryByDept);

        System.out.println("\n--- Grouping with Mapping ---");
        // Group and collect names only
        Map<String, List<String>> deptToNames = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        System.out.println("Dept to names: " + deptToNames);

        // Group and collect to Set
        Map<String, Set<String>> deptToNamesSet = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toSet())
                ));
        System.out.println("Dept to names (Set): " + deptToNamesSet);

        System.out.println("\n--- Multi-level Grouping (Nested) ---");
        // Group by department, then by age group
        Map<String, Map<String, List<Employee>>> nestedGroup = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.groupingBy(emp ->
                                emp.getAge() < 30 ? "Young" : "Senior"
                        )
                ));
        System.out.println("Nested grouping: " + nestedGroup);

        System.out.println("\n--- Grouping with Statistics ---");
        // Get summary statistics per group
        Map<String, DoubleSummaryStatistics> salaryStats = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)
                ));
        salaryStats.forEach((dept, stats) ->
                System.out.println(dept + ": " + stats));

        System.out.println("\n--- Advanced Grouping ---");
        // Group and find max
        Map<String, Optional<Employee>> highestPaid = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary))
                ));
        System.out.println("Highest paid by dept: " + highestPaid);

        // Group by custom criteria
        Map<String, List<Employee>> bySalaryRange = employees.stream()
                .collect(Collectors.groupingBy(emp -> {
                    if (emp.getSalary() < 55000) return "Low";
                    else if (emp.getSalary() < 75000) return "Medium";
                    else return "High";
                }));
        System.out.println("By salary range: " + bySalaryRange);

        System.out.println("\n--- Interview Tip: TreeMap for Sorted Keys ---");
        // Use TreeMap to maintain sorted order
        Map<String, Long> sortedCounts = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        TreeMap::new,  // Sorted map
                        Collectors.counting()
                ));
        System.out.println("Sorted department counts: " + sortedCounts);
    }

    // ============= 4. PARTITIONING DATA =============
    static void partitioningData() {
        List<Employee> employees = getEmployees();

        System.out.println("--- Basic Partitioning ---");
        // Partition by predicate (always returns Map with true/false keys)
        Map<Boolean, List<Employee>> byAge = employees.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.getAge() >= 30
                ));
        System.out.println("Age >= 30: " + byAge.get(true).size());
        System.out.println("Age < 30: " + byAge.get(false).size());

        System.out.println("\n--- Partitioning with Downstream ---");
        // Partition and count
        Map<Boolean, Long> countByAge = employees.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.getAge() >= 30,
                        Collectors.counting()
                ));
        System.out.println("Count by age: " + countByAge);

        // Partition and get names
        Map<Boolean, List<String>> namesByAge = employees.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.getAge() >= 30,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        System.out.println("Names by age: " + namesByAge);

        System.out.println("\n--- Partitioning vs Grouping ---");
        // Grouping by boolean (can have 0, 1, or 2 keys)
        Map<Boolean, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(emp -> emp.getAge() >= 30));
        System.out.println("Grouped keys: " + grouped.keySet());

        // Partitioning by boolean (ALWAYS has 2 keys: true and false)
        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getAge() >= 30));
        System.out.println("Partitioned keys: " + partitioned.keySet());

        System.out.println("\n--- Practical Partitioning Examples ---");
        // Example 1: Separate high/low earners
        Map<Boolean, List<Employee>> byEarnings = employees.stream()
                .collect(Collectors.partitioningBy(
                        emp -> emp.getSalary() > 65000
                ));
        System.out.println("High earners: " + byEarnings.get(true));
        System.out.println("Regular earners: " + byEarnings.get(false));

        // Example 2: Valid/Invalid data
        List<String> numbers = Arrays.asList("1", "2", "abc", "3", "xyz");
        Map<Boolean, List<String>> validNumbers = numbers.stream()
                .collect(Collectors.partitioningBy(s -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }));
        System.out.println("Valid: " + validNumbers.get(true));
        System.out.println("Invalid: " + validNumbers.get(false));

        // Example 3: Pass/Fail
        List<Integer> scores = Arrays.asList(45, 78, 89, 52, 95, 42, 88);
        Map<Boolean, List<Integer>> results = scores.stream()
                .collect(Collectors.partitioningBy(score -> score >= 60));
        System.out.println("Pass (>=60): " + results.get(true));
        System.out.println("Fail (<60): " + results.get(false));
    }

    // ============= 5. ADAPTING COLLECTOR RESULTS =============
    static void adaptingCollectorResults() {
        List<Employee> employees = getEmployees();

        System.out.println("--- collectingAndThen() ---");
        // Post-process collector result
        List<String> immutableNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
        System.out.println("Immutable list: " + immutableNames);

        // Get count as Integer instead of Long
        Integer count = employees.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue
                ));
        System.out.println("Count as Integer: " + count);

        // Get highest paid employee name (not Optional)
        String highestPaidName = employees.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                        opt -> opt.map(Employee::getName).orElse("None")
                ));
        System.out.println("Highest paid: " + highestPaidName);

        System.out.println("\n--- mapping() Collector ---");
        // Map before collecting
        List<String> upperNames = employees.stream()
                .collect(Collectors.mapping(
                        emp -> emp.getName().toUpperCase(),
                        Collectors.toList()
                ));
        System.out.println("Upper names: " + upperNames);

        // Useful in grouping
        Map<String, List<Integer>> deptToAges = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getAge, Collectors.toList())
                ));
        System.out.println("Dept to ages: " + deptToAges);

        System.out.println("\n--- filtering() Collector (Java 9+) ---");
        // Filter during collection
        Map<String, List<Employee>> seniorByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.filtering(
                                emp -> emp.getAge() >= 30,
                                Collectors.toList()
                        )
                ));
        System.out.println("Senior employees by dept: " + seniorByDept);

        System.out.println("\n--- flatMapping() Collector (Java 9+) ---");
        List<Department> departments = Arrays.asList(
                new Department("IT", Arrays.asList("Alice", "Bob", "Charlie")),
                new Department("HR", Arrays.asList("David", "Eve"))
        );

        // Flatten during collection
        List<String> allEmployees = departments.stream()
                .collect(Collectors.flatMapping(
                        dept -> dept.getEmployees().stream(),
                        Collectors.toList()
                ));
        System.out.println("All employees: " + allEmployees);

        System.out.println("\n--- Complex Adaptation ---");
        // Group, filter, map, and format
        Map<String, String> deptSummary = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.filtering(
                                        emp -> emp.getSalary() > 60000,
                                        Collectors.mapping(
                                                Employee::getName,
                                                Collectors.toList()
                                        )
                                ),
                                list -> list.isEmpty() ? "None" : String.join(", ", list)
                        )
                ));
        System.out.println("High earners summary: " + deptSummary);
    }

    // ============= 6. FINDING AND MATCHING =============
    static void findingAndMatching() {
        List<Employee> employees = getEmployees();

        System.out.println("--- findFirst() ---");
        // Find first matching element
        Optional<Employee> firstIT = employees.stream()
                .filter(emp -> emp.getDepartment().equals("IT"))
                .findFirst();
        System.out.println("First IT employee: " +
                firstIT.map(Employee::getName).orElse("None"));

        // findFirst() maintains encounter order
        Optional<String> first = Arrays.asList("a", "b", "c").stream()
                .findFirst();
        System.out.println("First element: " + first.get());

        System.out.println("\n--- findAny() ---");
        // Find any matching element (useful for parallel streams)
        Optional<Employee> anyIT = employees.stream()
                .filter(emp -> emp.getDepartment().equals("IT"))
                .findAny();
        System.out.println("Any IT employee: " +
                anyIT.map(Employee::getName).orElse("None"));

        // findAny() in parallel - may return different results
        Optional<Employee> anyParallel = employees.parallelStream()
                .filter(emp -> emp.getDepartment().equals("IT"))
                .findAny();
        System.out.println("Any (parallel): " +
                anyParallel.map(Employee::getName).orElse("None"));

        System.out.println("\n--- anyMatch() ---");
        // Check if ANY element matches predicate
        boolean hasIT = employees.stream()
                .anyMatch(emp -> emp.getDepartment().equals("IT"));
        System.out.println("Has IT employees: " + hasIT);

        boolean hasHighEarner = employees.stream()
                .anyMatch(emp -> emp.getSalary() > 100000);
        System.out.println("Has salary > 100k: " + hasHighEarner);

        System.out.println("\n--- allMatch() ---");
        // Check if ALL elements match predicate
        boolean allAdults = employees.stream()
                .allMatch(emp -> emp.getAge() >= 18);
        System.out.println("All adults: " + allAdults);

        boolean allHighEarners = employees.stream()
                .allMatch(emp -> emp.getSalary() > 60000);
        System.out.println("All earn > 60k: " + allHighEarners);

        System.out.println("\n--- noneMatch() ---");
        // Check if NO elements match predicate
        boolean noMinors = employees.stream()
                .noneMatch(emp -> emp.getAge() < 18);
        System.out.println("No minors: " + noMinors);

        boolean noneFromFinance = employees.stream()
                .noneMatch(emp -> emp.getDepartment().equals("Finance"));
        System.out.println("None from Finance: " + noneFromFinance);

        System.out.println("\n--- Short-circuiting Behavior ---");
        // These operations stop as soon as result is determined
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        boolean hasEven = numbers.stream()
                .peek(n -> System.out.print(n + " "))
                .anyMatch(n -> n % 2 == 0);
        System.out.println("\nHas even: " + hasEven);

        System.out.println("\n--- Interview Edge Cases ---");
        // Edge Case 1: Empty stream
        Optional<Employee> empty = Collections.<Employee>emptyList()
                .stream()
                .findFirst();
        System.out.println("Empty findFirst: " + empty.isPresent());

        boolean emptyAnyMatch = Collections.<Employee>emptyList()
                .stream()
                .anyMatch(emp -> true);
        System.out.println("Empty anyMatch: " + emptyAnyMatch);

        boolean emptyAllMatch = Collections.<Employee>emptyList()
                .stream()
                .allMatch(emp -> false);
        System.out.println("Empty allMatch: " + emptyAllMatch);  // true!

        boolean emptyNoneMatch = Collections.<Employee>emptyList()
                .stream()
                .noneMatch(emp -> true);
        System.out.println("Empty noneMatch: " + emptyNoneMatch);  // true!

        System.out.println("\n--- Practical Combinations ---");
        // Find oldest employee
        Optional<Employee> oldest = employees.stream()
                .max(Comparator.comparing(Employee::getAge));
        oldest.ifPresent(emp ->
                System.out.println("Oldest: " + emp.getName() + " (" + emp.getAge() + ")"));

        // Check if all employees in IT earn > 60k
        boolean allITHighEarners = employees.stream()
                .filter(emp -> emp.getDepartment().equals("IT"))
                .allMatch(emp -> emp.getSalary() > 60000);
        System.out.println("All IT earn > 60k: " + allITHighEarners);

        // Find first employee with salary > 70k
        employees.stream()
                .filter(emp -> emp.getSalary() > 70000)
                .findFirst()
                .ifPresentOrElse(
                        emp -> System.out.println("Found: " + emp.getName()),
                        () -> System.out.println("Not found")
                );
    }

    // Helper method to create sample data
    static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee("Alice", 30, "IT", 70000),
                new Employee("Bob", 25, "HR", 50000),
                new Employee("Charlie", 35, "IT", 80000),
                new Employee("David", 28, "HR", 55000),
                new Employee("Eve", 32, "IT", 75000),
                new Employee("Frank", 29, "Finance", 65000)
        );
    }
}

// Supporting classes
class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
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

class Department {
    private String name;
    private List<String> employees;

    public Department(String name, List<String> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() { return name; }
    public List<String> getEmployees() { return employees; }
}