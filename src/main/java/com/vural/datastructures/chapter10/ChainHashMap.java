package com.vural.datastructures.chapter10;

import java.util.ArrayList;

import com.vural.datastructures.chapter9.Entry;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {

    private UnsortedTableMap<K, V>[] table;

    public ChainHashMap() {
    }

    public ChainHashMap(int capacity) {
        super(capacity);
    }

    public ChainHashMap(int capacity, int prime) {
        super(capacity, prime);
    }

    @Override
    protected void createTable() {
        table = ((UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity]);
    }

    @Override
    protected V bucketGet(int hashValue, K key) {
        UnsortedTableMap<K, V> bucket = table[hashValue];
        if (bucket == null) {
            return null;
        }
        return bucket.get(key);
    }

    @Override
    protected V bucketPut(int hashValue, K key, V value) {
        UnsortedTableMap<K, V> bucket = table[hashValue];
        if (bucket == null) {
            bucket = table[hashValue] = new UnsortedTableMap<>();
        }
        int oldSize = bucket.size();
        V answer = bucket.put(key, value);
        numberOfEntries += (bucket.size() - oldSize); // size may have increased
        return answer;
    }

    @Override
    protected V bucketRemove(int hashValue, K key) {
        UnsortedTableMap<K, V> bucket = table[hashValue];
        if (bucket == null) {
            return null;
        }

        int oldSize = bucket.size();
        V answer = bucket.remove(key);
        numberOfEntries = (oldSize - bucket.size()); // size may have decreased
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i].entrySet()) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }
}
