package model;

/**
 * Represents a university student record with identification,
 * academic programme, and marks.
 */
public class Student {
    private String studentId;
    private String name;
    private String programme;
    private double marks;

    /**
     * Creates a new student with the given details.
     *
     * @param studentId unique student identifier
     * @param name      full name of the student
     * @param programme academic programme of study
     * @param marks     marks out of 100
     */
    public Student(String studentId, String name, String programme, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
        this.marks = marks;
    }

    /**
     * @return the unique student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the unique student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the student's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the academic programme
     */
    public String getProgramme() {
        return programme;
    }

    /**
     * @param programme the academic programme to set
     */
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    /**
     * @return the marks (0–100)
     */
    public double getMarks() {
        return marks;
    }

    /**
     * @param marks the marks to set (0–100)
     */
    public void setMarks(double marks) {
        this.marks = marks;
    }

    /**
     * @return a readable summary of this student record
     */
    @Override
    public String toString() {
        return "Student{id='" + studentId + "', name='" + name
                + "', programme='" + programme + "', marks=" + marks + "}";
    }
}
