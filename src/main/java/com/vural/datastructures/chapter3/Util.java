package com.vural.datastructures.chapter3;

import java.util.Arrays;

public class Util {

    //O(n)
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


    public static void main(String[] args) {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};

        System.out.printf("array1 == array2 : %b\n", array1 == array2); //false
        System.out.printf("array1.equals(array2) : %b\n", array1.equals(array2)); //false
        System.out.printf("Arrays.equals(array1, array2) : %b\n", Arrays.equals(array1, array2)); //true


        System.out.println("======================================");
        int[][] array3 = {{1}, {2}, {3}};
        int[][] array4 = {{1}, {2}, {3}};

        System.out.printf("array3 == array4 : %b\n", array3 == array4); //false
        System.out.printf("array3.equals(array4) : %b\n", array3.equals(array4)); //false
        System.out.printf("Arrays.equals(array3, array4) : %b\n", Arrays.equals(array3, array4)); //false
        System.out.printf("Arrays.deepEquals(array3, array4) : %b\n", Arrays.deepEquals(array3, array4)); //true
    }
}
