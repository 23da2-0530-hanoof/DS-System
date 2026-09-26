package datastructures;

import model.Student;

/**
 * Custom singly linked list that serves as the primary/authoritative
 * store of student records.
 */
public class StudentLinkedList {

    /**
     * Internal node holding a student and a reference to the next node.
     */
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Creates an empty student linked list.
     */
    public StudentLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * @return the number of students currently stored
     */
    public int size() {
        return size;
    }

    /**
     * @return true if the list contains no students
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Checks whether a student with the given ID exists.
     *
     * @param studentId student ID to look for
     * @return true if a matching student is present
     */
    public boolean contains(String studentId) {
        return find(studentId) != null;
    }

    /**
     * Appends a student to the end of the list.
     *
     * @param student the student to add
     */
    public void add(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Updates the fields of the student with the given ID.
     *
     * @param studentId student ID to update
     * @param name      new name
     * @param programme new programme
     * @param marks     new marks
     * @return true if the student was found and updated; false otherwise
     */
    public boolean update(String studentId, String name, String programme, double marks) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                current.data.setName(name);
                current.data.setProgramme(programme);
                current.data.setMarks(marks);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the student with the given ID from the list.
     *
     * @param studentId student ID to delete
     * @return true if the student was found and removed; false otherwise
     */
    public boolean delete(String studentId) {
        if (head == null) {
            return false;
        }
        if (head.data.getStudentId().equals(studentId)) {
            head = head.next;
            size--;
            return true;
        }
        Node previous = head;
        Node current = head.next;
        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    /**
     * Finds a student by ID.
     *
     * @param studentId student ID to search for
     * @return the matching Student, or null if not found
     */
    public Student find(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Prints every student record in list order to standard output.
     */
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("--- Student Records (Linked List) ---");
        Node current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.data);
            current = current.next;
            index++;
        }
    }
}
