import datastructures.CampusGraph;
import datastructures.ServiceQueue;
import model.ServiceRequest;
import model.Student;
import service.StudentRecordService;
import util.InputValidator;

import java.util.Scanner;

/**
 * Menu-driven console entry point for the University Student Record
 * and Campus Route Management System.
 */
public class Main {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRecordService studentService = new StudentRecordService();
        ServiceQueue serviceQueue = new ServiceQueue();
        CampusGraph campusGraph = new CampusGraph();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = InputValidator.readInt(scanner, "Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1:
                    handleAddStudent(scanner, studentService);
                    break;
                case 2:
                    handleUpdateStudent(scanner, studentService);
                    break;
                case 3:
                    handleDeleteStudent(scanner, studentService);
                    break;
                case 4:
                    studentService.getLinkedList().displayAll();
                    break;
                case 5:
                    handleEnqueueServiceRequest(scanner, serviceQueue);
                    break;
                case 6:
                    handleProcessServiceRequest(serviceQueue);
                    break;
                case 7:
                    studentService.getActionStack().displayRecent();
                    break;
                case 8:
                    studentService.getBst().inorderTraversal();
                    break;
                case 9:
                    handleSearchByHash(scanner, studentService);
                    break;
                case 10:
                    handleAddLocation(scanner, campusGraph);
                    break;
                case 11:
                    handleRemoveLocation(scanner, campusGraph);
                    break;
                case 12:
                    handleAddConnection(scanner, campusGraph);
                    break;
                case 13:
                    handleRemoveConnection(scanner, campusGraph);
                    break;
                case 14:
                    campusGraph.displayAll();
                    break;
                case 15:
                    handleTraverseCampus(scanner, campusGraph);
                    break;
                case 16:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu choice. Please select 1–16.");
                    break;
            }

            if (running) {
                System.out.println();
            }
        }

        scanner.close();
    }

    /**
     * Prints the exact assignment menu.
     */
    private static void printMenu() {
        System.out.println("==============================================");
        System.out.println(" University Student Record & Campus Route");
        System.out.println(" Management System");
        System.out.println("==============================================");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS (ask user which)");
        System.out.println("16. Exit");
        System.out.println("==============================================");
    }

    /**
     * Handles menu option 1: add student.
     */
    private static void handleAddStudent(Scanner scanner, StudentRecordService service) {
        String id = InputValidator.readNonEmptyString(scanner, "Enter Student ID: ");
        String name = InputValidator.readNonEmptyString(scanner, "Enter Name: ");
        String programme = InputValidator.readNonEmptyString(scanner, "Enter Programme: ");
        double marks = InputValidator.readMarks(scanner, "Enter Marks (0-100): ");

        if (service.addStudent(id, name, programme, marks)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Error: A student with ID '" + id + "' already exists.");
        }
    }

    /**
     * Handles menu option 2: update student.
     */
    private static void handleUpdateStudent(Scanner scanner, StudentRecordService service) {
        String id = InputValidator.readNonEmptyString(scanner, "Enter Student ID to update: ");
        if (service.getLinkedList().find(id) == null) {
            System.out.println("Error: No student found with ID '" + id + "'.");
            return;
        }
        String name = InputValidator.readNonEmptyString(scanner, "Enter new Name: ");
        String programme = InputValidator.readNonEmptyString(scanner, "Enter new Programme: ");
        double marks = InputValidator.readMarks(scanner, "Enter new Marks (0-100): ");

        if (service.updateStudent(id, name, programme, marks)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Error: No student found with ID '" + id + "'.");
        }
    }

    /**
     * Handles menu option 3: delete student.
     */
    private static void handleDeleteStudent(Scanner scanner, StudentRecordService service) {
        String id = InputValidator.readNonEmptyString(scanner, "Enter Student ID to delete: ");
        if (service.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Error: No student found with ID '" + id + "'.");
        }
    }

    /**
     * Handles menu option 5: enqueue service request.
     */
    private static void handleEnqueueServiceRequest(Scanner scanner, ServiceQueue queue) {
        String id = InputValidator.readNonEmptyString(scanner, "Enter Student ID: ");
        String description = InputValidator.readNonEmptyString(scanner, "Enter request description: ");
        queue.enqueue(new ServiceRequest(id, description));
        System.out.println("Service request added to the queue.");
    }

    /**
     * Handles menu option 6: dequeue and process next request.
     */
    private static void handleProcessServiceRequest(ServiceQueue queue) {
        if (queue.isEmpty()) {
            System.out.println("Service queue is empty. Nothing to process.");
            return;
        }
        ServiceRequest request = queue.dequeue();
        System.out.println("Processed: " + request);
    }

    /**
     * Handles menu option 9: search via hash table.
     */
    private static void handleSearchByHash(Scanner scanner, StudentRecordService service) {
        String id = InputValidator.readNonEmptyString(scanner, "Enter Student ID to search: ");
        Student student = service.searchByHash(id);
        if (student == null) {
            System.out.println("Error: No student found with ID '" + id + "'.");
        } else {
            System.out.println("Found: " + student);
        }
    }

    /**
     * Handles menu option 10: add campus location.
     */
    private static void handleAddLocation(Scanner scanner, CampusGraph graph) {
        String name = InputValidator.readNonEmptyString(scanner, "Enter location name: ");
        if (graph.addVertex(name)) {
            System.out.println("Location '" + name + "' added.");
        } else {
            System.out.println("Error: Location '" + name + "' already exists.");
        }
    }

    /**
     * Handles menu option 11: remove campus location.
     */
    private static void handleRemoveLocation(Scanner scanner, CampusGraph graph) {
        String name = InputValidator.readNonEmptyString(scanner, "Enter location name to remove: ");
        if (graph.removeVertex(name)) {
            System.out.println("Location '" + name + "' removed (incident roads cleared).");
        } else {
            System.out.println("Error: Location '" + name + "' does not exist.");
        }
    }

    /**
     * Handles menu option 12: add undirected road between locations.
     */
    private static void handleAddConnection(Scanner scanner, CampusGraph graph) {
        String from = InputValidator.readNonEmptyString(scanner, "Enter first location: ");
        String to = InputValidator.readNonEmptyString(scanner, "Enter second location: ");

        if (!graph.hasVertex(from)) {
            System.out.println("Error: Location '" + from + "' does not exist.");
            return;
        }
        if (!graph.hasVertex(to)) {
            System.out.println("Error: Location '" + to + "' does not exist.");
            return;
        }
        if (from.equalsIgnoreCase(to)) {
            System.out.println("Error: A location cannot connect to itself.");
            return;
        }
        if (graph.addEdge(from, to)) {
            System.out.println("Connection added between '" + from + "' and '" + to + "'.");
        } else {
            System.out.println("Error: Connection already exists or could not be added.");
        }
    }

    /**
     * Handles menu option 13: remove a campus road.
     */
    private static void handleRemoveConnection(Scanner scanner, CampusGraph graph) {
        String from = InputValidator.readNonEmptyString(scanner, "Enter first location: ");
        String to = InputValidator.readNonEmptyString(scanner, "Enter second location: ");

        if (!graph.hasVertex(from) || !graph.hasVertex(to)) {
            System.out.println("Error: One or both locations do not exist.");
            return;
        }
        if (graph.removeEdge(from, to)) {
            System.out.println("Connection removed between '" + from + "' and '" + to + "'.");
        } else {
            System.out.println("Error: No connection exists between those locations.");
        }
    }

    /**
     * Handles menu option 15: BFS or DFS traversal.
     */
    private static void handleTraverseCampus(Scanner scanner, CampusGraph graph) {
        if (graph.getVertexCount() == 0) {
            System.out.println("Campus graph has no locations to traverse.");
            return;
        }
        String start = InputValidator.readNonEmptyString(scanner, "Enter start location: ");
        if (!graph.hasVertex(start)) {
            System.out.println("Error: Location '" + start + "' does not exist.");
            return;
        }
        System.out.println("Choose traversal: 1 = BFS, 2 = DFS");
        int mode = InputValidator.readIntInRange(scanner, "Enter 1 or 2: ", 1, 2);
        if (mode == 1) {
            graph.bfs(start);
        } else {
            graph.dfs(start);
        }
    }
}
