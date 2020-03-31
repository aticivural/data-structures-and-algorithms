package com.vural.datastructures.chapter7;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO implement missing methods(like add())
public class ArrayList<E> implements List<E> {
    private static int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E answer = data[i];
        data[i] = e;
        return answer;
    }

    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            throw new IllegalStateException("Array is full");
        }
        for (int k = size - 1; k >= i; k--) {
            data[k + 1] = data[k];
        }
        data[i] = e;
        size++;

    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E answer = data[i];
        for (int k = i; k < size - 1; k++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return answer;
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i > n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    private class ArrayIterator implements Iterator<E> {

        private int j = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (j == size) {
                throw new NoSuchElementException("No next element");
            }
            removable = true;
            return data[j++];
        }

        @Override
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("nothing to remove");
            }
            ArrayList.this.remove(j - 1);
            j--;
            removable = false;
        }
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
