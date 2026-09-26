package datastructures;

import model.Student;

/**
 * Custom hash table with separate chaining for collision handling,
 * keyed by student ID for average-case O(1) lookup.
 * Kept in sync with the linked list by the service layer.
 */
public class StudentHashTable {

    /**
     * Chain node within a bucket.
     */
    private static class Entry {
        Student data;
        Entry next;

        Entry(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 31;

    /**
     * Creates an empty hash table with default capacity.
     */
    public StudentHashTable() {
        this.buckets = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Creates an empty hash table with the given capacity.
     *
     * @param capacity number of buckets (must be positive)
     */
    public StudentHashTable(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.buckets = new Entry[capacity];
        this.size = 0;
    }

    /**
     * @return number of students stored
     */
    public int size() {
        return size;
    }

    /**
     * Computes the bucket index for a student ID.
     *
     * @param studentId the key
     * @return bucket index in range [0, buckets.length)
     */
    private int hash(String studentId) {
        int h = studentId.hashCode() % buckets.length;
        if (h < 0) {
            h += buckets.length;
        }
        return h;
    }

    /**
     * Inserts a student into the table. If the ID already exists,
     * the existing entry's data reference is replaced.
     *
     * @param student the student to insert
     */
    public void insert(Student student) {
        int index = hash(student.getStudentId());
        Entry current = buckets[index];
        while (current != null) {
            if (current.data.getStudentId().equals(student.getStudentId())) {
                current.data = student;
                return;
            }
            current = current.next;
        }
        Entry newEntry = new Entry(student);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    /**
     * Searches for a student by ID.
     *
     * @param studentId the ID to look up
     * @return the matching Student, or null if not found
     */
    public Student search(String studentId) {
        int index = hash(studentId);
        Entry current = buckets[index];
        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Updates fields of an existing student in the table.
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
     * Removes the student with the given ID from the table.
     *
     * @param studentId student ID to delete
     * @return true if removed; false if not found
     */
    public boolean delete(String studentId) {
        int index = hash(studentId);
        Entry current = buckets[index];
        Entry previous = null;
        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }
}
