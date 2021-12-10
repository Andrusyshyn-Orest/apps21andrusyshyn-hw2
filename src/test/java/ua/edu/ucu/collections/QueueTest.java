package ua.edu.ucu.collections;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueueTest extends TestCase {

    @org.junit.jupiter.api.Test
    public void testPeekException(){
        Queue queue = new Queue();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            queue.peek();;
        });
    }


    @Test
    public void peek() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @org.junit.jupiter.api.Test
    public void testDequeueException(){
        Queue queue = new Queue();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            queue.dequeue();;
        });
    }
    @Test
    public void dequeue() {
        Queue queue = new Queue();
        queue.enqueue(1);
        assertEquals(1, queue.peek());
    }

}
