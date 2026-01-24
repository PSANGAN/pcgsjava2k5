package com.pcgs.core.java.pocs.collections.hashcollections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapComparison {

    public static void main(String[] args) {
        System.out.println("=== HASHMAP DEMO ===");
        demonstrateHashMap();

        System.out.println("\n=== HASHTABLE DEMO ===");
        demonstrateHashtable();

        System.out.println("\n=== LINKEDHASHMAP DEMO ===");
        demonstrateLinkedHashMap();

        System.out.println("\n=== PERFORMANCE COMPARISON ===");
        performanceComparison();
    }

    // 1. HashMap - Most commonly used, non-synchronized, allows null
    private static void demonstrateHashMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Adding elements
        hashMap.put("Apple", 100);
        hashMap.put("Banana", 80);
        hashMap.put("Cherry", 120);
        hashMap.put(null, 50);  // Allows null key
        hashMap.put("Date", null);  // Allows null value

        System.out.println("HashMap: " + hashMap);

        // Order is not guaranteed
        System.out.print("Iteration order: ");
        for (String key : hashMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Not thread-safe
        System.out.println("Thread-safe: No");
        System.out.println("Null key allowed: Yes");
        System.out.println("Null value allowed: Yes");
        System.out.println("Iteration order: Unpredictable");
    }

    // 2. Hashtable - Legacy class, synchronized, no null
    private static void demonstrateHashtable() {
        Hashtable<String, Integer> hashtable = new Hashtable<>();

        // Adding elements
        hashtable.put("Apple", 100);
        hashtable.put("Banana", 80);
        hashtable.put("Cherry", 120);

        // hashtable.put(null, 50);  // Would throw NullPointerException
        // hashtable.put("Date", null);  // Would throw NullPointerException

        System.out.println("Hashtable: " + hashtable);

        System.out.println("Thread-safe: Yes (synchronized methods)");
        System.out.println("Null key allowed: No");
        System.out.println("Null value allowed: No");
        System.out.println("Iteration order: Unpredictable");
        System.out.println("Note: Legacy class, prefer ConcurrentHashMap");
    }

    // 3. LinkedHashMap - Maintains insertion order
    private static void demonstrateLinkedHashMap() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Adding elements
        linkedHashMap.put("Apple", 100);
        linkedHashMap.put("Banana", 80);
        linkedHashMap.put("Cherry", 120);
        linkedHashMap.put(null, 50);  // Allows null key
        linkedHashMap.put("Date", null);  // Allows null value

        System.out.println("LinkedHashMap: " + linkedHashMap);

        // Maintains insertion order
        System.out.print("Iteration order (insertion): ");
        for (String key : linkedHashMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Access-order LinkedHashMap (LRU cache pattern)
        LinkedHashMap<String, Integer> lruMap = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > 3;  // Keep only 3 most recent entries
            }
        };

        lruMap.put("A", 1);
        lruMap.put("B", 2);
        lruMap.put("C", 3);
        System.out.println("\nLRU Cache before access: " + lruMap);

        lruMap.get("A");  // Access A, making it most recent
        lruMap.put("D", 4);  // This will remove B (least recently used)
        System.out.println("LRU Cache after accessing A and adding D: " + lruMap);

        System.out.println("\nThread-safe: No");
        System.out.println("Null key allowed: Yes");
        System.out.println("Null value allowed: Yes");
        System.out.println("Iteration order: Insertion or access order");
    }

    // Performance comparison
    private static void performanceComparison() {
        int size = 100000;

        // HashMap performance
        long start = System.nanoTime();
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapTime = System.nanoTime() - start;

        // Hashtable performance
        start = System.nanoTime();
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        for (int i = 0; i < size; i++) {
            hashtable.put(i, "Value" + i);
        }
        long hashtableTime = System.nanoTime() - start;

        // LinkedHashMap performance
        start = System.nanoTime();
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(i, "Value" + i);
        }
        long linkedHashMapTime = System.nanoTime() - start;

        System.out.println("Time to insert " + size + " elements:");
        System.out.println("HashMap: " + hashMapTime / 1_000_000 + " ms");
        System.out.println("Hashtable: " + hashtableTime / 1_000_000 + " ms");
        System.out.println("LinkedHashMap: " + linkedHashMapTime / 1_000_000 + " ms");
    }
}

/*
 * DETAILED COMPARISON:
 *
 * ┌─────────────────┬──────────────┬──────────────┬──────────────────┐
 * │   Feature       │   HashMap    │  Hashtable   │  LinkedHashMap   │
 * ├─────────────────┼──────────────┼──────────────┼──────────────────┤
 * │ Thread-safe     │ No           │ Yes          │ No               │
 * │ Synchronized    │ No           │ Yes          │ No               │
 * │ Null key        │ One allowed  │ Not allowed  │ One allowed      │
 * │ Null values     │ Allowed      │ Not allowed  │ Allowed          │
 * │ Iteration order │ Random       │ Random       │ Insertion/Access │
 * │ Performance     │ Fast         │ Slower       │ Slightly slower  │
 * │ Since version   │ Java 1.2     │ Java 1.0     │ Java 1.4         │
 * │ Inheritance     │ AbstractMap  │ Dictionary   │ HashMap          │
 * └─────────────────┴──────────────┴──────────────┴──────────────────┘
 *
 * WHEN TO USE:
 *
 * HashMap:
 * - Default choice for most scenarios
 * - Single-threaded applications
 * - When order doesn't matter
 * - Need null keys/values
 * - Best performance
 *
 * Hashtable:
 * - Legacy code maintenance only
 * - Don't use in new code
 * - Use ConcurrentHashMap instead for thread-safety
 *
 * LinkedHashMap:
 * - Need predictable iteration order
 * - Implementing LRU cache
 * - Maintaining insertion order is important
 * - Debugging (predictable output)
 *
 * LIMITATIONS:
 *
 * HashMap:
 * - Not thread-safe (use Collections.synchronizedMap() or ConcurrentHashMap)
 * - No ordering guarantee
 * - Performance degrades with poor hashCode() implementation
 * - Initial capacity and load factor affect performance
 *
 * Hashtable:
 * - Synchronization overhead even in single-threaded apps
 * - No null keys or values
 * - Legacy class (obsolete)
 * - Locks entire table for operations
 *
 * LinkedHashMap:
 * - Higher memory overhead (doubly-linked list)
 * - Slightly slower than HashMap
 * - Not thread-safe
 * - More complex internal structure
 *
 * THREAD-SAFE ALTERNATIVES:
 * - ConcurrentHashMap (preferred for concurrent access)
 * - Collections.synchronizedMap(new HashMap<>())
 * - Collections.synchronizedMap(new LinkedHashMap<>())
 */
