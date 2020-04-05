package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.Entry;

public interface SortedMap<K, V> extends Map<K, V> {

    Entry<K, V> firstEntry();

    Entry<K, V> lastEntry();

    Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException;

    Entry<K, V> floorEntry(K key) throws IllegalArgumentException;

    Entry<K, V> lowerEntry(K key) throws IllegalArgumentException;

    Entry<K, V> higherEntry(K key) throws IllegalArgumentException;

    Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;

}
