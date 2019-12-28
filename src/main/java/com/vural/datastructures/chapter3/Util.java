package com.vural.datastructures.chapter3;

public class Util {

    //O(n^2)
    public static void insertionSort(char[] data) {
        int n = data.length;
        for (int k = 1; k < n; k++) {
            int j = k;
            char current = data[k];
            while (j > 0 && data[j - 1] > current) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = current;
        }
    }
}
