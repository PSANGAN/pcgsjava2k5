package com.pcgs.core.java.pocs.collections;

class Node {
    int data;
    Node prev;
    Node next;

    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Insert at the beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Insert at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Insert at specific position (0-indexed)
    public void insertAtPosition(int data, int pos) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (pos == 0) {
            insertAtBeginning(data);
            return;
        }
        if (pos == size) {
            insertAtEnd(data);
            return;
        }

        Node newNode = new Node(data);
        Node curr = head;
        for (int i = 0; i < pos - 1; i++) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        newNode.prev = curr;
        curr.next.prev = newNode;
        curr.next = newNode;
        size++;
    }

    // Delete from beginning
    public void deleteFromBeginning() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    // Delete from end
    public void deleteFromEnd() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    // Delete specific value
    public boolean delete(int data) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == data) {
                if (curr == head) {
                    deleteFromBeginning();
                } else if (curr == tail) {
                    deleteFromEnd();
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                    size--;
                }
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Display forward
    public void displayForward() {
        Node curr = head;
        System.out.print("Forward: ");
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Display backward
    public void displayBackward() {
        Node curr = tail;
        System.out.print("Backward: ");
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.prev;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Main method for testing
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.insertAtBeginning(5);
        list.insertAtPosition(15, 2);

        list.displayForward();   // 5 10 15 20 30
        list.displayBackward();  // 30 20 15 10 5

        list.delete(15);
        list.displayForward();   // 5 10 20 30

        list.deleteFromBeginning();
        list.deleteFromEnd();
        list.displayForward();   // 10 20

        System.out.println("Size: " + list.getSize());
    }
}