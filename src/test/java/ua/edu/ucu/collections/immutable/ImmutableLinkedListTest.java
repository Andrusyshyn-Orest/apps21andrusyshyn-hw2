package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableLinkedListTest extends TestCase {

    @Test
    public void testDefaultConstructor() {
        ImmutableLinkedList lst = new ImmutableLinkedList();
        assertEquals(lst.getHead(), null);
        assertEquals(lst.getTail(), null);
        assertEquals(lst.getFirst(), null);
        assertEquals(lst.getLast(), null);
        assertEquals(lst.size(), 0);
    }

    @Test
    public void testArrayConstructor() {
        Object[] arr = {"a", "b", "c"};
        ImmutableLinkedList lst = new ImmutableLinkedList(arr);
        assertArrayEquals(lst.toArray(), arr);
        arr[1] = "z";
        Object[] oldArr = {"a", "b", "c"};
        assertArrayEquals(lst.toArray(), oldArr);

        lst = new ImmutableLinkedList(new Object[0]);
        assertArrayEquals(lst.toArray(), new Object[0]);

        arr = new Object[]{1};
        lst = new ImmutableLinkedList(arr);
        assertArrayEquals(lst.toArray(), arr);
    }

    @Test
    public void testNodeConstructor() {
        Node head = new Node();
        head.setValue(1);
        ImmutableLinkedList lst = new ImmutableLinkedList(head);
        Object[] expArr = {1};
        assertArrayEquals(lst.toArray(), expArr);

        Node second = new Node();
        second.setValue(2);
        head.setNext(second);
        second.setPrevious(head);
        ImmutableLinkedList lst1 = new ImmutableLinkedList(head);


        Object[] expArr1 = {1, 2};
        assertArrayEquals(lst1.toArray(), expArr1);

        second.setValue(10);
        assertArrayEquals(lst1.toArray(), expArr1);
    }

    @Test
    public void testAdd() {
        ImmutableLinkedList lst = new ImmutableLinkedList();
        assertArrayEquals(lst.toArray(), new Object[0]);

        ImmutableLinkedList newArr = lst.add(1);
        Object[] expArr1 = {1};
        assertArrayEquals(newArr.toArray(), expArr1);

        newArr = newArr.add(2);
        newArr = newArr.add(3);

        Object[] expArr2 = {1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr2);

        newArr = newArr.addFirst(0);
        Object[] expArr3 = {0, 1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr3);


        newArr = newArr.addLast(4);
        Object[] expArr4 = {0, 1, 2, 3, 4};
        assertArrayEquals(newArr.toArray(), expArr4);

        assertEquals(newArr.getHead().getValue(), 0);
        assertEquals(newArr.getTail().getValue(), 4);
        assertEquals(newArr.getFirst().toString(), "0");
        assertEquals(newArr.getLast(), 4);
        assertEquals(newArr.size(), 5);


        newArr = newArr.clear();
        assertArrayEquals(newArr.toArray(), new Object[0]);

        newArr = newArr.addFirst(-1);
        Object[] expArr5 = {-1};
        assertArrayEquals(newArr.toArray(), expArr5);

    }

    @org.junit.jupiter.api.Test
    public void testAddIndexException(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(5, 5);;
        });

    }

    @org.junit.jupiter.api.Test
    public void testAddIndexException2(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(-1, 5);;
        });

    }

    @Test
    public void testAddIndex() {
        ImmutableLinkedList arr = new ImmutableLinkedList();
        assertArrayEquals(arr.toArray(), new Object[0]);

        ImmutableLinkedList newArr = arr.add(0, 1);
        Object[] expArr1 = {1};
        assertArrayEquals(newArr.toArray(), expArr1);

        newArr = newArr.add(0, 0);
        Object[] expArr2 = {0, 1};
        assertArrayEquals(newArr.toArray(), expArr2);

        newArr = newArr.add(2, 3);
        Object[] expArr3 = {0, 1, 3};
        assertArrayEquals(newArr.toArray(), expArr3);

        newArr = newArr.add(1, 0);
        Object[] expArr4 = {0, 0, 1, 3};
        assertArrayEquals(newArr.toArray(), expArr4);

        assertArrayEquals(arr.toArray(), new Object[0]);

    }

    @Test
    public void testAddAll() {
        ImmutableLinkedList arr = new ImmutableLinkedList();
        assertArrayEquals(arr.toArray(), new Object[0]);

        Object[] inpArr = new Object[0];
        ImmutableLinkedList newArr = arr.addAll(inpArr);
        assertArrayEquals(newArr.toArray(), new Object[0]);

        inpArr = new Object[]{1, 2, 3};
        newArr = newArr.addAll(inpArr);
        Object[] expArr1 = {1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr1);

        inpArr = new Object[0];
        newArr = newArr.addAll(inpArr);
        Object[] expArr2 = {1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr2);

        inpArr = new Object[]{4, 5};
        newArr = newArr.addAll(inpArr);
        Object[] expArr3 = {1, 2, 3, 4, 5};
        assertArrayEquals(newArr.toArray(), expArr3);

        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @org.junit.jupiter.api.Test
    public void testAddAllIndexException(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.addAll(5, new Object[] {1, 2});;
        });
    }

    @org.junit.jupiter.api.Test
    public void testAddAllIndexException2(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.addAll(-1, new Object[] {1, 2});;
        });
    }

    @Test
    public void testAddAllIndex(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        assertArrayEquals(arr.toArray(), new Object[0]);

        Object[] inpArr = new Object[0];
        ImmutableLinkedList newArr = arr.addAll(0, inpArr);
        assertArrayEquals(newArr.toArray(), new Object[0]);

        inpArr = new Object[]{1, 2, 3};
        newArr = newArr.addAll(0, inpArr);
        Object[] expArr1 = {1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr1);

        inpArr = new Object[0];
        newArr = newArr.addAll(1, inpArr);
        Object[] expArr2 = {1, 2, 3};
        assertArrayEquals(newArr.toArray(), expArr2);

        inpArr = new Object[]{4, 5};
        newArr = newArr.addAll(3, inpArr);
        Object[] expArr3 = {1, 2, 3, 4, 5};
        assertArrayEquals(newArr.toArray(), expArr3);

        inpArr = new Object[]{-1, 0};
        newArr = newArr.addAll(0, inpArr);
        Object[] expArr4 = {-1, 0, 1, 2, 3, 4, 5};
        assertArrayEquals(newArr.toArray(), expArr4);

        inpArr = new Object[]{5};
        newArr = newArr.addAll(3, inpArr);
        Object[] expArr5 = {-1, 0, 1, 5, 2, 3, 4, 5};
        assertArrayEquals(newArr.toArray(), expArr5);

        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @org.junit.jupiter.api.Test
    public void testGetException(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[] {1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.get(3);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testGetException2(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[] {1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.get(-1);;
        });
    }

    @Test
    public void testGet(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[] {1, 2, 3});
        assertArrayEquals(arr.toArray(), new Object[] {1, 2, 3});
        assertEquals(arr.get(0), 1);
        assertEquals(arr.get(1), 2);
        assertEquals(arr.get(2), 3);
        assertArrayEquals(arr.toArray(), new Object[] {1, 2, 3});
    }

    @org.junit.jupiter.api.Test
    public void testRemoveFirstException(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            arr.removeFirst();;
        });
    }

    @org.junit.jupiter.api.Test
    public void testRemoveLastException(){
        ImmutableLinkedList arr = new ImmutableLinkedList();
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            arr.removeLast();;
        });
    }

    @Test
    public void testRemoveFirstLast(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1});
        assertArrayEquals(arr.toArray(), new Object[]{1});
        ImmutableLinkedList newArr = arr.removeFirst();
        assertArrayEquals(newArr.toArray(), new Object[0]);
        newArr = arr.removeLast();
        assertArrayEquals(newArr.toArray(), new Object[0]);

        newArr = new ImmutableLinkedList(new Object[] {1, 2, 3, 4});
        newArr = newArr.removeFirst();
        assertArrayEquals(newArr.toArray(), new Object[]{2, 3, 4});

        newArr = newArr.removeLast();
        assertArrayEquals(newArr.toArray(), new Object[]{2, 3});

        assertArrayEquals(arr.toArray(), new Object[]{1});
    }

    @org.junit.jupiter.api.Test
    public void testRemoveException(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.remove(3);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testRemoveException2(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.remove(-1);;
        });
    }

    @Test
    public void testRemove(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3, 4, 5});
        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3, 4, 5});

        ImmutableLinkedList newArr = arr.remove(0);
        assertArrayEquals(newArr.toArray(), new Object[]{2, 3, 4, 5});

        newArr = newArr.remove(3);
        assertArrayEquals(newArr.toArray(), new Object[]{2, 3, 4});

        newArr = newArr.remove(1);
        assertArrayEquals(newArr.toArray(), new Object[]{2, 4});

        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3, 4, 5});

    }

    @org.junit.jupiter.api.Test
    public void testSetException(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.set(3, 5);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testSetException2(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.set(-1, 5);;
        });
    }

    @Test
    public void testSet(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3});

        ImmutableLinkedList newArr = arr.set(0, 0);
        assertArrayEquals(newArr.toArray(), new Object[]{0, 2, 3});

        newArr = newArr.set(1, 1);
        assertArrayEquals(newArr.toArray(), new Object[]{0, 1, 3});

        newArr = newArr.set(2, 2);
        assertArrayEquals(newArr.toArray(), new Object[]{0, 1, 2});

        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3});

    }

    @Test
    public void testIndexOf(){
        ImmutableLinkedList arr = new ImmutableLinkedList(new Object[]{1, 2, 3});
        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3});

        assertEquals(arr.indexOf(2), 1);
        assertEquals(arr.indexOf(1), 0);
        assertEquals(arr.indexOf(3), 2);
        assertEquals(arr.indexOf("a"), -1);

        assertArrayEquals(arr.toArray(), new Object[]{1, 2, 3});

    }

}