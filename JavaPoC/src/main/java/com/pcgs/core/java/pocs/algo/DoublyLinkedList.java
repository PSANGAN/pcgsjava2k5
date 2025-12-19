package com.pcgs.core.java.pocs.algo;

public class DoublyLinkedList {
    DNode head;  // Pointer to the first node
    DNode tail;  // Pointer to the last node (optional but useful)

    // Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // PREPEND: Add node at the beginning - O(1)
    public void prepend(int data) {
        DNode newNode = new DNode(data);

        if (head == null) {
            // List is empty
            head = newNode;
            tail = newNode;
        } else {
            // Link new node to current head
            newNode.next = head;
            head.prev = newNode;
            // Update head
            head = newNode;
        }
        System.out.println("Prepended: " + data);
    }

    // APPEND: Add node at the end - O(1) with tail pointer
    public void append(int data) {
        DNode newNode = new DNode(data);

        if (head == null) {
            // List is empty
            head = newNode;
            tail = newNode;
        } else {
            // Link tail to new node
            tail.next = newNode;
            newNode.prev = tail;
            // Update tail
            tail = newNode;
        }
        System.out.println("Appended: " + data);
    }

    // Display forward (head to tail)
    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        DNode current = head;
        System.out.print("Forward:  null ← ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" ↔ ");
            }
            current = current.next;
        }
        System.out.println(" → null");
    }

    // Display backward (tail to head)
    public void displayBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }

        DNode current = tail;
        System.out.print("Backward: null → ");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" ↔ ");
            }
            current = current.prev;
        }
        System.out.println(" ← null");
    }

    // Delete a node by value
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        DNode current = head;

        // Search for the node
        while (current != null && current.data != data) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node not found");
            return;
        }

        // Node found, now remove it
        if (current.prev != null) {
            // Not the head node
            current.prev.next = current.next;
        } else {
            // Head node
            head = current.next;
        }

        if (current.next != null) {
            // Not the tail node
            current.next.prev = current.prev;
        } else {
            // Tail node
            tail = current.prev;
        }

        System.out.println("Deleted: " + data);
    }

    // Insert after a specific value
    public void insertAfter(int afterData, int newData) {
        DNode current = head;

        // Find the node with afterData
        while (current != null && current.data != afterData) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node with value " + afterData + " not found");
            return;
        }

        DNode newNode = new DNode(newData);
        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            // Inserting at the end
            tail = newNode;
        }

        current.next = newNode;
        System.out.println("Inserted " + newData + " after " + afterData);
    }

    // REVERSE - ITERATIVE APPROACH - O(n) time, O(1) space
    public void reverseIterative() {
        DNode current = head;
        DNode temp = null;

        // Swap next and prev for all nodes
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev; // Move to next (which is now in prev)
        }

        // Swap head and tail
        temp = head;
        head = tail;
        tail = temp;

        System.out.println("Reversed (Iterative)");
    }

    // REVERSE - RECURSIVE APPROACH - O(n) time, O(n) space
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);

        // Update tail
        tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }

        System.out.println("Reversed (Recursive)");
    }

    private DNode reverseRecursiveHelper(DNode node) {
        // Base case
        if (node == null) return null;

        // Swap next and prev
        DNode temp = node.prev;
        node.prev = node.next;
        node.next = temp;

        // If this was the last node, it's now the head
        if (node.prev == null) {
            return node;
        }

        // Recurse
        return reverseRecursiveHelper(node.prev);
    }
}