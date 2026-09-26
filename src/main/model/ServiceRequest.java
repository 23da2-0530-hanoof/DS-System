package model;

/**
 * Represents a student service desk request held in the service queue.
 */
public class ServiceRequest {
    private String studentId;
    private String requestDescription;

    /**
     * Creates a service request.
     *
     * @param studentId          ID of the student making the request
     * @param requestDescription short description of what is requested
     */
    public ServiceRequest(String studentId, String requestDescription) {
        this.studentId = studentId;
        this.requestDescription = requestDescription;
    }

    /**
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the student ID to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the request description
     */
    public String getRequestDescription() {
        return requestDescription;
    }

    /**
     * @param requestDescription the request description to set
     */
    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    /**
     * @return a readable summary of this service request
     */
    @Override
    public String toString() {
        return "ServiceRequest{studentId='" + studentId
                + "', description='" + requestDescription + "'}";
    }
}
