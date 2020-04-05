package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.DefaultComparator;

import java.util.Comparator;

public abstract class AbstractSortedMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {

    private Comparator comparator;

    public AbstractSortedMap() {
        this(new DefaultComparator<>());
    }

    public AbstractSortedMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    protected int compare(K key1, K key2) {
        return comparator.compare(key1, key2);
    }
}
