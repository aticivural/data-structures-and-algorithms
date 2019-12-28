package com.vural.datastructures.chapter6;

public class ArrayDeque<E> implements Deque<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private int head = -1;
    private int tail = 0;
    private int size = 0;

    public ArrayDeque() {
        this(CAPACITY);
    }

    public ArrayDeque(int capacity) {
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
    public E first() {
        if (isEmpty()) return null;
        return data[head];
    }

    @Override
    public E last() {
        if (isEmpty()) return null;
        return data[tail];
    }

    @Override
    public void addFirst(E e) {
        if (isFull()) throw new IllegalStateException("Deque is full");
        if (isInitial()) {
            head = 0;
        } else {
            head = (head - 1 + data.length) % data.length;
        }
        data[head] = e;
        size++;

    }

    @Override
    public void addLast(E e) {
        if (isFull()) throw new IllegalStateException("Deque is full");
        if (isInitial()) {
            head = 0;
        } else {
            tail = (tail + 1) % data.length;
        }
        data[tail] = e;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = data[head];
        data[head] = null;
        head = (head + 1)% data.length;
        size --;
        return answer;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;
        E answer = data[tail];
        data[tail] = null;
        tail = (tail - 1 + data.length) % data.length;
        size --;
        return answer;
    }

    private boolean isInitial() {
        return head == -1 && tail == 0;
    }

    private boolean isFull() {
        return size == data.length;
    }
}
