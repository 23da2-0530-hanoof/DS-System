package model;

/**
 * Represents a logged student-record action (add, update, or delete)
 * stored on the action stack for audit/history display.
 */
public class Action {
    /** Action type constants. */
    public static final String TYPE_ADD = "ADD";
    public static final String TYPE_UPDATE = "UPDATE";
    public static final String TYPE_DELETE = "DELETE";

    private String type;
    private String studentId;
    private String description;
    private long timestamp;

    /**
     * Creates an action log entry.
     *
     * @param type        action type (ADD, UPDATE, or DELETE)
     * @param studentId   student ID involved in the action
     * @param description human-readable description of the action
     */
    public Action(String type, String studentId, String description) {
        this.type = type;
        this.studentId = studentId;
        this.description = description;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * @return the action type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the student ID associated with this action
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @return the human-readable description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return epoch-millis timestamp when the action was recorded
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return a readable summary of this action
     */
    @Override
    public String toString() {
        return "[" + type + "] studentId=" + studentId
                + " | " + description + " | ts=" + timestamp;
    }
}
