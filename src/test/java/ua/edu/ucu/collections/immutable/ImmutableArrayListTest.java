package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableArrayListTest extends TestCase {


    @Test
    public void testAddToEmpty() {
        ImmutableArrayList arr = new ImmutableArrayList();
        assertArrayEquals(arr.toArray(), new Object[0]);

        ImmutableArrayList newArr =  arr.add(4);
        newArr =  newArr.add(7);
        newArr =  newArr.add(4);

        Object [] expected = {4, 7, 4};
        Object[] actual = newArr.toArray();
        assertArrayEquals(expected, actual);

        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @Test
    public void testAddToNotEmpty() {
        Object [] inputArr = {1, 2 ,3};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr =  arr.add(4);
        newArr =  newArr.add(7);
        newArr =  newArr.add(4);

        Object [] expected = {1, 2, 3, 4, 7, 4};
        Object[] actual = newArr.toArray();
        assertArrayEquals(expected, actual);

        assertArrayEquals(arr.toArray(), inputArr);
    }

    @org.junit.jupiter.api.Test
    public void testAddIndexException(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(4, 5);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testAddIndexException2(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(-1, 5);;
        });
    }

    @Test
    public void testAddIndexToEmpty() {
        ImmutableArrayList arr = new ImmutableArrayList();
        assertArrayEquals(arr.toArray(), new Object[0]);
        ImmutableArrayList newArr = arr.add(0, "a");
        newArr = newArr.add(1, "b");
        newArr = newArr.add(2, "c");
        newArr = newArr.add(0, "d");
        Object []expected = {"d", "a", "b", "c"};
        assertArrayEquals(expected, newArr.toArray());
        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @Test
    public void testAddIndexToNotEmpty() {
        Object [] inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);
        ImmutableArrayList newArr = arr.add(0, "a");
        newArr = newArr.add(1, "b");
        newArr = newArr.add(2, "c");
        newArr = newArr.add(0, "d");
        Object []expected = {"d", "a", "b", "c", "a", "b", "c"};
        assertArrayEquals(expected, newArr.toArray());
        assertArrayEquals(arr.toArray(), inputArr);
    }


    @org.junit.jupiter.api.Test
    public void testAddAllIndexException(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(4, new Object[] {1, 2});;
        });
    }

    @org.junit.jupiter.api.Test
    public void testAddAllIndexException2(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.add(-1, new Object[] {1, 2});;
        });
    }

    @Test
    public void testAddAllToEmpty() {
        ImmutableArrayList arr = new ImmutableArrayList();
        assertArrayEquals(arr.toArray(), new Object[0]);
        Object []inputArr = {"a", "b", "c"};
        ImmutableArrayList newArr = arr.addAll(inputArr);
        assertArrayEquals(newArr.toArray(), inputArr);

        newArr = newArr.addAll(inputArr);
        Object []expArr = {"a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @Test
    public void testAddAllToNotEmpty() {
        Object []inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr = arr.addAll(inputArr);
        Object []expArr = {"a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        assertArrayEquals(arr.toArray(), inputArr);
    }

    @Test
    public void testAddAllIndexToEmpty() {
        ImmutableArrayList arr = new ImmutableArrayList();
        assertArrayEquals(arr.toArray(), new Object[0]);
        Object []inputArr = {"a", "b", "c"};
        ImmutableArrayList newArr = arr.addAll(inputArr);
        assertArrayEquals(newArr.toArray(), inputArr);

        newArr = newArr.addAll(inputArr);
        Object []expArr = {"a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        newArr = newArr.addAll(3, inputArr);
        Object []expArr1 = {"a", "b", "c", "a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr1);

        newArr = newArr.addAll(0, inputArr);
        Object []expArr2 = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr2);

        assertArrayEquals(arr.toArray(), new Object[0]);
    }

    @Test
    public void testAddAllIndexToNotEmpty() {
        Object []inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr = arr.addAll(inputArr);
        Object []expArr = {"a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        newArr = newArr.addAll(3, inputArr);
        Object []expArr1 = {"a", "b", "c", "a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr1);

        newArr = newArr.addAll(0, inputArr);
        Object []expArr2 = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr2);

        assertArrayEquals(arr.toArray(), inputArr);
    }


    @org.junit.jupiter.api.Test
    public void testGetException(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.get(3);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testGetException2(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.get(-1);;
        });
    }

    @Test
    public void testGet(){
        Object[] inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);
        assertEquals(inputArr[1], arr.get(1));
        Object el = arr.get(1);
        inputArr[1] = "z";
        inputArr = new Object[]{"a", "b", "c"};
        assertArrayEquals(arr.toArray(), inputArr);
    }

    @org.junit.jupiter.api.Test
    public void testRemoveException(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.remove(3);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testRemoveException2(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.remove(-1);;
        });
    }

    @Test
    public void testRemove(){
        Object[] inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr = arr.remove(1);
        Object[] expArr = {"a", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        newArr = newArr.remove(1);
        expArr = new Object[]{"a"};
        assertArrayEquals(newArr.toArray(), expArr);

        newArr = newArr.remove(0);
        expArr = new Object[]{};
        assertArrayEquals(newArr.toArray(), expArr);

        assertArrayEquals(arr.toArray(), inputArr);

    }

    @org.junit.jupiter.api.Test
    public void testSetException(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.set(3, 5);;
        });
    }

    @org.junit.jupiter.api.Test
    public void testSetException2(){
        ImmutableArrayList arr = new ImmutableArrayList(new Object[]{1, 2, 3});
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            arr.set(-1, 5);;
        });
    }

    @Test
    public void testSet(){
        Object[] inputArr = {"a", "b", "c"};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr = arr.set(0, "z");
        Object[] expArr = {"z", "b", "c"};
        assertArrayEquals(newArr.toArray(), expArr);

        assertArrayEquals(arr.toArray(), inputArr);
    }

    @Test
    public void testIndexOf(){
        Object[] inputArr = {1, 2 ,3};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        int expInd = arr.indexOf(2);
        assertEquals(expInd, 1);

        int expInd1 = arr.indexOf("a");
        assertEquals(expInd1, -1);

        assertArrayEquals(arr.toArray(), inputArr);

    }

    @Test
    public void testSize(){
        Object[] inputArr = {1, 2 ,3};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        int expSize = arr.size();
        assertEquals(expSize, 3);

        assertArrayEquals(arr.toArray(), inputArr);
    }

    @Test
    public void testClear(){
        Object[] inputArr = {1, 2 ,3};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

        ImmutableArrayList newArr = arr.clear();
        assertArrayEquals(newArr.toArray(), new Object[0]);

        assertArrayEquals(arr.toArray(), inputArr);
    }

    @Test
    public void testIsEmpty(){
        ImmutableArrayList arr = new ImmutableArrayList();
        assertTrue(arr.isEmpty());
        ImmutableArrayList newArr = arr.add(1);
        assertFalse(newArr.isEmpty());
    }

    @Test
    public void testToArray(){
        Object[] inputArr = {1, 2 ,3};
        ImmutableArrayList arr = new ImmutableArrayList(inputArr);
        assertArrayEquals(arr.toArray(), inputArr);

    }
}