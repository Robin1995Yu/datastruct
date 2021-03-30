package com.robin.tree.bintree;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class BinSearchTreeMapTest {

    Map<Integer, Integer> map = new BinSearchTreeMap<>();

    @Before
    public void before() {
        map.clear();
        map.put(3, 3);
        map.put(2, 2);
        map.put(4, 4);
        map.put(6, 6);
        map.put(1, 1);
    }

    @Test
    public void testSize() {
        assertEquals(map.size(), 5);
        map.clear();
        assertEquals(map.size(), 0);
    }

    @Test
    public void testIsEmpty() {
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        boolean result = true;
        result &= map.containsKey(1);
        result &= map.containsKey(2);
        result &= map.containsKey(3);
        result &= map.containsKey(4);
        result &= map.containsKey(6);
        assertTrue(result);
        assertFalse(map.containsKey(5));
        assertFalse(map.containsKey(0));
        assertFalse(map.containsKey(7));
    }

    @Test
    public void testContainsValue() {
    }

    @Test
    public void testGet() {
        assertEquals(map.get(1), Integer.valueOf(1));
        assertEquals(map.get(2), Integer.valueOf(2));
        assertEquals(map.get(3), Integer.valueOf(3));
        assertEquals(map.get(4), Integer.valueOf(4));
        assertEquals(map.get(6), Integer.valueOf(6));
        assertNull(map.get(0));
        assertNull(map.get(5));
        assertNull(map.get(7));
    }

    @Test
    public void testPut() {
        assertEquals(map.put(1, 10), Integer.valueOf(1));
        assertEquals(map.get(1), Integer.valueOf(10));
        assertNull(map.put(5, 5));
        assertEquals(map.size(), 6);
        assertEquals(map.get(5), Integer.valueOf(5));
        assertEquals(map.get(2), Integer.valueOf(2));
        assertEquals(map.get(3), Integer.valueOf(3));
        assertEquals(map.get(4), Integer.valueOf(4));
        assertEquals(map.get(6), Integer.valueOf(6));
        assertNull(map.get(0));
        assertNull(map.get(7));
    }

    @Test
    public void testRemove() {
        assertNull(map.remove(0));
        assertNull(map.remove(5));
        assertNull(map.remove(7));
        assertEquals(map.remove(1), Integer.valueOf(1));
        assertEquals(map.size(), 4);
        assertNull(map.get(1));
        assertEquals(map.get(2), Integer.valueOf(2));
        assertEquals(map.get(3), Integer.valueOf(3));
        assertEquals(map.get(4), Integer.valueOf(4));
        assertEquals(map.get(6), Integer.valueOf(6));

        assertEquals(map.remove(4), Integer.valueOf(4));
        assertEquals(map.size(), 3);
        assertNull(map.get(4));
        assertEquals(map.get(2), Integer.valueOf(2));
        assertEquals(map.get(3), Integer.valueOf(3));
        assertEquals(map.get(6), Integer.valueOf(6));

        assertEquals(map.remove(3), Integer.valueOf(3));
        assertEquals(map.size(), 2);
        assertEquals(map.get(2), Integer.valueOf(2));
        assertEquals(map.get(6), Integer.valueOf(6));

    }

    @Test
    public void testPutAll() {
    }

    @Test
    public void testClear() {
        map.clear();
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(4));
        assertNull(map.get(6));
        assertEquals(map.size(), 0);
    }

    @Test
    public void testKeySet() {
    }

    @Test
    public void testValues() {
    }

    @Test
    public void testEntrySet() {
    }
}