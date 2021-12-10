package ua.edu.ucu.collections;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest extends TestCase {
    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            stack.push(i);
        }
    }

    @org.junit.jupiter.api.Test
    public void testPopException(){
        Stack newStack = new Stack();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            newStack.pop();;
        });
    }

    @Test
    public void pop() {
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @org.junit.jupiter.api.Test
    public void testPeekException(){
        Stack newStack = new Stack();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            newStack.peek();;
        });
    }
    @Test
    public void peek() {
        assertEquals(5, stack.peek());
    }
}
