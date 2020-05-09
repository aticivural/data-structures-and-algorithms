package com.vural.datastructures.chapter12;

import com.vural.datastructures.chapter6.LinkedQueue;
import com.vural.datastructures.chapter6.Queue;

import java.util.Comparator;

public class QuickSort {

    //Quick-sort contents of a queue.
    public static <K> void quickSort(Queue<K> S, Comparator<K> comp) {
        int n = S.size();
        if (n < 2) return;

        Queue<K> L = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();

        K pivot = S.first();
        while (!S.isEmpty()) {
            K element = S.dequeue();
            int compare = comp.compare(element, pivot);
            if (compare < 0) {
                L.enqueue(element);
            } else if (compare == 0) {
                E.enqueue(element);
            } else {
                G.enqueue(element);
            }
        }

        quickSort(L, comp);
        quickSort(G, comp);

        while (!L.isEmpty()) {
            S.enqueue(L.dequeue());
        }
        while (!E.isEmpty()) {
            S.enqueue(E.dequeue());
        }
        while (!G.isEmpty()) {
            S.enqueue(G.dequeue());
        }

    }


    /*-----------------------------------------------
    quick-sort in-place implementation
    it takes O(n logn)
    -----------------------------------------------*/
    public static <K> void quickSortInPlace(K[] array, int a, int b, Comparator<K> comparator) {
        if (a >= b) return;

        int left = a;
        int right = b - 1;
        K pivot = array[b];
        K temp;

        while (left <= right) {

            while (left <= right && comparator.compare(array[left], pivot) < 0) {
                left++;
            }

            while (left <= right && comparator.compare(array[right], pivot) > 0) {
                right--;
            }

            if (left <= right) {
                temp = array[right];
                array[right] = array[left];
                array[left] = temp;
                left++;
                right--;
            }

        }

        temp = pivot;
        array[b] = array[left];
        array[left] = temp;

        quickSortInPlace(array, a, left - 1, comparator);
        quickSortInPlace(array, left + 1, b, comparator);

    }

    public static void main(String[] args) {
        Integer[] array = {85, 24, 63, 45, 17, 31, 96, 50};
        quickSortInPlace(array, 0, array.length - 1, (Integer i1, Integer i2) -> {
            if (i1 < i2) return -1;
            else if (i1 == i2) return 0;
            else return 1;
        });
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
