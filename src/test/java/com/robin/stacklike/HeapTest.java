package com.robin.stacklike;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeapTest {
    private StackLike<Long> heap = new Heap<>();

    @Test
    public void testSize() {
        assertEquals(heap.size(), 0);
        heap.push(1L);
        assertEquals(heap.size(), 1);
    }

    @Test
    public void testPush() {
        heap.push(2L);
        assertEquals(heap.peek(), Long.valueOf(2));
        heap.push(1L);
        assertEquals(heap.peek(), Long.valueOf(1));
        heap.push(3L);
        assertEquals(heap.peek(), Long.valueOf(1));
    }

    @Test
    public void testPop() {
        heap.push(2L);
        heap.push(1L);
        heap.push(0L);
        heap.push(3L);
        assertEquals(heap.pop(), Long.valueOf(0));
        assertEquals(heap.pop(), Long.valueOf(1));
        assertEquals(heap.pop(), Long.valueOf(2));
        assertEquals(heap.pop(), Long.valueOf(3));
    }

    @Test
    public void testPeek() {
    }
}