package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.Entry;

public class CostPerformanceDatabase {

    public SortedMap<Integer, Integer> map = new SortedTableMap<>();

    public CostPerformanceDatabase() {
    }

    public Entry<Integer, Integer> best(int cost) {
        return map.floorEntry(cost);
    }

    public void add(int cost, int performance) {
        Entry<Integer, Integer> other = map.floorEntry(cost);
        if (other != null && other.getValue() >= performance) {
            return;
        }
        map.put(cost, performance);
        other = map.higherEntry(cost);

        while (other != null && other.getValue() <= performance) {
            map.remove(other.getKey());
            other = map.higherEntry(cost);
        }
    }
}
