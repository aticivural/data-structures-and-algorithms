package com.vural.datastructures.chapter5;

import java.io.File;

public class FileSystems {

    //O(n)
    public static long diskUsage(File root) {
        long total = root.length();
        if (root.isDirectory()) {
            for (String childName : root.list()) {
                File child = new File(root, childName);
                total += diskUsage(child);
            }
        }
        System.out.println(total + "\t" + root);
        return total;
    }

}
