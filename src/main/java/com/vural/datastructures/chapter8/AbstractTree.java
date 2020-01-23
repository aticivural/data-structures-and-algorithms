package com.vural.datastructures.chapter8;

import com.vural.datastructures.chapter6.LinkedQueue;
import com.vural.datastructures.chapter6.Queue;
import com.vural.datastructures.chapter7.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {

    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    private int heightBad() {
        int h = 0;
        for (Position<E> p : positions())
            if (isExternal(p))
                h = Math.max(h, depth(p));
        return h;
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }

    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue();
                snapshot.add(p);
                for (Position<E> c : children(p)) {
                    fringe.enqueue(c);
                }
            }
        }
        return snapshot;
    }

    public static <E> void printPreorderIndent(Tree<E> t, Position<E> p, int d) {
        System.out.println(spaces(2 * d) + p.getElement());
        for (Position<E> c : t.children(p)) {
            printPreorderIndent(t, c, d + 1);
        }
    }

    public static <E> void printPreorderLabeled(Tree<E> t, Position<E> p, ArrayList<Integer> path) {
        int d = path.size();
        System.out.println(spaces(2 * d));
        for (int j = 0; j < d; j++) {
            System.out.print(path.get(j) + (j == d - 1 ? " " : "."));
        }
        System.out.println(p.getElement());
        path.add(1);
        for (Position<E> c : t.children(p)) {
            printPreorderLabeled(t, c, path);
            path.set(d, 1 + path.get(d));
        }
        path.remove(d);
    }

    private static String spaces(int length) {
        String spaces = "";
        for (int i = 0; i < length; i++) {
            spaces += " ";
        }
        return spaces;
    }

    public int diskSpace(Tree<Integer> t, Position<Integer> p) {
        int subTotal = p.getElement();
        for (Position<Integer> c : t.children(p)) {
            subTotal += diskSpace(t, c);
        }
        return subTotal;
    }

    public static <E> void parenthesize(Tree<E> t, Position<E> p) {
        System.out.print(p.getElement());
        if (t.isInternal(p)) {
            boolean firstTime = true;
            for (Position<E> c : t.children(p)) {
                System.out.print((firstTime ? " (" : ", "));
                firstTime = false;
                parenthesize(t, c);
            }
            System.out.println(")");
        }
    }

}
