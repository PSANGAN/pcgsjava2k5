package com.pcgs.core.java.pocs.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Comprehensive Java Map Implementation Guide
 * Demonstrates all major Map types with comparisons
 */
public class MapImplementationGuide {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   JAVA MAP IMPLEMENTATIONS - COMPLETE GUIDE       ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");

        // Run all demonstrations
        demonstrateHashMap();
        demonstrateLinkedHashMap();
        demonstrateTreeMap();
        demonstrateHashtable();
        demonstrateConcurrentHashMap();

        // Comparisons
        comparePerformance();
        demonstrateRealWorldUseCases();
        demonstrateCommonOperations();
        demonstrateIterationMethods();
        demonstrateMergingAndComputing();
    }

    // ========== 1. HASHMAP - Fast, Unordered ==========
    private static void demonstrateHashMap() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("1. HASHMAP - Fast, Unordered, Most Common");
        System.out.println("=".repeat(60));

        // Basic operations
        Map<String, Integer> studentGrades = new HashMap<>();

        // Adding entries
        studentGrades.put("Alice", 95);
        studentGrades.put("Bob", 87);
        studentGrades.put("Charlie", 92);
        studentGrades.put("Diana", 88);
        studentGrades.put("Alice", 98); // Updates existing key

        System.out.println("HashMap: " + studentGrades);
        System.out.println("Order: NOT GUARANTEED (hash-based)");

        // Get operations
        System.out.println("\nGet Operations:");
        System.out.println("Alice's grade: " + studentGrades.get("Alice"));
        System.out.println("Eve's grade: " + studentGrades.get("Eve")); // null
        System.out.println("Eve's grade (with default): " +
                studentGrades.getOrDefault("Eve", 0));

        // Check operations
        System.out.println("\nCheck Operations:");
        System.out.println("Contains key 'Bob': " + studentGrades.containsKey("Bob"));
        System.out.println("Contains value 92: " + studentGrades.containsValue(92));

        // Remove operations
        System.out.println("\nRemove Operations:");
        System.out.println("Removed Charlie: " + studentGrades.remove("Charlie"));
        System.out.println("After removal: " + studentGrades);

        // HashMap allows ONE null key and multiple null values
        studentGrades.put(null, 0);
        studentGrades.put("Unknown1", null);
        studentGrades.put("Unknown2", null);
        System.out.println("\nWith nulls: " + studentGrades);

        // Initial capacity and load factor
        Map<String, String> optimizedMap = new HashMap<>(16, 0.75f);
        System.out.println("\nHashMap can be initialized with capacity and load factor");
        System.out.println("Default capacity: 16, Default load factor: 0.75");

        // Key characteristics
        System.out.println("\n📋 HashMap Characteristics:");
        System.out.println("  ✓ Fastest general-purpose map");
        System.out.println("  ✓ O(1) average for get/put/remove");
        System.out.println("  ✓ Allows one null key");
        System.out.println("  ✓ Allows multiple null values");
        System.out.println("  ✓ NOT thread-safe");
        System.out.println("  ✓ NO ordering guarantee");
        System.out.println("  ✗ Not sorted");
    }

    // ========== 2. LINKEDHASHMAP - Ordered HashMap ==========
    private static void demonstrateLinkedHashMap() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("2. LINKEDHASHMAP - Maintains Insertion Order");
        System.out.println("=".repeat(60));

        // Insertion-order LinkedHashMap
        Map<String, Integer> insertionOrderMap = new LinkedHashMap<>();

        insertionOrderMap.put("First", 1);
        insertionOrderMap.put("Second", 2);
        insertionOrderMap.put("Third", 3);
        insertionOrderMap.put("Fourth", 4);

        System.out.println("LinkedHashMap (insertion order): " + insertionOrderMap);
        System.out.println("Order: INSERTION ORDER maintained");

        // Access-order LinkedHashMap (LRU Cache)
        System.out.println("\nAccess-Order LinkedHashMap (LRU Cache):");
        Map<String, String> lruCache = new LinkedHashMap<>(16, 0.75f, true);

        lruCache.put("Page1", "Data1");
        lruCache.put("Page2", "Data2");
        lruCache.put("Page3", "Data3");

        System.out.println("Initial: " + lruCache);

        // Access Page1 - moves it to end
        lruCache.get("Page1");
        System.out.println("After accessing Page1: " + lruCache);

        // LRU Cache Implementation
        System.out.println("\nLRU Cache Implementation:");
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("A", "Data A");
        cache.put("B", "Data B");
        cache.put("C", "Data C");
        System.out.println("Cache: " + cache);

        cache.get("A"); // Access A
        System.out.println("After accessing A: " + cache);

        cache.put("D", "Data D"); // Evicts B (least recently used)
        System.out.println("After adding D (evicts B): " + cache);

        // Key characteristics
        System.out.println("\n📋 LinkedHashMap Characteristics:");
        System.out.println("  ✓ Maintains insertion order (or access order)");
        System.out.println("  ✓ Slightly slower than HashMap");
        System.out.println("  ✓ Perfect for LRU cache");
        System.out.println("  ✓ Predictable iteration order");
        System.out.println("  ✓ Allows one null key");
        System.out.println("  ✗ NOT thread-safe");
        System.out.println("  ✗ Not sorted");
    }

    // LRU Cache Implementation
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true); // access-order
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }

    // ========== 3. TREEMAP - Sorted Map ==========
    private static void demonstrateTreeMap() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("3. TREEMAP - Sorted by Keys (Red-Black Tree)");
        System.out.println("=".repeat(60));

        // Natural ordering
        Map<String, Integer> sortedMap = new TreeMap<>();

        sortedMap.put("Zebra", 26);
        sortedMap.put("Apple", 1);
        sortedMap.put("Mango", 13);
        sortedMap.put("Banana", 2);
        sortedMap.put("Cherry", 3);

        System.out.println("TreeMap (natural order): " + sortedMap);
        System.out.println("Order: SORTED by keys (alphabetical)");

        // Custom comparator - reverse order
        Map<String, Integer> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        reverseMap.putAll(sortedMap);
        System.out.println("\nTreeMap (reverse order): " + reverseMap);

        // TreeMap with Integer keys
        TreeMap<Integer, String> numbers = new TreeMap<>();
        numbers.put(50, "Fifty");
        numbers.put(20, "Twenty");
        numbers.put(80, "Eighty");
        numbers.put(10, "Ten");
        numbers.put(40, "Forty");

        System.out.println("\nTreeMap with Integer keys: " + numbers);

        // Navigation methods
        System.out.println("\n🧭 Navigation Methods:");
        System.out.println("First key: " + numbers.firstKey());
        System.out.println("Last key: " + numbers.lastKey());
        System.out.println("Lower than 50: " + numbers.lowerKey(50));
        System.out.println("Higher than 50: " + numbers.higherKey(50));
        System.out.println("Floor (≤40): " + numbers.floorKey(40));
        System.out.println("Ceiling (≥40): " + numbers.ceilingKey(40));

        // Range operations
        System.out.println("\n📊 Range Operations:");
        System.out.println("HeadMap (<50): " + numbers.headMap(50));
        System.out.println("TailMap (≥50): " + numbers.tailMap(50));
        System.out.println("SubMap [20-80): " + numbers.subMap(20, 80));

        // Custom object sorting
        System.out.println("\n👤 Custom Object Sorting:");
        TreeMap<Employee, String> employees = new TreeMap<>(
                Comparator.comparingInt((Employee e) -> e.id)
        );

        employees.put(new Employee(103, "Alice"), "Engineering");
        employees.put(new Employee(101, "Bob"), "Sales");
        employees.put(new Employee(102, "Charlie"), "Marketing");

        System.out.println("Employees sorted by ID:");
        employees.forEach((emp, dept) ->
                System.out.println("  " + emp + " -> " + dept));

        // TreeMap does NOT allow null keys
        try {
            sortedMap.put(null, 100);
        } catch (NullPointerException e) {
            System.out.println("\n⚠️  TreeMap does NOT allow null keys!");
        }

        // Key characteristics
        System.out.println("\n📋 TreeMap Characteristics:");
        System.out.println("  ✓ Keys sorted (natural or custom order)");
        System.out.println("  ✓ O(log n) for get/put/remove");
        System.out.println("  ✓ Navigation methods (first, last, lower, higher)");
        System.out.println("  ✓ Range view operations");
        System.out.println("  ✗ Slower than HashMap");
        System.out.println("  ✗ NO null keys");
        System.out.println("  ✗ NOT thread-safe");
    }

    static class Employee {
        int id;
        String name;

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "'}";
        }
    }

    // ========== 4. HASHTABLE - Legacy, Synchronized ==========
    private static void demonstrateHashtable() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("4. HASHTABLE - Legacy, Synchronized (AVOID IN NEW CODE)");
        System.out.println("=".repeat(60));

        Map<String, Integer> hashtable = new Hashtable<>();

        hashtable.put("One", 1);
        hashtable.put("Two", 2);
        hashtable.put("Three", 3);

        System.out.println("Hashtable: " + hashtable);

        // Hashtable does NOT allow null keys or values
        try {
            hashtable.put(null, 100);
        } catch (NullPointerException e) {
            System.out.println("\n⚠️  Hashtable does NOT allow null keys!");
        }

        try {
            hashtable.put("Null", null);
        } catch (NullPointerException e) {
            System.out.println("⚠️  Hashtable does NOT allow null values!");
        }

        // Key characteristics
        System.out.println("\n📋 Hashtable Characteristics:");
        System.out.println("  ✓ Thread-safe (all methods synchronized)");
        System.out.println("  ✗ LEGACY class (since Java 1.0)");
        System.out.println("  ✗ Slower than HashMap (synchronization overhead)");
        System.out.println("  ✗ NO null keys or values");
        System.out.println("  ✗ Use ConcurrentHashMap instead for thread-safety!");

        System.out.println("\n⚠️  RECOMMENDATION: Don't use Hashtable in new code!");
        System.out.println("   Use ConcurrentHashMap for thread-safety");
        System.out.println("   Use Collections.synchronizedMap(new HashMap<>()) if needed");
    }

    // ========== 5. CONCURRENTHASHMAP - Thread-Safe, High Performance ==========
    private static void demonstrateConcurrentHashMap() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("5. CONCURRENTHASHMAP - Thread-Safe, High Performance");
        System.out.println("=".repeat(60));

        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put("User1", 100);
        concurrentMap.put("User2", 200);
        concurrentMap.put("User3", 300);

        System.out.println("ConcurrentHashMap: " + concurrentMap);

        // Thread-safe operations
        System.out.println("\n🔒 Thread-Safe Operations:");
        concurrentMap.putIfAbsent("User4", 400);
        System.out.println("After putIfAbsent: " + concurrentMap);

        concurrentMap.replace("User1", 100, 150);
        System.out.println("After replace: " + concurrentMap);

        // Atomic compute operations
        concurrentMap.compute("User1", (key, value) -> value + 10);
        System.out.println("After compute: " + concurrentMap);

        concurrentMap.merge("User1", 5, Integer::sum);
        System.out.println("After merge: " + concurrentMap);

        // Key characteristics
        System.out.println("\n📋 ConcurrentHashMap Characteristics:");
        System.out.println("  ✓ Thread-safe WITHOUT locking entire map");
        System.out.println("  ✓ High concurrency (segment-based locking)");
        System.out.println("  ✓ Better performance than Hashtable");
        System.out.println("  ✓ Atomic operations (putIfAbsent, compute, merge)");
        System.out.println("  ✗ NO null keys or values");
        System.out.println("  ✗ Slightly slower than HashMap (thread-safety overhead)");
    }

    // ========== PERFORMANCE COMPARISON ==========
    private static void comparePerformance() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("⚡ PERFORMANCE COMPARISON");
        System.out.println("=".repeat(60));

        int iterations = 100000;

        // HashMap performance
        long start = System.nanoTime();
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < iterations; i++) {
            hashMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            hashMap.get(i);
        }
        long hashMapTime = System.nanoTime() - start;

        // LinkedHashMap performance
        start = System.nanoTime();
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < iterations; i++) {
            linkedHashMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedHashMap.get(i);
        }
        long linkedHashMapTime = System.nanoTime() - start;

        // TreeMap performance
        start = System.nanoTime();
        Map<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < iterations; i++) {
            treeMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            treeMap.get(i);
        }
        long treeMapTime = System.nanoTime() - start;

        System.out.println(String.format("HashMap:        %d ms ⚡ (Fastest)", hashMapTime / 1_000_000));
        System.out.println(String.format("LinkedHashMap:  %d ms", linkedHashMapTime / 1_000_000));
        System.out.println(String.format("TreeMap:        %d ms 🐌 (Slowest - O(log n))", treeMapTime / 1_000_000));

        System.out.println("\n📊 Time Complexity Summary:");
        System.out.println("╔═══════════════════╦════════╦═════════╦═══════════╗");
        System.out.println("║ Operation         ║ HashMap║ TreeMap ║ LinkedHashMap ║");
        System.out.println("╠═══════════════════╬════════╬═════════╬═══════════╣");
        System.out.println("║ get(key)          ║ O(1)   ║ O(log n)║ O(1)      ║");
        System.out.println("║ put(key, value)   ║ O(1)   ║ O(log n)║ O(1)      ║");
        System.out.println("║ remove(key)       ║ O(1)   ║ O(log n)║ O(1)      ║");
        System.out.println("║ containsKey(key)  ║ O(1)   ║ O(log n)║ O(1)      ║");
        System.out.println("╚═══════════════════╩════════╩═════════╩═══════════╝");
    }

    // ========== REAL-WORLD USE CASES ==========
    private static void demonstrateRealWorldUseCases() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🌍 REAL-WORLD USE CASES");
        System.out.println("=".repeat(60));

        // Use Case 1: Word Frequency Counter (HashMap)
        System.out.println("\n1️⃣  Word Frequency Counter (HashMap):");
        String text = "the quick brown fox jumps over the lazy dog the fox";
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : text.split(" ")) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("Word frequencies: " + wordCount);

        // Use Case 2: User Session Management (LinkedHashMap)
        System.out.println("\n2️⃣  User Session Management (LinkedHashMap - LRU):");
        LRUCache<String, String> sessionCache = new LRUCache<>(3);
        sessionCache.put("session1", "user1");
        sessionCache.put("session2", "user2");
        sessionCache.put("session3", "user3");
        System.out.println("Sessions: " + sessionCache);

        sessionCache.get("session1"); // Keep session1 active
        sessionCache.put("session4", "user4"); // Evicts session2
        System.out.println("After new session: " + sessionCache);

        // Use Case 3: Leaderboard (TreeMap)
        System.out.println("\n3️⃣  Game Leaderboard (TreeMap - sorted by score):");
        TreeMap<Integer, String> leaderboard = new TreeMap<>(Comparator.reverseOrder());
        leaderboard.put(1500, "Player1");
        leaderboard.put(2000, "Player2");
        leaderboard.put(1800, "Player3");
        leaderboard.put(2200, "Player4");

        System.out.println("Top players:");
        leaderboard.forEach((score, player) ->
                System.out.println("  " + player + ": " + score));

        // Use Case 4: Configuration Properties (HashMap)
        System.out.println("\n4️⃣  Application Configuration (HashMap):");
        Map<String, String> config = new HashMap<>();
        config.put("database.url", "jdbc:mysql://localhost:3306/mydb");
        config.put("database.user", "admin");
        config.put("max.connections", "100");
        config.put("timeout", "30");

        System.out.println("Database URL: " + config.get("database.url"));
        System.out.println("Timeout: " + config.getOrDefault("timeout", "60") + "s");

        // Use Case 5: Character Position Mapping (HashMap)
        System.out.println("\n5️⃣  Character Positions (HashMap):");
        String str = "programming";
        Map<Character, List<Integer>> charPositions = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charPositions.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        System.out.println("Character positions in '" + str + "':");
        charPositions.forEach((ch, positions) ->
                System.out.println("  '" + ch + "' at positions: " + positions));
    }

    // ========== COMMON OPERATIONS ==========
    private static void demonstrateCommonOperations() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔧 COMMON MAP OPERATIONS");
        System.out.println("=".repeat(60));

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        // 1. putIfAbsent
        System.out.println("\n1. putIfAbsent (Java 8+):");
        map.putIfAbsent("D", 4); // Adds
        map.putIfAbsent("A", 10); // Doesn't replace existing
        System.out.println("After putIfAbsent: " + map);

        // 2. computeIfAbsent
        System.out.println("\n2. computeIfAbsent:");
        map.computeIfAbsent("E", key -> key.length());
        System.out.println("After computeIfAbsent: " + map);

        // 3. computeIfPresent
        System.out.println("\n3. computeIfPresent:");
        map.computeIfPresent("A", (key, value) -> value * 10);
        System.out.println("After computeIfPresent: " + map);

        // 4. compute
        System.out.println("\n4. compute:");
        map.compute("B", (key, value) -> value == null ? 1 : value + 1);
        System.out.println("After compute: " + map);

        // 5. merge
        System.out.println("\n5. merge:");
        map.merge("A", 5, Integer::sum); // Adds 5 to existing value
        map.merge("F", 6, Integer::sum); // Creates new entry
        System.out.println("After merge: " + map);

        // 6. replace
        System.out.println("\n6. replace:");
        map.replace("A", 100);
        map.replace("B", 3, 200); // Only if current value is 3
        System.out.println("After replace: " + map);

        // 7. replaceAll
        System.out.println("\n7. replaceAll:");
        map.replaceAll((key, value) -> value * 2);
        System.out.println("After replaceAll: " + map);
    }

    // ========== ITERATION METHODS ==========
    private static void demonstrateIterationMethods() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔄 MAP ITERATION METHODS");
        System.out.println("=".repeat(60));

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Cherry", 30);

        // Method 1: Using entrySet()
        System.out.println("\n1️⃣  Using entrySet() - RECOMMENDED:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        // Method 2: Using keySet()
        System.out.println("\n2️⃣  Using keySet():");
        for (String key : map.keySet()) {
            System.out.println("  " + key + " = " + map.get(key));
        }

        // Method 3: Using values()
        System.out.println("\n3️⃣  Using values():");
        for (Integer value : map.values()) {
            System.out.println("  Value: " + value);
        }

        // Method 4: Using forEach (Java 8+)
        System.out.println("\n4️⃣  Using forEach (Java 8+) - MODERN WAY:");
        map.forEach((key, value) ->
                System.out.println("  " + key + " = " + value));

        // Method 5: Using Stream API
        System.out.println("\n5️⃣  Using Stream API:");
        map.entrySet().stream()
                .filter(entry -> entry.getValue() > 15)
                .forEach(entry -> System.out.println("  " + entry));
    }

    // ========== MERGING AND COMPUTING ==========
    private static void demonstrateMergingAndComputing() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔬 ADVANCED: MERGING & COMPUTING");
        System.out.println("=".repeat(60));

        // Combining two maps
        System.out.println("\n1️⃣  Combining Two Maps:");
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 3);
        map2.put("C", 4);

        System.out.println("Map 1: " + map1);
        System.out.println("Map 2: " + map2);

        // Merge map2 into map1
        map2.forEach((key, value) ->
                map1.merge(key, value, Integer::sum));

        System.out.println("After merging (sum conflicts): " + map1);

        // Group by and count
        System.out.println("\n2️⃣  Group By and Count:");
        List<String> words = Arrays.asList("apple", "banana", "apple",
                "cherry", "banana", "apple");
        Map<String, Long> wordCount = new HashMap<>();

        words.forEach(word ->
                wordCount.merge(word, 1L, Long::sum));

        System.out.println("Word count: " + wordCount);

        // Computing values
        System.out.println("\n3️⃣  Computing Values:");
        Map<String, List<Integer>> groups = new HashMap<>();

        groups.computeIfAbsent("even", k -> new ArrayList<>()).add(2);
        groups.computeIfAbsent("odd", k -> new ArrayList<>()).add(1);
        groups.computeIfAbsent("even", k -> new ArrayList<>()).add(4);

        System.out.println("Groups: " + groups);
    }
}
