package com.vural.datastructures.chapter7;

public class Util {

    public static void insertionSort(PositionalList<Integer> list) {
        Position<Integer> marker = list.first();
        while (marker != list.last()) {
            Position<Integer> pivot = list.after(marker);
            Integer value = pivot.getElement();
            if (value > marker.getElement()) {
                marker = pivot;
            } else {
                Position<Integer> walk = marker;
                while (walk != list.first() && list.before(walk).getElement() > value) {
                    walk = list.before(walk);
                }
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else
                    break;
            }
        }
    }
}
