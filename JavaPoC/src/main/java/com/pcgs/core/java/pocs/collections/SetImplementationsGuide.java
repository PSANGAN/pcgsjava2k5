package com.pcgs.core.java.pocs.collections;

import java.util.*;

/**
 * Comprehensive Guide to Java Set Implementations
 * Demonstrates HashSet, LinkedHashSet, TreeSet, EnumSet, SortedSet, NavigableSet
 */
public class SetImplementationsGuide {

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        demonstrateHashSet();
        demonstrateLinkedHashSet();
        demonstrateTreeSet();
        demonstrateEnumSet();
        demonstrateSortedSet();
        demonstrateNavigableSet();
        performanceComparison();
        demonstrateSetOperations();
    }

    // ==================== 1. HASHSET ====================
    static void demonstrateHashSet() {
        System.out.println("\n=== HASHSET ===");

        // HashSet: Hash table implementation, no order guarantee
        HashSet<String> hashSet = new HashSet<>();

        // Adding elements - O(1) average
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple"); // Duplicate - won't be added

        System.out.println("HashSet: " + hashSet);
        System.out.println("Size: " + hashSet.size()); // 3, not 4

        // Contains - O(1) average
        System.out.println("Contains Banana: " + hashSet.contains("Banana"));

        // Remove - O(1) average
        hashSet.remove("Banana");
        System.out.println("After removal: " + hashSet);

        // Initial capacity and load factor
        HashSet<Integer> customSet = new HashSet<>(32, 0.75f);

        // Iteration - order not guaranteed
        System.out.println("Iteration order (unpredictable):");
        for (String fruit : hashSet) {
            System.out.println("  " + fruit);
        }

        // Creating from collection
        List<String> list = Arrays.asList("X", "Y", "Z", "X");
        HashSet<String> fromList = new HashSet<>(list);
        System.out.println("From list (duplicates removed): " + fromList);

        // Null handling
        hashSet.add(null); // HashSet allows one null
        System.out.println("With null: " + hashSet);
    }

    // ==================== 2. LINKEDHASHSET ====================
    static void demonstrateLinkedHashSet() {
        System.out.println("\n=== LINKEDHASHSET ===");

        // LinkedHashSet: Hash table + linked list, maintains insertion order
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        // Adding elements - O(1) average
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        linkedHashSet.add("First"); // Duplicate ignored

        System.out.println("LinkedHashSet: " + linkedHashSet);

        // Maintains insertion order
        System.out.println("Iteration (insertion order preserved):");
        for (String item : linkedHashSet) {
            System.out.println("  " + item);
        }

        // Contains and Remove - O(1) average
        System.out.println("Contains Second: " + linkedHashSet.contains("Second"));
        linkedHashSet.remove("Second");
        System.out.println("After removal: " + linkedHashSet);

        // Re-adding doesn't change position
        linkedHashSet.add("Third"); // Already exists, position unchanged
        linkedHashSet.add("Fourth"); // New element, added at end
        System.out.println("After re-adding: " + linkedHashSet);

        // Use case: LRU Cache-like behavior
        LinkedHashSet<String> recentItems = new LinkedHashSet<>();
        recentItems.add("Item1");
        recentItems.add("Item2");
        recentItems.add("Item3");
        // To move to end: remove and re-add
        recentItems.remove("Item1");
        recentItems.add("Item1");
        System.out.println("Recent items: " + recentItems);
    }

    // ==================== 3. TREESET ====================
    static void demonstrateTreeSet() {
        System.out.println("\n=== TREESET ===");

        // TreeSet: Red-Black tree implementation, sorted order
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Adding elements - O(log n)
        treeSet.add(50);
        treeSet.add(20);
        treeSet.add(80);
        treeSet.add(10);
        treeSet.add(30);

        System.out.println("TreeSet (sorted): " + treeSet);

        // Contains and Remove - O(log n)
        System.out.println("Contains 30: " + treeSet.contains(30));
        treeSet.remove(30);
        System.out.println("After removal: " + treeSet);

        // NavigableSet operations - O(log n)
        System.out.println("First: " + treeSet.first());
        System.out.println("Last: " + treeSet.last());
        System.out.println("Lower than 50: " + treeSet.lower(50));
        System.out.println("Higher than 50: " + treeSet.higher(50));
        System.out.println("Floor of 45: " + treeSet.floor(45));
        System.out.println("Ceiling of 45: " + treeSet.ceiling(45));

        // Range operations
        System.out.println("HeadSet (<50): " + treeSet.headSet(50));
        System.out.println("TailSet (>=50): " + treeSet.tailSet(50));
        System.out.println("SubSet [20,80): " + treeSet.subSet(20, 80));

        // Custom comparator
        TreeSet<String> reverseSet = new TreeSet<>(Collections.reverseOrder());
        reverseSet.addAll(Arrays.asList("A", "C", "B", "D"));
        System.out.println("Reverse order: " + reverseSet);

        // Custom objects with Comparator
        TreeSet<Person> people = new TreeSet<>(Comparator.comparing(Person::getAge));
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));
        System.out.println("People by age: " + people);

        // Descending operations
        System.out.println("Descending: " + treeSet.descendingSet());

        // Poll operations (remove and return)
        System.out.println("Poll first: " + treeSet.pollFirst());
        System.out.println("Poll last: " + treeSet.pollLast());
        System.out.println("After polls: " + treeSet);
    }

    // ==================== 4. ENUMSET ====================
    static void demonstrateEnumSet() {
        System.out.println("\n=== ENUMSET ===");

        // EnumSet: Specialized Set for enum types, very efficient

        // Create with specific elements
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);
        System.out.println("Weekend: " + weekend);

        // Create with range
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("Weekdays: " + weekdays);

        // Create all elements
        EnumSet<Day> allDays = EnumSet.allOf(Day.class);
        System.out.println("All days: " + allDays);

        // Create none (empty)
        EnumSet<Day> noDays = EnumSet.noneOf(Day.class);
        System.out.println("No days: " + noDays);

        // Complement
        EnumSet<Day> notWeekend = EnumSet.complementOf(weekend);
        System.out.println("Not weekend: " + notWeekend);

        // Operations - all O(1)
        weekdays.add(Day.SATURDAY); // Adding to weekdays
        System.out.println("Modified weekdays: " + weekdays);

        System.out.println("Contains MONDAY: " + weekdays.contains(Day.MONDAY));
        weekdays.remove(Day.SATURDAY);

        // Set operations
        EnumSet<Day> workAndWeekend = EnumSet.copyOf(weekdays);
        workAndWeekend.addAll(weekend);
        System.out.println("Work + Weekend: " + workAndWeekend);

        // Iteration (in declaration order)
        System.out.println("Iteration (enum order):");
        for (Day day : allDays) {
            System.out.println("  " + day);
        }

        // Very memory efficient - uses bit vectors
        System.out.println("\nEnumSet is extremely memory efficient!");
        System.out.println("Uses bit vector internally (1 bit per enum value)");
    }

    // ==================== 5. SORTEDSET (Interface) ====================
    static void demonstrateSortedSet() {
        System.out.println("\n=== SORTEDSET (Interface) ===");

        // SortedSet: Interface guaranteeing sorted order
        // TreeSet implements SortedSet

        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.addAll(Arrays.asList("Dog", "Cat", "Elephant", "Bear", "Ant"));

        System.out.println("SortedSet: " + sortedSet);

        // SortedSet specific operations
        System.out.println("First: " + sortedSet.first());
        System.out.println("Last: " + sortedSet.last());

        // Get comparator (null for natural ordering)
        System.out.println("Comparator: " + sortedSet.comparator());

        // Range views
        SortedSet<String> headSet = sortedSet.headSet("Dog"); // Elements < "Dog"
        System.out.println("HeadSet (<Dog): " + headSet);

        SortedSet<String> tailSet = sortedSet.tailSet("Cat"); // Elements >= "Cat"
        System.out.println("TailSet (>=Cat): " + tailSet);

        SortedSet<String> subSet = sortedSet.subSet("Bear", "Elephant");
        System.out.println("SubSet [Bear,Elephant): " + subSet);

        // Custom comparator example
        SortedSet<String> lengthSorted = new TreeSet<>(
                Comparator.comparing(String::length).thenComparing(String::compareTo)
        );
        lengthSorted.addAll(Arrays.asList("A", "ABC", "AB", "ABCD", "B"));
        System.out.println("By length: " + lengthSorted);
    }

    // ==================== 6. NAVIGABLESET (Interface) ====================
    static void demonstrateNavigableSet() {
        System.out.println("\n=== NAVIGABLESET (Interface) ===");

        // NavigableSet: Extended SortedSet with navigation methods
        // TreeSet implements NavigableSet

        NavigableSet<Integer> navSet = new TreeSet<>();
        navSet.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90));

        System.out.println("NavigableSet: " + navSet);

        // Navigation methods
        System.out.println("Lower than 50 (< 50): " + navSet.lower(50));
        System.out.println("Floor of 50 (<= 50): " + navSet.floor(50));
        System.out.println("Ceiling of 55 (>= 55): " + navSet.ceiling(55));
        System.out.println("Higher than 50 (> 50): " + navSet.higher(50));

        // Poll operations (retrieve and remove)
        System.out.println("Poll first: " + navSet.pollFirst());
        System.out.println("Poll last: " + navSet.pollLast());
        System.out.println("After polls: " + navSet);

        // Descending views
        NavigableSet<Integer> descendingSet = navSet.descendingSet();
        System.out.println("Descending view: " + descendingSet);

        // Descending iterator
        System.out.println("Descending iteration:");
        Iterator<Integer> descIter = navSet.descendingIterator();
        while (descIter.hasNext()) {
            System.out.print(descIter.next() + " ");
        }
        System.out.println();

        // Extended range views with inclusive/exclusive bounds
        NavigableSet<Integer> subSet1 = navSet.subSet(30, true, 70, false);
        System.out.println("SubSet [30,70): " + subSet1);

        NavigableSet<Integer> headSet1 = navSet.headSet(50, true);
        System.out.println("HeadSet (<=50): " + headSet1);

        NavigableSet<Integer> tailSet1 = navSet.tailSet(50, false);
        System.out.println("TailSet (>50): " + tailSet1);
    }

    // ==================== PERFORMANCE COMPARISON ====================
    static void performanceComparison() {
        System.out.println("\n=== PERFORMANCE COMPARISON ===");

        int operations = 100000;

        // HashSet - Add
        long start = System.nanoTime();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < operations; i++) {
            hashSet.add(i);
        }
        long hashAddTime = System.nanoTime() - start;

        // LinkedHashSet - Add
        start = System.nanoTime();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        for (int i = 0; i < operations; i++) {
            linkedHashSet.add(i);
        }
        long linkedAddTime = System.nanoTime() - start;

        // TreeSet - Add
        start = System.nanoTime();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < operations; i++) {
            treeSet.add(i);
        }
        long treeAddTime = System.nanoTime() - start;

        System.out.println("Add " + operations + " elements:");
        System.out.printf("  HashSet: %.2f ms%n", hashAddTime / 1_000_000.0);
        System.out.printf("  LinkedHashSet: %.2f ms%n", linkedAddTime / 1_000_000.0);
        System.out.printf("  TreeSet: %.2f ms%n", treeAddTime / 1_000_000.0);

        // Contains
        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            hashSet.contains(i * 10);
        }
        long hashContainsTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            treeSet.contains(i * 10);
        }
        long treeContainsTime = System.nanoTime() - start;

        System.out.println("\nContains (10000 lookups):");
        System.out.printf("  HashSet: %.2f ms%n", hashContainsTime / 1_000_000.0);
        System.out.printf("  TreeSet: %.2f ms%n", treeContainsTime / 1_000_000.0);

        // Memory comparison
        System.out.println("\nMemory overhead (approximate per element):");
        System.out.println("  HashSet: ~32-36 bytes");
        System.out.println("  LinkedHashSet: ~40-48 bytes (+ links)");
        System.out.println("  TreeSet: ~40-48 bytes (+ tree pointers)");
        System.out.println("  EnumSet: ~1 bit per enum constant");
    }

    // ==================== SET OPERATIONS ====================
    static void demonstrateSetOperations() {
        System.out.println("\n=== SET OPERATIONS ===");

        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E", "F"));

        // Union
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);
        System.out.println("Union: " + union);

        // Intersection
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference (Set1 - Set2)
        Set<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (Set1-Set2): " + difference);

        // Symmetric Difference
        Set<String> symDiff = new HashSet<>(set1);
        symDiff.addAll(set2);
        Set<String> temp = new HashSet<>(set1);
        temp.retainAll(set2);
        symDiff.removeAll(temp);
        System.out.println("Symmetric Difference: " + symDiff);

        // Subset check
        Set<String> subset = new HashSet<>(Arrays.asList("A", "B"));
        System.out.println("Is {A,B} subset of Set1: " + set1.containsAll(subset));

        // Disjoint check
        Set<String> disjoint = new HashSet<>(Arrays.asList("X", "Y"));
        System.out.println("Are Set1 and {X,Y} disjoint: " +
                Collections.disjoint(set1, disjoint));
    }

    // Helper class
    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        int getAge() { return age; }

        @Override
        public String toString() {
            return name + "(" + age + ")";
        }
    }
}