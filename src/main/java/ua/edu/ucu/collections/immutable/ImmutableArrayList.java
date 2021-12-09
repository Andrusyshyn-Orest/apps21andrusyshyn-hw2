package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] elements;
    private final int length;
    public ImmutableArrayList(Object[] arrElements) {
        this.elements = Arrays.copyOf(arrElements, arrElements.length);
        this.length = arrElements.length;
    }

    public ImmutableArrayList() {
        this.elements = new Object[0];
        this.length = 0;
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return this.add(this.length, e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        if (!( (0 <= index) && (index <= this.length) )){
            throw new IllegalArgumentException();
        }
        Object [] newArr = Arrays.copyOf(this.elements, this.length+1);
        for (int i = index + 1; i < this.length + 1; i++) {
            newArr[i] = this.elements[i-1];
        }
        newArr[index] = e;
        return new ImmutableArrayList(newArr);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(this.length, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (!( (0 <= index) && (index <= this.length) )){
            throw new IllegalArgumentException();
        }
        Object [] newArr = Arrays.copyOf(this.elements, this.length+c.length);
        for (int i = index + c.length; i < newArr.length; i++) {
            newArr[i] = this.elements[i-c.length];
        }
        for (int i = index; i < index + c.length; i++) {
            newArr[i] = c[i-index];
        }

        return new ImmutableArrayList(newArr);
    }

    @Override
    public Object get(int index) {
        if (!( (0 <= index) && (index < this.length) )){
            throw new IllegalArgumentException();
        }
        return this.toArray()[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        if (!( (0 <= index) && (index < this.length) )){
            throw new IllegalArgumentException();
        }
        Object [] newArr = Arrays.copyOf(this.elements, this.length-1);
        for (int i = index; i < newArr.length; i++) {
            newArr[index] = this.elements[index+1];
        }
        return new ImmutableArrayList(newArr);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        if (!( (0 <= index) && (index < this.length) )){
            throw new IllegalArgumentException();
        }
        Object[] newArr = this.toArray();
        newArr[index] = e;
        return new ImmutableArrayList(newArr);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < this.length; i++) {
            if (this.elements[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return (this.length == 0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.length);
    }
}
