package com.vural.datastructures.chapter12;

import com.vural.datastructures.chapter6.LinkedQueue;
import com.vural.datastructures.chapter6.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {


    /*-----------------------------------------------
    merge-sort array implementation with recursion
    it takes O(n logn)
    -----------------------------------------------*/

    public static <K> void mergeSort(K[] S, Comparator<K> comparator) {

        int length = S.length;
        if (length < 2) return;

        int mid = length / 2;

        K[] S1 = Arrays.copyOfRange(S, 0, mid);
        K[] S2 = Arrays.copyOfRange(S, mid, length);

        mergeSort(S1, comparator);
        mergeSort(S2, comparator);

        merge(S1, S2, S, comparator);
    }

    public static <K> void merge(K[] S1, K[] S2, K[] S, Comparator<K> comparator) {
        int length = S.length;
        int i = 0, j = 0;

        while (i + j < length) {
            if (j == S2.length || (i < S1.length && comparator.compare(S1[j], S2[i]) < 0)) {
                S[i + j] = S1[i++];
            } else {
                S[i + j] = S2[j++];
            }
        }
    }


    /*-----------------------------------------------
    merge-sort queue implementation with recursion
    it takes O(n logn)
    -----------------------------------------------*/
    public static <K> void mergeSort(Queue<K> S, Comparator<K> comparator) {
        int length = S.size();
        if (length < 2) return;

        Queue<K> S1 = new LinkedQueue<>();
        Queue<K> S2 = new LinkedQueue<>();
        while (S1.size() < length / 2) {
            S1.enqueue(S.dequeue());
        }
        while (!S.isEmpty()) {
            S2.enqueue(S.dequeue());
        }

        mergeSort(S1, comparator);
        mergeSort(S2, comparator);

        merge(S1, S2, S, comparator);
    }

    public static <K> void merge(Queue<K> S1, Queue<K> S2, Queue<K> S, Comparator<K> comparator) {
        while (!S1.isEmpty() && !S2.isEmpty()) {
            if (comparator.compare(S1.first(), S2.first()) < 0) {
                S.enqueue(S1.dequeue());
            } else {
                S.enqueue(S2.dequeue());
            }
        }

        while (!S1.isEmpty()) {
            S.enqueue(S1.dequeue());
        }

        while (!S2.isEmpty()) {
            S.enqueue(S2.dequeue());
        }
    }


    /*-----------------------------------------------
    merge-sort bottom up implementation without recursion
    it takes O(n logn)
    -----------------------------------------------*/

    public static <K> void mergeSortBottomUp(K[] orig, Comparator<K> comp) {
        int n = orig.length;
        K[] src = orig;
        K[] dest = (K[]) new Object[n];
        K[] temp;
        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n; j += 2 * i) {
                merge(src, dest, comp, j, i);
            }
            temp = src;
            src = dest;
            dest = temp;
        }

        if (orig != src) {
            System.arraycopy(src, 0, orig, 0, n);
        }
    }

    public static <K> void merge(K[] in, K[] out, Comparator<K> comparator, int start, int inc) {
        int end1 = Math.min(start + inc, in.length);
        int end2 = Math.min(start + 2 * inc, in.length);
        int x = start;
        int y = start + inc;
        int z = start;
        while (x < end1 && y < end2) {
            if (comparator.compare(in[x], in[y]) < 0) {
                out[z++] = in[x++];
            } else {
                out[z++] = in[y++];
            }
        }
        if (x < end1) {
            System.arraycopy(in, x, out, z, end1 - x);
        } else if (y < end2) {
            System.arraycopy(in, y, out, z, end2 - y);
        }
    }

    public static void main(String[] args) {
        /*Integer[] array = {4, 3, 2, 1};
        mergeSort(array, (Integer i1, Integer i2) -> {
            if (i1 < i2) return -1;
            else if (i1 == i2) return 0;
            else return 1;
        });
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }*/

        /*LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        mergeSort(queue, (Integer i1, Integer i2) -> {
            if (i1 < i2) return -1;
            else if (i1 == i2) return 0;
            else return 1;
        });

        int length = queue.size();
        for (int i = 0; i < length; i++) {
            System.out.print(queue.dequeue() + " ");
        }*/

        Integer[] array = {4, 3, 2, 1};
        mergeSortBottomUp(array, (Integer i1, Integer i2) -> {
            if (i1 < i2) return -1;
            else if (i1 == i2) return 0;
            else return 1;
        });
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
