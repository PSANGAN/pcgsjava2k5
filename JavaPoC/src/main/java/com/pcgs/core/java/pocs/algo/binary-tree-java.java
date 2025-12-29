class Node {
    int data;
    Node left, right;
    
    public Node(int value) {
        data = value;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    // Insert a new node
    public void insert(int value) {
        root = insertRec(root, value);
    }
    
    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        
        if (value < root.data) {
            root.left = insertRec(root.left, value);
        } else if (value > root.data) {
            root.right = insertRec(root.right, value);
        }
        
        return root;
    }
    
    // Search for a value
    public boolean search(int value) {
        return searchRec(root, value);
    }
    
    private boolean searchRec(Node root, int value) {
        if (root == null) {
            return false;
        }
        
        if (root.data == value) {
            return true;
        }
        
        if (value < root.data) {
            return searchRec(root.left, value);
        }
        
        return searchRec(root.right, value);
    }
    
    // Delete a node
    public void delete(int value) {
        root = deleteRec(root, value);
    }
    
    private Node deleteRec(Node root, int value) {
        if (root == null) {
            return root;
        }
        
        if (value < root.data) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.data) {
            root.right = deleteRec(root.right, value);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            
            // Node with two children: Get inorder successor
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
        
        return root;
    }
    
    private int minValue(Node root) {
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
    
    // Find minimum value in tree
    public int findMin() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        return minValue(root);
    }
    
    // Find maximum value in tree
    public int findMax() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }
    
    // Inorder traversal (Left-Root-Right)
    public void inorder() {
        System.out.print("Inorder: ");
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
    
    // Preorder traversal (Root-Left-Right)
    public void preorder() {
        System.out.print("Preorder: ");
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
    
    // Postorder traversal (Left-Right-Root)
    public void postorder() {
        System.out.print("Postorder: ");
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
    
    // Level order traversal (Breadth-First)
    public void levelOrder() {
        if (root == null) {
            return;
        }
        
        System.out.print("Level Order: ");
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");
            
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }
    
    // Calculate height of tree
    public int height() {
        return heightRec(root);
    }
    
    private int heightRec(Node root) {
        if (root == null) {
            return 0;
        }
        
        int leftHeight = heightRec(root.left);
        int rightHeight = heightRec(root.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    // Count total nodes
    public int countNodes() {
        return countNodesRec(root);
    }
    
    private int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }
    
    // Count leaf nodes
    public int countLeaves() {
        return countLeavesRec(root);
    }
    
    private int countLeavesRec(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return countLeavesRec(root.left) + countLeavesRec(root.right);
    }
    
    // Check if tree is empty
    public boolean isEmpty() {
        return root == null;
    }
    
    // Clear the tree
    public void clear() {
        root = null;
    }
}

// Main class to demonstrate usage
 class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        // Insert operations
        System.out.println("Inserting: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        // Traversals
        bst.inorder();
        bst.preorder();
        bst.postorder();
        bst.levelOrder();
        
        // Search operation
        System.out.println("\nSearch 40: " + bst.search(40));
        System.out.println("Search 100: " + bst.search(100));
        
        // Tree properties
        System.out.println("\nHeight: " + bst.height());
        System.out.println("Total nodes: " + bst.countNodes());
        System.out.println("Leaf nodes: " + bst.countLeaves());
        System.out.println("Min value: " + bst.findMin());
        System.out.println("Max value: " + bst.findMax());
        
        // Delete operation
        System.out.println("\nDeleting 20");
        bst.delete(20);
        bst.inorder();
        
        System.out.println("\nDeleting 30");
        bst.delete(30);
        bst.inorder();
        
        System.out.println("\nDeleting 50");
        bst.delete(50);
        bst.inorder();
    }
}