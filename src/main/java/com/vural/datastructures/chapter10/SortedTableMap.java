package com.vural.datastructures.chapter10;

import com.vural.datastructures.chapter9.Entry;

import java.util.ArrayList;
import java.util.Comparator;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {

    private ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    public SortedTableMap() {
        super();
    }

    public SortedTableMap(Comparator<K> comparator) {
        super(comparator);
    }

    private int findIndex(K key, int low, int high) {
        if (high < low) {
            return high + 1;
        }
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid).getKey());
        if (comp == 0) {
            return mid;
        } else if (comp < 0) {
            return findIndex(key, low, mid - 1);
        } else {
            return findIndex(key, mid + 1, high);
        }
    }

    private int findIndex(K key) {
        return this.findIndex(key, 0, table.size() - 1);
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        if (size() == index || compare(key, table.get(index).getKey()) != 0) {
            return null;
        }
        return table.get(index).getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = findIndex(key);
        if (index < size() && compare(key, table.get(index).getKey()) == 0) {
            return table.get(index).setValue(value);
        }
        table.add(index, new MapEntry<>(key, value));
        return null;
    }

    @Override
    public V remove(K key) {
        int index = findIndex(key);
        if (index == size() || compare(key, table.get(index).getKey()) != 0) {
            return null;
        }
        return table.remove(index).getValue();
    }

    private Entry<K, V> safeEntry(int index) {
        if (index < 0 || index >= table.size()) {
            return null;
        }
        return table.get(index);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    @Override
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size() - 1);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key));
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        int index = findIndex(key);
        if (index == size() || !key.equals(table.get(index).getKey())) {
            index--;
        }
        return safeEntry(index);
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key) - 1);
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        int index = findIndex(key);
        if (index < size() && key.equals(table.get(index).getKey())) {
            index++;
        }
        return safeEntry(index);
    }

    private Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int j = startIndex;
        while (j < table.size() && (stop == null || compare(stop, table.get(j).getKey()) > 0)) {
            buffer.add(table.get(j++));
        }
        return buffer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return snapshot(0, null);
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        return snapshot(findIndex(fromKey), toKey);
    }
}