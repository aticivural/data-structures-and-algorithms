package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.Entry;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V> {
    protected int numberOfEntries = 0;
    protected int capacity;
    private int prime;
    private long scale, shift;

    public AbstractHashMap(int capacity, int prime) {
        this.prime = prime;
        this.capacity = capacity;
        Random random = new Random();
        scale = random.nextInt(this.prime - 1) + 1;
        shift = random.nextInt(this.prime);
        createTable();
    }

    public AbstractHashMap(int capacity) {
        this(capacity, 109345121); // default prime

    }

    public AbstractHashMap() {
        this(17); // default capacity
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    @Override
    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    @Override
    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (numberOfEntries > capacity / 2) {
            resize(2 * capacity - 1);
        }
        return answer;
    }

    private int hashValue(K key) {
        return ((int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity));
    }

    private void resize(int newCapacity) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(newCapacity);
        for (Entry<K, V> entry : entrySet()) {
            buffer.add(entry);
        }

        capacity = newCapacity;
        createTable();
        numberOfEntries = 0;
        for (Entry<K, V> entry : buffer) {
            put(entry.getKey(), entry.getValue());
        }
    }


    protected abstract void createTable();

    protected abstract V bucketGet(int hashValue, K key);

    protected abstract V bucketPut(int hashValue, K key, V value);

    protected abstract V bucketRemove(int hashValue, K key);

}
