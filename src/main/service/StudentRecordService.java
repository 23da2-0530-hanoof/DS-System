package service;

import datastructures.ActionStack;
import datastructures.StudentBST;
import datastructures.StudentHashTable;
import datastructures.StudentLinkedList;
import model.Action;
import model.Student;

/**
 * Orchestrates student CRUD so the linked list, BST, and hash table
 * stay synchronised, and every mutation is logged on the action stack.
 */
public class StudentRecordService {

    private final StudentLinkedList linkedList;
    private final StudentBST bst;
    private final StudentHashTable hashTable;
    private final ActionStack actionStack;

    /**
     * Creates a service that owns and coordinates all student structures.
     */
    public StudentRecordService() {
        this.linkedList = new StudentLinkedList();
        this.bst = new StudentBST();
        this.hashTable = new StudentHashTable();
        this.actionStack = new ActionStack();
    }

    /**
     * @return the authoritative linked list of students
     */
    public StudentLinkedList getLinkedList() {
        return linkedList;
    }

    /**
     * @return the student BST
     */
    public StudentBST getBst() {
        return bst;
    }

    /**
     * @return the student hash table
     */
    public StudentHashTable getHashTable() {
        return hashTable;
    }

    /**
     * @return the action audit stack
     */
    public ActionStack getActionStack() {
        return actionStack;
    }

    /**
     * Adds a new student to the list, BST, and hash table, then logs the action.
     *
     * @param studentId student ID (must be unique)
     * @param name      student name
     * @param programme academic programme
     * @param marks     marks 0–100
     * @return true if added; false if the ID already exists
     */
    public boolean addStudent(String studentId, String name, String programme, double marks) {
        if (linkedList.contains(studentId)) {
            return false;
        }
        Student student = new Student(studentId, name, programme, marks);
        linkedList.add(student);
        bst.insert(student);
        hashTable.insert(student);
        actionStack.push(new Action(
                Action.TYPE_ADD,
                studentId,
                "Added student " + name + " (" + programme + ", marks=" + marks + ")"
        ));
        return true;
    }

    /**
     * Updates an existing student's details across all structures and logs the action.
     *
     * @param studentId student ID to update
     * @param name      new name
     * @param programme new programme
     * @param marks     new marks
     * @return true if updated; false if the ID does not exist
     */
    public boolean updateStudent(String studentId, String name, String programme, double marks) {
        if (!linkedList.contains(studentId)) {
            return false;
        }
        linkedList.update(studentId, name, programme, marks);
        // Shared Student reference keeps BST/hash in sync; call update for clarity
        bst.update(studentId, name, programme, marks);
        hashTable.update(studentId, name, programme, marks);
        actionStack.push(new Action(
                Action.TYPE_UPDATE,
                studentId,
                "Updated student to name=" + name + ", programme=" + programme + ", marks=" + marks
        ));
        return true;
    }

    /**
     * Deletes a student from all structures and logs the action.
     *
     * @param studentId student ID to delete
     * @return true if deleted; false if the ID does not exist
     */
    public boolean deleteStudent(String studentId) {
        if (!linkedList.contains(studentId)) {
            return false;
        }
        linkedList.delete(studentId);
        bst.delete(studentId);
        hashTable.delete(studentId);
        actionStack.push(new Action(
                Action.TYPE_DELETE,
                studentId,
                "Deleted student " + studentId
        ));
        return true;
    }

    /**
     * Searches for a student using the hash table.
     *
     * @param studentId student ID to find
     * @return the Student, or null if not found
     */
    public Student searchByHash(String studentId) {
        return hashTable.search(studentId);
    }
}
