package com.pcgs.core.java.pocs.collections;

import java.util.*;

/**
 * Comprehensive Guide to Java List Implementations
 * Demonstrates ArrayList, LinkedList, Vector, Stack, AbstractList, AbstractSequentialList
 */
public class ListImplementationsGuide {

    public static void main(String[] args) {
        demonstrateArrayList();
        demonstrateLinkedList();
        demonstrateVector();
        demonstrateStack();
        demonstrateAbstractList();
        demonstrateAbstractSequentialList();
        performanceComparison();
    }

    // ==================== 1. ARRAYLIST ====================
    static void demonstrateArrayList() {
        System.out.println("\n=== ARRAYLIST ===");

        // ArrayList: Resizable array implementation
        ArrayList<String> arrayList = new ArrayList<>();

        // Adding elements - O(1) amortized
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Avocado"); // Insert at index - O(n)

        // Accessing elements - O(1)
        System.out.println("Element at index 2: " + arrayList.get(2));

        // Updating - O(1)
        arrayList.set(0, "Apricot");

        // Removing - O(n)
        arrayList.remove("Banana");
        arrayList.remove(0);

        // Size and capacity
        System.out.println("Size: " + arrayList.size());
        arrayList.trimToSize(); // Trim capacity to current size

        // Iteration
        for (String fruit : arrayList) {
            System.out.println(fruit);
        }

        // Initial capacity
        ArrayList<Integer> listWithCapacity = new ArrayList<>(100);
    }

    // ==================== 2. LINKEDLIST ====================
    static void demonstrateLinkedList() {
        System.out.println("\n=== LINKEDLIST ===");

        // LinkedList: Doubly-linked list implementation
        LinkedList<String> linkedList = new LinkedList<>();

        // Adding elements - O(1) at ends, O(n) in middle
        linkedList.add("First");
        linkedList.addFirst("Start");  // O(1)
        linkedList.addLast("End");     // O(1)
        linkedList.add(1, "Second");   // O(n)

        // Accessing elements - O(n)
        System.out.println("First: " + linkedList.getFirst());
        System.out.println("Last: " + linkedList.getLast());
        System.out.println("At index 2: " + linkedList.get(2));

        // Removing - O(1) at ends, O(n) in middle
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.remove(0);

        // Queue operations
        linkedList.offer("Queue Element");    // Add to end
        System.out.println("Poll: " + linkedList.poll()); // Remove from front

        // Deque operations
        linkedList.push("Stack Element");     // Add to front
        System.out.println("Pop: " + linkedList.pop()); // Remove from front

        // Peek operations
        linkedList.add("Peek This");
        System.out.println("Peek: " + linkedList.peek()); // View without removing
    }

    // ==================== 3. VECTOR ====================
    static void demonstrateVector() {
        System.out.println("\n=== VECTOR ===");

        // Vector: Synchronized resizable array (legacy, thread-safe)
        Vector<String> vector = new Vector<>();

        // All methods are synchronized
        vector.add("Element1");
        vector.addElement("Element2"); // Legacy method
        vector.add("Element3");

        // Accessing
        System.out.println("First element: " + vector.firstElement());
        System.out.println("Last element: " + vector.lastElement());
        System.out.println("Element at 1: " + vector.elementAt(1));

        // Capacity management
        System.out.println("Capacity: " + vector.capacity());
        System.out.println("Size: " + vector.size());
        vector.ensureCapacity(50);

        // Vector doubles capacity by default when full
        Vector<Integer> vectorWithIncrement = new Vector<>(10, 5); // initial capacity, increment

        // Enumeration (legacy)
        Enumeration<String> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

    // ==================== 4. STACK ====================
    static void demonstrateStack() {
        System.out.println("\n=== STACK ===");

        // Stack: LIFO data structure, extends Vector
        Stack<String> stack = new Stack<>();

        // Push elements - O(1)
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");

        // Peek without removing - O(1)
        System.out.println("Peek: " + stack.peek());

        // Pop elements - O(1)
        System.out.println("Pop: " + stack.pop());
        System.out.println("Pop: " + stack.pop());

        // Search (returns position from top, 1-based)
        stack.push("Element1");
        stack.push("Element2");
        System.out.println("Search 'Bottom': " + stack.search("Bottom")); // Distance from top

        // Check if empty
        System.out.println("Is empty: " + stack.empty());

        // Note: Deque is preferred over Stack in modern Java
        Deque<String> modernStack = new ArrayDeque<>();
        modernStack.push("Better Stack");
    }

    // ==================== 5. ABSTRACTLIST ====================
    static void demonstrateAbstractList() {
        System.out.println("\n=== ABSTRACTLIST ===");

        // AbstractList: Skeletal implementation for random access lists
        // Custom implementation example
        class SimpleList extends AbstractList<String> {
            private String[] elements;
            private int size;

            public SimpleList(int capacity) {
                elements = new String[capacity];
                size = 0;
            }

            @Override
            public String get(int index) {
                if (index >= size) throw new IndexOutOfBoundsException();
                return elements[index];
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public boolean add(String element) {
                if (size < elements.length) {
                    elements[size++] = element;
                    return true;
                }
                return false;
            }

            @Override
            public String set(int index, String element) {
                if (index >= size) throw new IndexOutOfBoundsException();
                String old = elements[index];
                elements[index] = element;
                return old;
            }
        }

        SimpleList customList = new SimpleList(10);
        customList.add("Custom1");
        customList.add("Custom2");
        customList.add("Custom3");

        System.out.println("Custom list element: " + customList.get(1));
        System.out.println("Custom list size: " + customList.size());

        // AbstractList provides: iterator, indexOf, lastIndexOf, etc.
        System.out.println("Index of Custom2: " + customList.indexOf("Custom2"));
    }

    // ==================== 6. ABSTRACTSEQUENTIALLIST ====================
    static void demonstrateAbstractSequentialList() {
        System.out.println("\n=== ABSTRACTSEQUENTIALLIST ===");

        // AbstractSequentialList: Skeletal implementation for sequential access lists
        // LinkedList extends this class
        // Custom implementation example
        class SimpleLinkedList extends AbstractSequentialList<String> {
            private Node head;
            private int size;

            private class Node {
                String data;
                Node next;
                Node prev;

                Node(String data) {
                    this.data = data;
                }
            }

            @Override
            public ListIterator<String> listIterator(int index) {
                return new ListIterator<String>() {
                    private Node current = getNode(index);
                    private int cursor = index;

                    private Node getNode(int idx) {
                        if (idx == size) return null;
                        Node n = head;
                        for (int i = 0; i < idx; i++) {
                            n = n.next;
                        }
                        return n;
                    }

                    @Override
                    public boolean hasNext() {
                        return cursor < size;
                    }

                    @Override
                    public String next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        if (current == null) current = head;
                        String data = current.data;
                        current = current.next;
                        cursor++;
                        return data;
                    }

                    @Override
                    public boolean hasPrevious() {
                        return cursor > 0;
                    }

                    @Override
                    public String previous() {
                        if (!hasPrevious()) throw new NoSuchElementException();
                        current = (current == null) ? getLast() : current.prev;
                        cursor--;
                        return current.data;
                    }

                    private Node getLast() {
                        Node n = head;
                        while (n.next != null) n = n.next;
                        return n;
                    }

                    @Override
                    public int nextIndex() { return cursor; }

                    @Override
                    public int previousIndex() { return cursor - 1; }

                    @Override
                    public void remove() { throw new UnsupportedOperationException(); }

                    @Override
                    public void set(String s) { throw new UnsupportedOperationException(); }

                    @Override
                    public void add(String s) { throw new UnsupportedOperationException(); }
                };
            }

            @Override
            public int size() {
                return size;
            }

            @Override
            public void add(int index, String element) {
                Node newNode = new Node(element);
                if (head == null) {
                    head = newNode;
                } else {
                    Node current = head;
                    for (int i = 0; i < index - 1 && current.next != null; i++) {
                        current = current.next;
                    }
                    newNode.next = current.next;
                    if (current.next != null) current.next.prev = newNode;
                    current.next = newNode;
                    newNode.prev = current;
                }
                size++;
            }
        }

        SimpleLinkedList seqList = new SimpleLinkedList();
        seqList.add("Sequential1");
        seqList.add("Sequential2");
        seqList.add("Sequential3");

        System.out.println("Sequential list size: " + seqList.size());
        for (String item : seqList) {
            System.out.println(item);
        }
    }

    // ==================== PERFORMANCE COMPARISON ====================
    static void performanceComparison() {
        System.out.println("\n=== PERFORMANCE COMPARISON ===");

        int operations = 10000;

        // ArrayList vs LinkedList - Add at end
        long start = System.nanoTime();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < operations; i++) al.add(i);
        long alAddTime = System.nanoTime() - start;

        start = System.nanoTime();
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < operations; i++) ll.add(i);
        long llAddTime = System.nanoTime() - start;

        System.out.println("Add at end (" + operations + " elements):");
        System.out.println("  ArrayList: " + alAddTime / 1000000.0 + " ms");
        System.out.println("  LinkedList: " + llAddTime / 1000000.0 + " ms");

        // Random access
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) al.get(i * 10);
        long alGetTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) ll.get(i * 10);
        long llGetTime = System.nanoTime() - start;

        System.out.println("\nRandom access (1000 gets):");
        System.out.println("  ArrayList: " + alGetTime / 1000000.0 + " ms");
        System.out.println("  LinkedList: " + llGetTime / 1000000.0 + " ms");

        // Insert at beginning
        start = System.nanoTime();
        for (int i = 0; i < 100; i++) al.add(0, i);
        long alInsertTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 100; i++) ll.addFirst(i);
        long llInsertTime = System.nanoTime() - start;

        System.out.println("\nInsert at beginning (100 elements):");
        System.out.println("  ArrayList: " + alInsertTime / 1000000.0 + " ms");
        System.out.println("  LinkedList: " + llInsertTime / 1000000.0 + " ms");
    }
}