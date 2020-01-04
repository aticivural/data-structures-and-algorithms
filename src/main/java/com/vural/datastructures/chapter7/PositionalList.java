package com.vural.datastructures.chapter7;

import java.util.Iterator;

public interface PositionalList<E> extends Iterable<E>{

    int size();

    boolean isEmpty();

    Position<E> first();

    Position<E> last();

    Position<E> before(Position<E> p) throws IllegalStateException;

    Position<E> after(Position<E> p) throws IllegalStateException;

    Position<E> addFirst(E e);

    Position<E> addLast(E e);

    Position<E> addBefore(Position<E> p, E e) throws IllegalStateException;

    Position<E> addAfter(Position<E> p, E e) throws IllegalStateException;

    E set(Position<E> p, E e) throws IllegalStateException;

    E remove(Position<E> p) throws IllegalStateException;

    Iterator<E> iterator();

}