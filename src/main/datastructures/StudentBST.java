package datastructures;

import model.Student;

/**
 * Custom Binary Search Tree keyed by student ID for sorted traversal
 * and logarithmic search. Kept in sync with the linked list by the service layer.
 */
public class StudentBST {

    /**
     * Internal BST node.
     */
    private static class Node {
        Student data;
        Node left;
        Node right;

        Node(Student data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    /**
     * Creates an empty student BST.
     */
    public StudentBST() {
        this.root = null;
    }

    /**
     * Inserts a student into the tree keyed by student ID.
     *
     * @param student the student to insert
     */
    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    /**
     * Recursive helper for insertion.
     *
     * @param node    current subtree root
     * @param student student to insert
     * @return the (possibly new) subtree root
     */
    private Node insertRecursive(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }
        int cmp = student.getStudentId().compareTo(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRecursive(node.left, student);
        } else if (cmp > 0) {
            node.right = insertRecursive(node.right, student);
        } else {
            // ID already present: refresh stored data reference
            node.data = student;
        }
        return node;
    }

    /**
     * Searches for a student by ID.
     *
     * @param studentId the ID to search for
     * @return the matching Student, or null if not found
     */
    public Student search(String studentId) {
        Node current = root;
        while (current != null) {
            int cmp = studentId.compareTo(current.data.getStudentId());
            if (cmp == 0) {
                return current.data;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * Updates the fields of an existing student in the tree.
     * Because the tree is keyed by ID (which does not change),
     * this finds the node and mutates the Student object in place.
     *
     * @param studentId student ID to update
     * @param name      new name
     * @param programme new programme
     * @param marks     new marks
     * @return true if found and updated; false otherwise
     */
    public boolean update(String studentId, String name, String programme, double marks) {
        Student found = search(studentId);
        if (found == null) {
            return false;
        }
        found.setName(name);
        found.setProgramme(programme);
        found.setMarks(marks);
        return true;
    }

    /**
     * Deletes the student with the given ID from the tree.
     *
     * @param studentId student ID to delete
     * @return true if a node was removed; false if not found
     */
    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = deleteRecursive(root, studentId);
        return true;
    }

    /**
     * Recursive helper for deletion.
     *
     * @param node      current subtree root
     * @param studentId ID to delete
     * @return the updated subtree root
     */
    private Node deleteRecursive(Node node, String studentId) {
        if (node == null) {
            return null;
        }
        int cmp = studentId.compareTo(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, studentId);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRecursive(node.right, successor.data.getStudentId());
        }
        return node;
    }

    /**
     * Finds the leftmost (minimum ID) node in a subtree.
     *
     * @param node subtree root
     * @return the minimum node
     */
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Prints all students in ascending student-ID order (inorder).
     */
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No students in the BST.");
            return;
        }
        System.out.println("--- Students Sorted by ID (BST Inorder) ---");
        inorderRecursive(root);
    }

    /**
     * Recursive inorder walk.
     *
     * @param node current subtree root
     */
    private void inorderRecursive(Node node) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.left);
        System.out.println(node.data);
        inorderRecursive(node.right);
    }
}
