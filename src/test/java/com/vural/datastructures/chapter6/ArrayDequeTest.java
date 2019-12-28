package com.vural.datastructures.chapter6;

import org.junit.Test;

public class ArrayDequeTest {

    @Test
    public void deque_test(){
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>(3);
        deque.addFirst(1);
        deque.addLast(2);
        assert  2 == deque.removeLast();
        assert  1 == deque.removeLast();

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assert deque.removeLast() == 3;
        assert deque.removeFirst() == 1;


    }

}