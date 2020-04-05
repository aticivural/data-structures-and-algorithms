package com.vural.datastructures.chapter10;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProbeHashMapTest {

    @Test
    public void probeHashMao() {
        Map<Integer, String> map = new ProbeHashMap<>();
        map.put(1, "vural");

    }

}