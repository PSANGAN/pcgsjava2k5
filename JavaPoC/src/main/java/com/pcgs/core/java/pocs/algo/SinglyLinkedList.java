package com.pcgs.core.java.pocs.algo;


public class SinglyLinkedList {
    Node head;  // Pointer to the first node

    // Constructor
    public SinglyLinkedList() {
        this.head = null;
    }

    // PREPEND: Add node at the beginning - O(1)
    public void prepend(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            // List is empty
            head = newNode;
        } else {
            // Point new node to current head
            newNode.next = head;
            // Update head to new node
            head = newNode;
        }
        System.out.println("Prepended: " + data);
    }

    // APPEND: Add node at the end - O(n)
    public void append(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            // List is empty
            head = newNode;
            System.out.println("Appended: " + data);
            return;
        }

        // Traverse to the last node
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Link last node to new node
        current.next = newNode;
        System.out.println("Appended: " + data);
    }

    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;
        System.out.print("List: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" → ");
            }
            current = current.next;
        }
        System.out.println(" → null");
    }

    // Delete a node by value
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // If head node needs to be deleted
        if (head.data == data) {
            head = head.next;
            System.out.println("Deleted: " + data);
            return;
        }

        // Search for the node to delete
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Node not found");
        } else {
            // Remove the node by skipping it
            current.next = current.next.next;
            System.out.println("Deleted: " + data);
        }
    }

    // REVERSE - ITERATIVE APPROACH (Recommended) - O(n) time, O(1) space
    public void reverseIterative() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            // Store next node
            next = current.next;

            // Reverse the link
            current.next = prev;

            // Move pointers forward
            prev = current;
            current = next;
        }

        // Update head to the last node (now first)
        head = prev;
        System.out.println("Reversed (Iterative)");
    }

    // REVERSE - RECURSIVE APPROACH - O(n) time, O(n) space (call stack)
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);
        System.out.println("Reversed (Recursive)");
    }

    private Node reverseRecursiveHelper(Node node) {
        // Base case: empty list or last node
        if (node == null || node.next == null) {
            return node;
        }

        // Recursively reverse the rest of the list
        Node newHead = reverseRecursiveHelper(node.next);

        // Reverse the link
        node.next.next = node;
        node.next = null;

        return newHead;
    }
}

