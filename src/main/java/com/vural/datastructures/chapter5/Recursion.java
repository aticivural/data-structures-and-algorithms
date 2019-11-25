package com.vural.datastructures.chapter5;

import java.util.Arrays;

public class Recursion {
    //==================Linear Recursion===============

    //O(n)
    public static int linearSum(int[] data, int n) {
        if (n == 0)
            return 0;
        else
            return linearSum(data, n - 1) + data[n - 1];
    }

    //O(n)
    public static void reverseArray(int[] data, int low, int high) {
        if (low < high) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low - 1, high - 1);
        }
    }

    public static void reverseIterative(int[] data) {
        int low = 0;
        int high = data.length - 1;
        while (low < high) {
            int temp = data[low];
            data[low++] = data[high];
            data[high--] = temp;
        }
    }

    //O(n)
    public static double power1(double x, int n) {
        if (n == 0)
            return 1;
        else
            return x * power1(x, n - 1);
    }

    //O(logn)
    public static double power2(double x, int n) {
        if (n == 0)
            return 1;
        else {
            double partial = power2(x, n / 2);
            double result = partial * partial;
            if (n % 2 == 1)
                result *= x;
            return result;
        }
    }

    //==================Binary Recursion===============

    //O(n)
    public static int binaryRecursion(int[] data, int low, int high) {
        System.out.println("data : " + Arrays.toString(data) + " low : " + low + " high : " + high);
        if (low > high)
            return 0;
        else if (low == high)
            return data[low];
        else {
            int mid = (high + low) / 2;
            return binaryRecursion(data, low, mid) + binaryRecursion(data, mid + 1, high);
        }
    }

}
