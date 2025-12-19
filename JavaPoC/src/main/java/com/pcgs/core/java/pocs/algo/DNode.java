package com.pcgs.core.java.pocs.algo;

class DNode {
    int data;           // Data stored in the node
    DNode next;         // Pointer to next node
    DNode prev;         // Pointer to previous node

    // Constructor
    public DNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
