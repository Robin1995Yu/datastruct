package com.robin.list;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyArrayListTest extends TestCase {

    private MyArrayList<String> myArrayList = new MyArrayList<>();

    @Before
    public void before() {
        myArrayList.clear();
    }

    @After
    public void after() {
        myArrayList.clear();
    }

    public void testSize() {
        Assert.assertTrue(myArrayList.size() == 0);
        myArrayList.add("1");
        Assert.assertTrue(myArrayList.size() == 1);
    }

    public void testIsEmpty() {
        Assert.assertTrue(myArrayList.isEmpty());
        myArrayList.add("1");
        Assert.assertFalse(myArrayList.isEmpty());
    }

    public void testContains() {
        myArrayList.add("1");
        assertTrue(myArrayList.contains("1"));
    }

    public void testIterator() {
    }

    public void testToArray() {
    }

    public void testTestToArray() {
    }

    public void testAdd() {
        myArrayList.add("1");
        assertFalse(myArrayList.isEmpty());
        assertTrue(myArrayList.contains("1"));
    }

    public void testRemove() {
        myArrayList.add("1");
        assertFalse(myArrayList.remove("2"));
        assertTrue(myArrayList.remove("1"));
        assertTrue(myArrayList.isEmpty());
    }

    public void testContainsAll() {
        Set<String> s = new HashSet<>();
        s.add("1");
        s.add("2");
        s.add("3");
        myArrayList.addAll(s);
        assertTrue(myArrayList.containsAll(s));
    }

    public void testAddAll() {
    }

    public void testTestAddAll() {
    }

    public void testRemoveAll() {
    }

    public void testRetainAll() {
    }

    public void testClear() {
        myArrayList.add("1");
        myArrayList.clear();
        assertTrue(myArrayList.isEmpty());
    }

    public void testGet() {
        myArrayList.add("1");
        myArrayList.add("2");
        assertEquals(myArrayList.get(0), "1");
        assertEquals(myArrayList.get(1), "2");
    }

    public void testSet() {
        myArrayList.set(0, "1");
        assertEquals(myArrayList.get(0), "1");
    }

    public void testTestAdd() {
    }

    public void testTestRemove() {
    }

    public void testIndexOf() {
    }

    public void testLastIndexOf() {
    }

    public void testListIterator() {
    }

    public void testTestListIterator() {
    }

    public void testSubList() {
    }
}