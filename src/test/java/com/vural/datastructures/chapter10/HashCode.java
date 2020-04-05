package com.vural.datastructures.chapter10;

import org.junit.Test;

public class HashCode {

    int hashCode(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h << 5) | (h >>> 27);
            h += (int) s.charAt(i);
        }
        return h;
    }

    @Test
    public void test() {
        System.out.println(new HashCode().hashCode("vural"));
    }
}
