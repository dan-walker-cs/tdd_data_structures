package trees;

public class BinarySearchTree {

    public TreeNode root;

    private class TreeNode {
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode() {
            this.data = 0;
            this.leftChild = null;
            this.rightChild = null;
        }

        TreeNode(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(TreeNode root, int data) {
        if(root == null) {
            return false;
        }

        if(root.data == data) {
            return true;
        }

        return data < root.data
                ? search(root.leftChild, data)
                : search(root.rightChild, data);
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private TreeNode insert(TreeNode root, int data) {
        if(root == null) {
            root = new TreeNode(data);
        } else if(data < root.data) {
            root.leftChild = insert(root.leftChild, data);
        } else if(data > root.data) {
            root.rightChild = insert(root.rightChild, data);
        }

        return root;
    }

    public void remove(int data) {
        root = remove(root, data);
    }

    private TreeNode remove(TreeNode root, int data) {
        if(root == null) {
            return null;
        }
        if(root.data == data) {
            // no children
            if(root.leftChild == null && root.rightChild == null) {
                return null;
            }
            // 1 child
            if(root.leftChild == null) {
                return root.rightChild;
            } else if(root.rightChild == null) {
                return root.leftChild;
            }
            // 2 children
            int rst_min = findMin(root.rightChild);
            root.data = rst_min;
            root.rightChild = remove(root.rightChild, rst_min);
        } else if(data < root.data) {
            root.leftChild = remove(root.leftChild, data);
        } else {
            root.rightChild = remove(root.rightChild, data);
        }

        return root;
    }

    private int findMin(TreeNode root) {
        return root.leftChild == null
                ? root.data
                : findMin(root.leftChild);
    }

    private boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }



}
