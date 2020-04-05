package com.vural.datastructures.chapter10;

import java.util.ArrayList;

import com.vural.datastructures.chapter9.Entry;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
    }

    public ProbeHashMap(int capacity) {
        super(capacity);
    }

    public ProbeHashMap(int capacity, int prime) {
        super(capacity, prime);
    }

    @Override
    protected void createTable() {
        table = ((MapEntry<K, V>[]) new MapEntry[capacity]);
    }

    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    private int findSlot(int hashValue, K key) {
        int avail = -1;
        int j = hashValue;
        do {
            if (isAvailable(j)) {
                if (avail == -1) {
                    avail = j;
                }

                if (table[j] == null) {
                    break;
                }
            } else if (table[j].getKey().equals(key)) {
                return j;
            }

            j = (j + 1) % capacity;
        } while (j != hashValue);
        return -(avail + 1);
    }

    @Override
    protected V bucketGet(int hashValue, K key) {
        int j = findSlot(hashValue, key);
        if (j < 0) {
            return null;
        }
        return table[j].getValue();
    }

    @Override
    protected V bucketPut(int hashValue, K key, V value) {
        int j = findSlot(hashValue, key);
        if (j >= 0) {
            return table[j].setValue(value);
        }

        table[-(j + 1)] = new MapEntry<>(key, value);
        numberOfEntries++;
        return null;
    }

    @Override
    protected V bucketRemove(int hashValue, K key) {
        int j = findSlot(hashValue, key);
        if (j < 0) return null;

        V answer = table[j].getValue();
        table[j] = DEFUNCT;
        numberOfEntries--;
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (!isAvailable(h)) {
                buffer.add(table[h]);
            }
        }
        return buffer;
    }
}
