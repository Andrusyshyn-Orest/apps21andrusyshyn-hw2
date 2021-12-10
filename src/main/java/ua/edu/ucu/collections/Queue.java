package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public Object peek() {
        if (queue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return queue.getFirst();
    }

    public Object dequeue() {
        if (queue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Object first = queue.getFirst();
        queue = queue.removeFirst();
        return first;
    }

    public void enqueue(Object e) {
        queue = queue.addLast(e);
    }
}
