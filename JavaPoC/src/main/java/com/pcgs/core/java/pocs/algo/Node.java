package com.pcgs.core.java.pocs.algo;

public class Node {
    int data;        // Data stored in the node
    Node next;       // Pointer/reference to next node

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
