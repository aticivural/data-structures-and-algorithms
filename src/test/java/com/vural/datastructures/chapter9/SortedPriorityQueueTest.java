package com.vural.datastructures.chapter9;

import org.junit.Test;

public class SortedPriorityQueueTest {

    @Test
    public void test() {
        SortedPriorityQueue<Integer, Integer> queue = new SortedPriorityQueue<>();
        queue.insert(4, 4);
        queue.insert(2, 2);
        queue.insert(1, 1);
        queue.insert(3, 3);
    }

}