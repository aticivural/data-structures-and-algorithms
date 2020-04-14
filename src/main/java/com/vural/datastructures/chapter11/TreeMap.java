package com.vural.datastructures.chapter11;

import com.vural.datastructures.chapter10.AbstractSortedMap;
import com.vural.datastructures.chapter7.Position;
import com.vural.datastructures.chapter8.LinkedBinaryTree;
import com.vural.datastructures.chapter9.Entry;

import java.util.ArrayList;

public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    public TreeMap() {
        super();
        tree.addRoot(null);
    }

    public int size() {
        return (tree.size() - 1) / 2;
    }

    private void expandExternal(Position<Entry<K, V>> position, Entry<K, V> entry) {
        tree.set(position, entry);
        tree.addLeft(position, null);
        tree.addRight(position, null);
    }

    protected Position<Entry<K, V>> root() {
        return tree.root();
    }

    protected Position<Entry<K, V>> right(Position<Entry<K, V>> position) {
        return tree.right(position);
    }

    protected Position<Entry<K, V>> left(Position<Entry<K, V>> position) {
        return tree.left(position);
    }

    protected Position<Entry<K, V>> sibling(Position<Entry<K, V>> p) {
        return tree.sibling(p);
    }

    protected boolean isRoot(Position<Entry<K, V>> p) {
        return tree.isRoot(p);
    }

    protected boolean isExternal(Position<Entry<K, V>> position) {
        return tree.isExternal(position);
    }

    protected boolean isInternal(Position<Entry<K, V>> left) {
        return tree.isInternal(left);
    }

    protected void set(Position<Entry<K, V>> position, Entry<K, V> entry) {
        tree.set(position, entry);
    }

    protected Entry<K, V> remove(Position<Entry<K, V>> position) {
        return tree.remove(position);
    }

    protected void rotate(Position<Entry<K, V>> position) {
        tree.rotate(position);
    }

    protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> position) {
        return tree.restructure(position);
    }

    protected Position<Entry<K, V>> parent(Position<Entry<K, V>> position) {
        return tree.parent(position);
    }

    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> position, K key) {
        if (isExternal(position)) {
            return position;   //key not found, return the final leaf
        }

        int compare = compare(key, position.getElement().getKey());
        if (compare == 0) {
            return position;
        } else if (compare < 0) {
            return treeSearch(left(position), key);
        } else {
            return treeSearch(right(position), key);
        }
    }


    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> p) {
        Position<Entry<K, V>> walk = p;
        while (isInternal(walk)) {
            walk = left(walk);
        }
        return parent(walk);              // we want the parent of the leaf
    }


    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return treeMin(root()).getElement();
    }

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }

        return treeMax(root()).getElement();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        checkKey(key);                              // may throw IllegalArgumentException
        Position<Entry<K, V>> p = treeSearch(root(), key);
        if (isInternal(p)) {
            return p.getElement();   // exact match
        }
        while (!isRoot(p)) {
            if (p == left(parent(p))) {
                return parent(p).getElement();          // parent has next greater key
            } else {
                p = parent(p);
            }
        }
        return null;                                // no such ceiling exists
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position)) {         // exact match
            return position.getElement();
        }

        while (!isRoot(position)) {
            if (position == right(parent(position))) {
                return parent(position).getElement();   // parent has next lesser key
            } else {
                position = parent(position);
            }
        }

        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position) && isInternal(left(position))) {
            return treeMax(left(position)).getElement();        // this is the predecessor to p
        }

        // otherwise, we had failed search, or match with no left child
        while (!isRoot(position)) {
            if (position == right(parent(position))) {
                return parent(position).getElement();   // parent has next lesser key
            } else {
                position = parent(position);
            }
        }

        return null;            // no such lesser key exists
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        checkKey(key);                               // may throw IllegalArgumentException
        Position<Entry<K, V>> p = treeSearch(root(), key);
        if (isInternal(p) && isInternal(right(p))) {
            return treeMin(right(p)).getElement();     // this is the successor to p
        }    // otherwise, we had failed search, or match with no right child
        while (!isRoot(p)) {
            if (p == left(parent(p))) {
                return parent(p).getElement();           // parent has next lesser key
            } else {
                p = parent(p);
            }
        }
        return null;                                 // no such greater key exists
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());

        if (compare(fromKey, toKey) < 0) {                  // ensure that fromKey < toKey
            subMapRecurse(fromKey, toKey, root(), buffer);
        }

        return buffer;
    }

    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> position, ArrayList<Entry<K, V>> buffer) {
        if (isInternal(position)) {
            if (compare(position.getElement().getKey(), fromKey) < 0) {
                // position's key is less than fromKey, so any relevant entries are to the right
                subMapRecurse(fromKey, toKey, right(position), buffer);
            } else {
                subMapRecurse(fromKey, toKey, left(position), buffer);           // first consider left subtree
                if (compare(position.getElement().getKey(), toKey) < 0) {        // position is within range
                    buffer.add(position.getElement());                           // so add it to buffer, and consider
                    subMapRecurse(fromKey, toKey, right(position), buffer);      // right subtree as well
                }
            }
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(size());

        for (Position<Entry<K, V>> position : tree.inorder()) {
            if (isInternal(position)) {
                buffer.add(position.getElement());
            }
        }

        return buffer;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        rebalanceAccess(position);
        if (isExternal(position)) {
            return null;
        }
        return position.getElement().getValue();
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isExternal(position)) {
            expandExternal(position, newEntry);
            rebalanceInsert(position);
            return null;
        } else {
            V old = position.getElement().getValue();
            set(position, newEntry);
            rebalanceAccess(position);
            return old;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isExternal(position)) {
            rebalanceAccess(position);
            return null;
        } else {
            V old = position.getElement().getValue();
            if (isInternal(left(position)) && isInternal(right(position))) {
                Position<Entry<K, V>> replacement = treeMax(left(position));
                set(position, replacement.getElement());
                position = replacement;
            }
            Position<Entry<K, V>> leaf = (isExternal(left(position)) ? left(position) : right(position));
            Position<Entry<K, V>> sib = sibling(leaf);
            remove(leaf);
            remove(position);
            rebalanceDelete(sib);
            return old;
        }
    }

    private Position<Entry<K, V>> treeMax(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> walk = position;
        while (isInternal(walk)) {
            walk = right(walk);
        }

        return parent(walk);
    }

    protected void rebalanceInsert(Position<Entry<K, V>> p) {
    }

    protected void rebalanceDelete(Position<Entry<K, V>> p) {
    }

    protected void rebalanceAccess(Position<Entry<K, V>> p) {
    }


    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        protected static class BSTNode<E> extends LinkedBinaryTree.Node<E> {
            int aux = 0;

            BSTNode(E element, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
                super(element, parent, leftChild, rightChild);
            }

            public int getAux() {
                return aux;
            }

            public void setAux(int aux) {
                this.aux = aux;
            }
        }

        public int getAux(Position<Entry<K, V>> position) {
            return ((BSTNode<Entry<K, V>>) position).getAux();
        }

        public void setAux(Position<Entry<K, V>> position, int value) {
            ((BSTNode<Entry<K, V>>) position).setAux(value);
        }

        protected Node<Entry<K, V>> createNode(Entry<K, V> element, Node<Entry<K, V>> parent, Node<Entry<K, V>> left, Node<Entry<K, V>> right) {
            return new BSTNode<>(element, parent, left, right);
        }

        private void relink(Node<Entry<K, V>> parent, Node<Entry<K, V>> child, boolean makeLeftChild) {
            child.setParent(parent);
            if (makeLeftChild) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        public void rotate(Position<Entry<K, V>> position) {
            Node<Entry<K, V>> x = validate(position);
            Node<Entry<K, V>> y = x.getParent();
            Node<Entry<K, V>> z = y.getParent();

            if (z == null) {
                root = x;    // x becomes root of the tree
                x.setParent(null);
            } else {
                relink(z, x, y == z.getLeft()); // x becomes direct child of z
            }

            //now rotate x and y, including transfer of middle subtree
            if (x == y.getLeft()) {
                relink(y, x.getRight(), true);
                relink(x, y, false);
            } else {
                relink(y, x.getLeft(), false);
                relink(x, y, true);
            }
        }

        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
            Position<Entry<K, V>> y = parent(x);
            Position<Entry<K, V>> z = parent(y);
            if ((x == right(y)) == (y == right(z))) {
                rotate(y);
                return y;
            } else {
                rotate(x);
                rotate(x);
                return x;
            }
        }
    }
}



















