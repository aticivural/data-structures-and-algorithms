package com.vural.datastructures.chapter5;

public class BinarySearch {

    //O(logn)
    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high)
            return false;
        else {
            int mid = (low + high) / 2;
            if (target == data[mid])
                return true;
            else if (target <= data[mid]) {
                return binarySearch(data, target, low, mid - 1);
            } else
                return binarySearch(data, target, low, mid - 1);
        }
    }

    public static boolean binarySearchIterative(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == data[mid])
                return true;
            else if (target < data[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }
}
