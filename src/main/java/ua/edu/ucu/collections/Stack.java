package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;

    public Stack() {
        this.stack = new ImmutableLinkedList();
    }

    public void push(Object e) {
        stack = stack.addLast(e);
    }

    public Object pop() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Object last = stack.getLast();
        stack = stack.removeFirst();
        return last;
    }

    public Object peek() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return stack.getLast();
    }
}
