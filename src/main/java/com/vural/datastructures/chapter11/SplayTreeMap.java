package com.vural.datastructures.chapter11;

import com.vural.datastructures.chapter7.Position;
import com.vural.datastructures.chapter9.Entry;

import java.util.Comparator;

public class SplayTreeMap<K, V> extends TreeMap<K, V> {

    public SplayTreeMap() {
    }

    public SplayTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    private void splay(Position<Entry<K, V>> position) {
        while (!isRoot(position)) {
            Position<Entry<K, V>> parent = parent(position);
            Position<Entry<K, V>> grandParent = parent(parent);

            if (grandParent == null) {
                rotate(position);
            } else if ((parent == left(grandParent)) == (position == left(parent))) {
                rotate(parent);
                rotate(position);
            } else {
                rotate(position);
                rotate(position);
            }
        }
    }

    @Override
    protected void rebalanceAccess(Position<Entry<K, V>> position) {
        if (isExternal(position)) {
            position = parent(position);
        }
        if (position != null) {
            splay(position);
        }
    }


    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> position) {
        splay(position);
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> position) {
        if (!isRoot(position)) {
            splay(parent(position));
        }
    }
}
