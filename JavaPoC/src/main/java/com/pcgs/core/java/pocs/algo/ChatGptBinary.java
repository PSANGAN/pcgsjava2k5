package com.pcgs.core.java.pocs.algo;

import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree {

    // Node definition
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    // ================= INSERT =================
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    // ================= SEARCH =================
    public boolean search(int data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;

        return data < root.data
                ? searchRec(root.left, data)
                : searchRec(root.right, data);
    }

    // ================= DELETE =================
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node root, int data) {
        if (root == null)
            return null;

        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Case 1 & 2: One child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Case 3: Two children
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    private int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    // ================= TRAVERSALS =================
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // ================= LEVEL ORDER =================
    public void levelOrder() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");

            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
        System.out.println();
    }

    // ================= HEIGHT =================
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node root) {
        if (root == null)
            return -1;

        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    // ================= COUNT NODES =================
    public int countNodes() {
        return countRec(root);
    }

    private int countRec(Node root) {
        if (root == null)
            return 0;
        return 1 + countRec(root.left) + countRec(root.right);
    }

    // ================= MIN & MAX =================
    public int findMin() {
        if (root == null)
            throw new RuntimeException("Tree is empty");
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.data;
    }

    public int findMax() {
        if (root == null)
            throw new RuntimeException("Tree is empty");
        Node current = root;
        while (current.right != null)
            current = current.right;
        return current.data;
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        System.out.print("Inorder: ");
        bst.inorder();

        System.out.print("Preorder: ");
        bst.preorder();

        System.out.print("Postorder: ");
        bst.postorder();

        System.out.print("Level Order: ");
        bst.levelOrder();

        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Min: " + bst.findMin());
        System.out.println("Max: " + bst.findMax());
        System.out.println("Height: " + bst.height());
        System.out.println("Total Nodes: " + bst.countNodes());

        bst.delete(50);
        System.out.print("After deleting 50 (Inorder): ");
        bst.inorder();
    }
}
