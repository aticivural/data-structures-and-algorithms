package com.vural.datastructures.chapter5;

public class Fibonacci {

    //O(n)
    public static long[] fibonacciGood(int n) {
        if (n <= 1) {
            return new long[]{n, 0};
        } else {
            long[] temp = fibonacciGood(n - 1);
            long[] answer = new long[]{temp[0] + temp[1], temp[0]};
            return answer;
        }
    }


    //O(n^2)
    public static long fibonacciBad(int n) {
        if (n <= 1)
            return 1;
        else
            return fibonacciBad(n - 2) * fibonacciBad(n - 1);
    }
}
