package datastructures;

import model.Action;

/**
 * Custom stack that logs student-record actions (add/update/delete).
 * Implemented with a dynamically resized array.
 */
public class ActionStack {

    private Action[] elements;
    private int top;
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Creates an empty action stack with default capacity.
     */
    public ActionStack() {
        this.elements = new Action[DEFAULT_CAPACITY];
        this.top = -1;
    }

    /**
     * @return true if the stack has no actions
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * @return the number of actions currently on the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Pushes an action onto the top of the stack.
     *
     * @param action the action to record
     */
    public void push(Action action) {
        if (top + 1 >= elements.length) {
            resize();
        }
        elements[++top] = action;
    }

    /**
     * Removes and returns the most recent action.
     *
     * @return the popped Action, or null if the stack is empty
     */
    public Action pop() {
        if (isEmpty()) {
            return null;
        }
        Action action = elements[top];
        elements[top] = null;
        top--;
        return action;
    }

    /**
     * Returns the most recent action without removing it.
     *
     * @return the top Action, or null if the stack is empty
     */
    public Action peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[top];
    }

    /**
     * Displays recent actions from most recent to oldest
     * without destroying the stack contents.
     */
    public void displayRecent() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("--- Recent Actions (most recent first) ---");
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + elements[i]);
        }
    }

    /**
     * Doubles the internal array capacity when full.
     */
    private void resize() {
        Action[] larger = new Action[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            larger[i] = elements[i];
        }
        elements = larger;
    }
}
