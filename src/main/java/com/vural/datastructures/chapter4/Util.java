package com.vural.datastructures.chapter4;

import java.util.Arrays;

public class Util {

    //O(n^2)
    public static double[] prefixAverage1(int[] x) {
        int length = x.length;
        double[] a = new double[length];
        for (int i = 0; i < length; i++) {
            int total = 0;
            for (int j = 0; j < i; j++) {
                total += x[j];
            }
            a[i] = total / (i + 1);
        }
        return a;
    }

    //O(n)
    public static double[] prefixAverage2(int[] x) {
        int length = x.length;
        double[] a = new double[length];
        int total = 0;
        for (int i = 0; i < length; i++) {
            total += x[i];
            a[i] = total / (i + 1);
        }
        return a;
    }

    //O(n^2)
    public static boolean unique1(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i] == data[j])
                    return false;
            }
        }
        return true;
    }

    //O(nlogn)
    public static boolean unique2(int[] data) {
        int length = data.length;
        int[] temp = Arrays.copyOf(data, length);
        Arrays.sort(temp);  //O(nlogn)
        for (int i = 0; i < length - 1; i++) { //O(n)
            if (temp[i] == temp[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //O(2^n)
    public static boolean unique3(int[] data, int low, int high) {
        if (low > high)
            return true;
        else if (!unique3(data, low, high - 1)) return false;
        else if (!unique3(data, low + 1, high)) return false;
        else return (data[low] != data[high]);
    }


    //O(n^3)
    public static boolean disjoint1(int[] groupA, int[] groupB, int[] groupC) {
        for (int a : groupA) {
            for (int b : groupB) {
                for (int c : groupC) {
                    if ((a == b) && (b == c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //O(n^2)
    public static boolean disjoint2(int[] groupA, int[] groupB, int[] groupC) {
        for (int a : groupA) {
            for (int b : groupB) {
                if (a == b) {
                    for (int c : groupC) {
                        if (a == c) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
