package com.pcgs.core.java.pocs.MapSearch;

import java.util.*;

// Employee class
class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name=%s, Salary=%.2f]", id, name, salary);
    }
}

// TreeNode class
class TreeNode {
    Employee employee;
    TreeNode left;
    TreeNode right;

    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}

// Binary Search Tree implementation
class EmployeeBST {
    private TreeNode root;

    public EmployeeBST() {
        this.root = null;
    }

    // Insert employee into BST
    public void insert(Employee employee) {
        root = insertRecursive(root, employee);
    }

    private TreeNode insertRecursive(TreeNode node, Employee employee) {
        if (node == null) {
            return new TreeNode(employee);
        }

        if (employee.compareTo(node.employee) < 0) {
            node.left = insertRecursive(node.left, employee);
        } else if (employee.compareTo(node.employee) > 0) {
            node.right = insertRecursive(node.right, employee);
        }
        // If equal, we don't insert duplicates

        return node;
    }

    // In-Order Traversal (Left -> Root -> Right) - Returns sorted order
    public void inOrder() {
        System.out.println("\n=== In-Order Traversal (Sorted) ===");
        inOrderRecursive(root);
    }

    private void inOrderRecursive(TreeNode node) {
        if (node != null) {
            inOrderRecursive(node.left);
            System.out.println(node.employee);
            inOrderRecursive(node.right);
        }
    }

    // Pre-Order Traversal (Root -> Left -> Right)
    public void preOrder() {
        System.out.println("\n=== Pre-Order Traversal ===");
        preOrderRecursive(root);
    }

    private void preOrderRecursive(TreeNode node) {
        if (node != null) {
            System.out.println(node.employee);
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    // Post-Order Traversal (Left -> Right -> Root)
    public void postOrder() {
        System.out.println("\n=== Post-Order Traversal ===");
        postOrderRecursive(root);
    }

    private void postOrderRecursive(TreeNode node) {
        if (node != null) {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.println(node.employee);
        }
    }

    // Find minimum value (leftmost node)
    public Employee findMin() {
        if (root == null) {
            return null;
        }
        TreeNode minNode = findMinNode(root);
        return minNode.employee;
    }

    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Find maximum value (rightmost node)
    public Employee findMax() {
        if (root == null) {
            return null;
        }
        TreeNode maxNode = findMaxNode(root);
        return maxNode.employee;
    }

    private TreeNode findMaxNode(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // Search for an employee by ID
    public Employee search(int id) {
        TreeNode result = searchRecursive(root, id);
        return result != null ? result.employee : null;
    }

    private TreeNode searchRecursive(TreeNode node, int id) {
        if (node == null || node.employee.getId() == id) {
            return node;
        }

        if (id < node.employee.getId()) {
            return searchRecursive(node.left, id);
        }
        return searchRecursive(node.right, id);
    }

    // Delete employee by ID
    public void delete(int id) {
        root = deleteRecursive(root, id);
    }

    private TreeNode deleteRecursive(TreeNode node, int id) {
        if (node == null) {
            return null;
        }

        // Find the node to delete
        if (id < node.employee.getId()) {
            node.left = deleteRecursive(node.left, id);
        } else if (id > node.employee.getId()) {
            node.right = deleteRecursive(node.right, id);
        } else {
            // Node found - handle three cases

            // Case 1: Node with no children (leaf node)
            if (node.left == null && node.right == null) {
                System.out.println("Deleting leaf node: " + node.employee);
                return null;
            }

            // Case 2: Node with one child
            if (node.left == null) {
                System.out.println("Deleting node with right child: " + node.employee);
                return node.right;
            }
            if (node.right == null) {
                System.out.println("Deleting node with left child: " + node.employee);
                return node.left;
            }

            // Case 3: Node with two children
            // Find inorder successor (minimum in right subtree)
            System.out.println("Deleting node with two children: " + node.employee);
            TreeNode successor = findMinNode(node.right);
            node.employee = successor.employee;
            node.right = deleteRecursive(node.right, successor.employee.getId());
        }

        return node;
    }

    // Get height of tree
    public int getHeight() {
        return getHeightRecursive(root);
    }

    private int getHeightRecursive(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeightRecursive(node.left), getHeightRecursive(node.right));
    }

    // Check if tree is empty
    public boolean isEmpty() {
        return root == null;
    }
}

// Main class to demonstrate all operations
class BinarySearchTreeDemo {
    public static void main(String[] args) {
        EmployeeBST bst = new EmployeeBST();

        // Insert employees
        System.out.println("=== Inserting Employees ===");
        bst.insert(new Employee(50, "John Doe", 75000));
        bst.insert(new Employee(30, "Jane Smith", 65000));
        bst.insert(new Employee(70, "Bob Johnson", 80000));
        bst.insert(new Employee(20, "Alice Brown", 55000));
        bst.insert(new Employee(40, "Charlie Davis", 70000));
        bst.insert(new Employee(60, "Diana Wilson", 72000));
        bst.insert(new Employee(80, "Eve Martinez", 85000));
        System.out.println("Employees inserted successfully!");

        // Traversals
        bst.inOrder();
        bst.preOrder();
        bst.postOrder();

        // Min and Max
        System.out.println("\n=== Min and Max ===");
        System.out.println("Minimum: " + bst.findMin());
        System.out.println("Maximum: " + bst.findMax());

        // Search
        System.out.println("\n=== Search Operation ===");
        Employee found = bst.search(40);
        System.out.println("Search for ID 40: " + (found != null ? found : "Not found"));

        // Tree height
        System.out.println("\n=== Tree Information ===");
        System.out.println("Tree height: " + bst.getHeight());

        // Deletion demonstrations
        System.out.println("\n=== Deletion: Leaf Node (ID 20) ===");
        bst.delete(20);
        bst.inOrder();

        System.out.println("\n=== Deletion: Node with One Child (ID 30) ===");
        bst.delete(30);
        bst.inOrder();

        System.out.println("\n=== Deletion: Node with Two Children (ID 50 - root) ===");
        bst.delete(50);
        bst.inOrder();

        // Final state
        System.out.println("\n=== Final Tree State ===");
        System.out.println("Minimum: " + bst.findMin());
        System.out.println("Maximum: " + bst.findMax());
        System.out.println("Tree height: " + bst.getHeight());
    }
}
