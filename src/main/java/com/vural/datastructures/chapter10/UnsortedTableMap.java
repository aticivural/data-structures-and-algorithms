package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {

    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    private int findIndex(K key) {
        int n = table.size();
        for (int i = 0; i < n; i++) {
            if (table.get(n).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return null;
        }
        return table.get(index).getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = findIndex(key);
        if (index == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            return table.get(index).setValue(value);
        }
    }

    @Override
    public V remove(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return null;
        }
        V answer = table.get(index).getValue();
        if (index != size() - 1) {
            table.set(index, table.get(size() - 1));
        }
        table.remove(size() - 1);
        return answer;
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < table.size();
        }

        @Override
        public Entry<K, V> next() {
            if (i == table.size()) {
                throw new NoSuchElementException();
            }
            return table.get(i++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }
}
