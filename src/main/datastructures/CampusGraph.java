package datastructures;

/**
 * Custom undirected campus graph using a hand-rolled adjacency list.
 * Vertices are location names; edges are bidirectional campus roads/paths.
 * Does not use java.util collections.
 */
public class CampusGraph {

    /**
     * Node in a vertex's neighbour (adjacency) chain.
     */
    private static class NeighbourNode {
        String locationName;
        NeighbourNode next;

        NeighbourNode(String locationName) {
            this.locationName = locationName;
            this.next = null;
        }
    }

    /**
     * Vertex node holding a location name and its neighbour list.
     */
    private static class Vertex {
        String name;
        NeighbourNode neighbours;
        Vertex next;
        boolean visited;

        Vertex(String name) {
            this.name = name;
            this.neighbours = null;
            this.next = null;
            this.visited = false;
        }
    }

    /**
     * Simple linked FIFO queue of vertex names for BFS (no java.util.Queue).
     */
    private static class NameQueue {
        private static class QNode {
            String value;
            QNode next;

            QNode(String value) {
                this.value = value;
            }
        }

        private QNode front;
        private QNode rear;

        boolean isEmpty() {
            return front == null;
        }

        void enqueue(String value) {
            QNode node = new QNode(value);
            if (rear == null) {
                front = rear = node;
            } else {
                rear.next = node;
                rear = node;
            }
        }

        String dequeue() {
            if (front == null) {
                return null;
            }
            String value = front.value;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return value;
        }
    }

    private Vertex head;
    private int vertexCount;

    /**
     * Creates an empty campus graph.
     */
    public CampusGraph() {
        this.head = null;
        this.vertexCount = 0;
    }

    /**
     * @return number of locations (vertices) in the graph
     */
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Finds a vertex by location name.
     *
     * @param name location name
     * @return the Vertex, or null if not present
     */
    private Vertex findVertex(String name) {
        Vertex current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Checks whether a location exists in the graph.
     *
     * @param name location name
     * @return true if the vertex is present
     */
    public boolean hasVertex(String name) {
        return findVertex(name) != null;
    }

    /**
     * Adds a campus location as a vertex.
     *
     * @param name location name
     * @return true if added; false if a duplicate name already exists
     */
    public boolean addVertex(String name) {
        if (findVertex(name) != null) {
            return false;
        }
        Vertex vertex = new Vertex(name);
        vertex.next = head;
        head = vertex;
        vertexCount++;
        return true;
    }

    /**
     * Removes a location and all incident roads/edges.
     *
     * @param name location name to remove
     * @return true if removed; false if not found
     */
    public boolean removeVertex(String name) {
        Vertex target = findVertex(name);
        if (target == null) {
            return false;
        }

        // Remove this vertex from every other vertex's neighbour list
        Vertex current = head;
        while (current != null) {
            if (current != target) {
                removeNeighbourLink(current, name);
            }
            current = current.next;
        }

        // Unlink the vertex itself from the vertex list
        if (head == target) {
            head = head.next;
        } else {
            Vertex previous = head;
            while (previous.next != target) {
                previous = previous.next;
            }
            previous.next = target.next;
        }
        vertexCount--;
        return true;
    }

    /**
     * Adds an undirected edge (road) between two existing locations.
     *
     * @param from first location name
     * @param to   second location name
     * @return true if the edge was added; false if a location is missing,
     *         names are equal, or the edge already exists
     */
    public boolean addEdge(String from, String to) {
        if (from.equalsIgnoreCase(to)) {
            return false;
        }
        Vertex vFrom = findVertex(from);
        Vertex vTo = findVertex(to);
        if (vFrom == null || vTo == null) {
            return false;
        }
        if (hasNeighbour(vFrom, to) || hasNeighbour(vTo, from)) {
            return false;
        }
        // Store canonical names from the vertices for consistency
        prependNeighbour(vFrom, vTo.name);
        prependNeighbour(vTo, vFrom.name);
        return true;
    }

    /**
     * Removes an undirected edge between two locations.
     *
     * @param from first location name
     * @param to   second location name
     * @return true if an edge was removed; false otherwise
     */
    public boolean removeEdge(String from, String to) {
        Vertex vFrom = findVertex(from);
        Vertex vTo = findVertex(to);
        if (vFrom == null || vTo == null) {
            return false;
        }
        boolean removedA = removeNeighbourLink(vFrom, to);
        boolean removedB = removeNeighbourLink(vTo, from);
        return removedA || removedB;
    }

    /**
     * Prints the neighbours of a given location.
     *
     * @param location location name
     */
    public void displayNeighbours(String location) {
        Vertex vertex = findVertex(location);
        if (vertex == null) {
            System.out.println("Location not found: " + location);
            return;
        }
        System.out.print("Neighbours of " + vertex.name + ": ");
        if (vertex.neighbours == null) {
            System.out.println("(none)");
            return;
        }
        NeighbourNode n = vertex.neighbours;
        boolean first = true;
        while (n != null) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(n.locationName);
            first = false;
            n = n.next;
        }
        System.out.println();
    }

    /**
     * Prints the full adjacency list of the campus graph.
     */
    public void displayAll() {
        if (head == null) {
            System.out.println("Campus graph has no locations.");
            return;
        }
        System.out.println("--- Campus Connections (Adjacency List) ---");
        Vertex current = head;
        while (current != null) {
            System.out.print(current.name + " -> ");
            NeighbourNode n = current.neighbours;
            if (n == null) {
                System.out.println("(no connections)");
            } else {
                boolean first = true;
                while (n != null) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(n.locationName);
                    first = false;
                    n = n.next;
                }
                System.out.println();
            }
            current = current.next;
        }
    }

    /**
     * Performs a breadth-first traversal starting from the given location
     * and prints the visit order.
     *
     * @param startLocation starting location name
     */
    public void bfs(String startLocation) {
        Vertex start = findVertex(startLocation);
        if (start == null) {
            System.out.println("Start location not found: " + startLocation);
            return;
        }
        clearVisited();
        NameQueue queue = new NameQueue();
        start.visited = true;
        queue.enqueue(start.name);

        System.out.println("--- BFS from " + start.name + " ---");
        boolean first = true;
        while (!queue.isEmpty()) {
            String name = queue.dequeue();
            if (!first) {
                System.out.print(" -> ");
            }
            System.out.print(name);
            first = false;

            Vertex vertex = findVertex(name);
            NeighbourNode n = vertex.neighbours;
            while (n != null) {
                Vertex neighbour = findVertex(n.locationName);
                if (neighbour != null && !neighbour.visited) {
                    neighbour.visited = true;
                    queue.enqueue(neighbour.name);
                }
                n = n.next;
            }
        }
        System.out.println();
    }

    /**
     * Performs a depth-first traversal starting from the given location
     * and prints the visit order.
     *
     * @param startLocation starting location name
     */
    public void dfs(String startLocation) {
        Vertex start = findVertex(startLocation);
        if (start == null) {
            System.out.println("Start location not found: " + startLocation);
            return;
        }
        clearVisited();
        System.out.println("--- DFS from " + start.name + " ---");
        StringBuilder order = new StringBuilder();
        dfsRecursive(start, order);
        // Trim leading " -> "
        String result = order.toString();
        if (result.startsWith(" -> ")) {
            result = result.substring(4);
        }
        System.out.println(result);
    }

    /**
     * Recursive DFS helper that appends visit order to the builder.
     *
     * @param vertex current vertex
     * @param order  visit-order accumulator
     */
    private void dfsRecursive(Vertex vertex, StringBuilder order) {
        vertex.visited = true;
        order.append(" -> ").append(vertex.name);
        NeighbourNode n = vertex.neighbours;
        while (n != null) {
            Vertex neighbour = findVertex(n.locationName);
            if (neighbour != null && !neighbour.visited) {
                dfsRecursive(neighbour, order);
            }
            n = n.next;
        }
    }

    /**
     * Resets visited flags on all vertices.
     */
    private void clearVisited() {
        Vertex current = head;
        while (current != null) {
            current.visited = false;
            current = current.next;
        }
    }

    /**
     * @param vertex vertex to inspect
     * @param name   neighbour name
     * @return true if name is already a neighbour of vertex
     */
    private boolean hasNeighbour(Vertex vertex, String name) {
        NeighbourNode n = vertex.neighbours;
        while (n != null) {
            if (n.locationName.equalsIgnoreCase(name)) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    /**
     * Prepends a neighbour to a vertex's adjacency chain.
     *
     * @param vertex       vertex to update
     * @param neighbourName neighbour location name
     */
    private void prependNeighbour(Vertex vertex, String neighbourName) {
        NeighbourNode node = new NeighbourNode(neighbourName);
        node.next = vertex.neighbours;
        vertex.neighbours = node;
    }

    /**
     * Removes a neighbour link from a vertex's adjacency chain.
     *
     * @param vertex vertex to update
     * @param name   neighbour name to remove
     * @return true if a link was removed
     */
    private boolean removeNeighbourLink(Vertex vertex, String name) {
        NeighbourNode current = vertex.neighbours;
        NeighbourNode previous = null;
        while (current != null) {
            if (current.locationName.equalsIgnoreCase(name)) {
                if (previous == null) {
                    vertex.neighbours = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }
}
