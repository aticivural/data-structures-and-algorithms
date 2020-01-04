package com.vural.datastructures.chapter7;

import java.util.Iterator;

public class FavoritesList<E> {

    protected static class Item<E> {
        private E value;
        private int count = 0;

        public Item(E val) {
            this.value = val;
        }

        public int getCount() {
            return count;
        }

        public E getValue() {
            return value;
        }

        public void increment() {
            count++;
        }
    }

    PositionalList<Item<E>> list = new LinkedPositionalList<>();

    public FavoritesList() {
    }

    protected E value(Position<Item<E>> p) {
        return p.getElement().getValue();
    }

    protected int count(Position<Item<E>> p) {
        return p.getElement().getCount();
    }

    protected Position<Item<E>> findPosition(E e) {
        Position<Item<E>> walk = list.first();
        while (walk != null && !e.equals(value(walk))) {
            walk = list.after(walk);
        }
        return walk;
    }

    protected void moveUp(Position<Item<E>> p) {
        int cnt = count(p);
        Position<Item<E>> walk = p;
        while (walk != list.first() && count(list.before(walk)) < cnt) {
            walk = list.before(walk);
        }
        if (walk != p) {
            list.addBefore(walk, list.remove(p));
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void access(E e) {
        Position<Item<E>> p = findPosition(e);
        if (p == null) {
            list.addLast(new Item<>(e));
        }
        p.getElement().increment();
        moveUp(p);
    }

    public void remove(E e) {
        Position<Item<E>> p = findPosition(e);
        if (p != null) {
            list.remove(p);
        }
    }

    public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("Invalid k");
        }
        PositionalList<E> result = new LinkedPositionalList<>();
        Iterator<Item<E>> iter = list.iterator();
        for (int j = 0; j < k; j++) {
            result.addLast(iter.next().getValue());
        }
        return result;
    }
}
