package com.vural.datastructures.chapter11;

import com.vural.datastructures.chapter7.Position;
import com.vural.datastructures.chapter9.Entry;

import java.util.Comparator;

public class RBTreeMap<K, V> extends TreeMap<K, V> {
    public RBTreeMap() {
    }

    public RBTreeMap(Comparator<K> comparator) {
        super(comparator);
    }

    //black=0 and red=1

    private boolean isBlack(Position<Entry<K, V>> position) {
        return tree.getAux(position) == 0;
    }

    private boolean isRed(Position<Entry<K, V>> position) {
        return tree.getAux(position) == 1;
    }

    private void makeBlack(Position<Entry<K, V>> position) {
        tree.setAux(position, 0);
    }

    private void makeRed(Position<Entry<K, V>> position) {
        tree.setAux(position, 1);
    }

    private void setColor(Position<Entry<K, V>> position, boolean toRed) {
        tree.setAux(position, toRed ? 1 : 0);
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> position) {
        if (!isRoot(position)) {
            makeRed(position);
            resolveRed(position);
        }
    }

    private void resolveRed(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> parent, uncle, middle, grand;
        parent = parent(position);
        if (isRed(parent)) {
            uncle = sibling(parent);
            if (isBlack(uncle)) {
                middle = restructure(position);
                makeBlack(middle);
                makeRed(left(middle));
                makeRed(right(middle));
            } else {
                makeBlack(parent);
                makeBlack(uncle);
                grand = parent(parent);
                if (!isRoot(grand)) {
                    makeRed(grand);
                    resolveRed(grand);
                }
            }
        }
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> position) {
        if (isRed(position)) {
            makeBlack(position);
        } else if (!isRoot(position)) {
            Position<Entry<K, V>> sibling = sibling(position);
            if (isInternal(sibling) && (isBlack(sibling) || isInternal(sibling))) {
                remedyDoubleBlack(position);
            }
        }
    }

    private void remedyDoubleBlack(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> z = parent(position);
        Position<Entry<K, V>> y = sibling(position);

        if (isBlack(y)) {
            if (isRed(left(y)) || isRed(right(y))) {
                Position<Entry<K, V>> x = isRed(left(y)) ? left(y) : right(y);
                Position<Entry<K, V>> middle = restructure(x);
                setColor(middle, isRed(z));
                makeBlack(left(middle));
                makeBlack(right(middle));
            } else {
                makeRed(y);
                if (isRed(z)) {
                    makeBlack(z);
                } else if (!isRoot(z)) {
                    remedyDoubleBlack(z);
                }
            }
        } else {
            rotate(y);
            makeBlack(y);
            makeRed(z);
            remedyDoubleBlack(position);
        }
    }
}

