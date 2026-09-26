package datastructures;

import model.ServiceRequest;

/**
 * Custom FIFO queue for student service desk requests.
 * Implemented with a circular array that grows as needed.
 */
public class ServiceQueue {

    private ServiceRequest[] elements;
    private int front;
    private int rear;
    private int count;
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Creates an empty service queue with default capacity.
     */
    public ServiceQueue() {
        this.elements = new ServiceRequest[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    /**
     * @return true if the queue has no pending requests
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * @return the number of pending requests
     */
    public int size() {
        return count;
    }

    /**
     * Adds a service request to the rear of the queue.
     *
     * @param request the request to enqueue
     */
    public void enqueue(ServiceRequest request) {
        if (count == elements.length) {
            resize();
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = request;
        count++;
    }

    /**
     * Removes and returns the request at the front of the queue.
     *
     * @return the dequeued ServiceRequest, or null if empty
     */
    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest request = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        count--;
        return request;
    }

    /**
     * Returns the front request without removing it.
     *
     * @return the front ServiceRequest, or null if empty
     */
    public ServiceRequest peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[front];
    }

    /**
     * Prints all pending requests from front to rear.
     */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Service queue is empty.");
            return;
        }
        System.out.println("--- Service Queue (front to rear) ---");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % elements.length;
            System.out.println((i + 1) + ". " + elements[index]);
        }
    }

    /**
     * Grows the circular buffer, linearising elements into a larger array.
     */
    private void resize() {
        ServiceRequest[] larger = new ServiceRequest[elements.length * 2];
        for (int i = 0; i < count; i++) {
            larger[i] = elements[(front + i) % elements.length];
        }
        elements = larger;
        front = 0;
        rear = count - 1;
    }
}
