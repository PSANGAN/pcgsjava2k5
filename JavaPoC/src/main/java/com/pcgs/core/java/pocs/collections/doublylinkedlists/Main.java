package com.pcgs.core.java.pocs.collections.doublylinkedlists;

import com.pcgs.core.java.pocs.algo.Employee;

public class Main {
    public static void main(String[] args) {
        Employee janeJones = new Employee("Jane", "Jones", 123);
        Employee johnDoe = new Employee("John", "Doe", 4567);
        Employee marySmith = new Employee("Mary", "Smith", 22);
        Employee mikeWilson = new Employee("Mike", "Wilson", 3245);

        EmployeeDoublyLinkedList list = new EmployeeDoublyLinkedList();

        list.addToFront(janeJones);
        list.addToFront(johnDoe);
        list.addToFront(marySmith);
        list.addToFront(mikeWilson);

        list.printList();
        System.out.println(list.getSize());

        Employee billEnd = new Employee("Bill", "End", 78);
        list.addToEnd(billEnd);
        list.printList();
        System.out.println(list.getSize());
        list.removeFromFront();
        list.printList();
        System.out.println(list.getSize());
        list.removeFromEnd();
        list.printList();
        System.out.println(list.getSize());

        Integer one = 1;
        Integer two = 2;
        Integer three = 3;
        Integer four = 4;

        IntegerLinkedList integerLinkedList = new IntegerLinkedList();
        integerLinkedList.insertSorted(three);
        integerLinkedList.printList();
        integerLinkedList.insertSorted(two);
        integerLinkedList.printList();
        integerLinkedList.insertSorted(one);
        integerLinkedList.printList();
        integerLinkedList.insertSorted(four);
        integerLinkedList.printList();

    }
}
