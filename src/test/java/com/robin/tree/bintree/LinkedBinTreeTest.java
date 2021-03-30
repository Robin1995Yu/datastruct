package com.robin.tree.bintree;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedBinTreeTest {
    private static final String ROOT = "root";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String LEFT_LEFT = "leftLeft";
    private static final String RIGHT_RIGHT = "rightRight";
    private static final String CHANGE = "CHANGE";

    private BinTree<String> tree = new LinkedBinTree<>(ROOT);
    private BinTree<String> left = new LinkedBinTree<>(LEFT);
    private BinTree<String> right = new LinkedBinTree<>(RIGHT);
    private BinTree<String> leftLeft = new LinkedBinTree<>(LEFT_LEFT);
    private BinTree<String> rightRight = new LinkedBinTree<>(RIGHT_RIGHT);

    @Before
    public void before() {
        tree.setValue(ROOT);
        tree.setLeft(null);
        tree.setRight(null);
        left.setLeft(null);
        right.setRight(null);
    }

    @Test
    public void testGetInstance() {

    }

    @Test
    public void testGetValue() {
        assertEquals(tree.getValue(), ROOT);
    }

    @Test
    public void testSetValue() {
        assertEquals(tree.setValue(CHANGE), ROOT);
        assertEquals(tree.getValue(), CHANGE);
    }

    @Test
    public void testGetParent() {
        assertNull(tree.getParent());
        tree.setLeft(left);
        assertEquals(tree, left.getParent());
    }

    @Test
    public void testGetLeft() {
        assertNull(tree.getLeft());
        tree.setLeft(left);
        assertEquals(tree.getLeft(), left);
    }

    @Test
    public void testSetLeft() {
        assertNull(tree.setLeft(left));
        assertEquals(tree.getLeft(), left);
        assertEquals(tree, left.getParent());
    }

    @Test
    public void testGetRight() {
        assertNull(tree.getLeft());
        tree.setRight(left);
        assertEquals(tree.getRight(), left);
    }

    @Test
    public void testSetRight() {
        assertNull(tree.setRight(left));
        assertEquals(tree.getRight(), left);
        assertEquals(tree, left.getParent());
    }

    @Test
    public void testDlrIterator() {
        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftLeft);
        right.setRight(rightRight);
        Iterator<BinTree<String>> it = tree.dlrIterator();
        List<BinTree<String>> ans = new ArrayList<>(5);
        ans.add(tree);
        ans.add(left);
        ans.add(leftLeft);
        ans.add(right);
        ans.add(rightRight);
        List<BinTree<String>> res = new ArrayList<>(5);
        while (it.hasNext()) {
            res.add(it.next());
        }
        assertEquals(ans, res);
    }

    @Test
    public void testLdrIterator() {
        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftLeft);
        right.setRight(rightRight);
        Iterator<BinTree<String>> it = tree.ldrIterator();
        List<BinTree<String>> ans = new ArrayList<>(5);
        ans.add(leftLeft);
        ans.add(left);
        ans.add(tree);
        ans.add(right);
        ans.add(rightRight);
        List<BinTree<String>> res = new ArrayList<>(5);
        while (it.hasNext()) {
            res.add(it.next());
        }
        assertEquals(ans, res);
    }

    @Test
    public void testLrdIterator() {
        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftLeft);
        right.setRight(rightRight);
        Iterator<BinTree<String>> it = tree.lrdIterator();
        List<BinTree<String>> ans = new ArrayList<>(5);
        ans.add(leftLeft);
        ans.add(left);
        ans.add(rightRight);
        ans.add(right);
        ans.add(tree);
        List<BinTree<String>> res = new ArrayList<>(5);
        while (it.hasNext()) {
            res.add(it.next());
        }
        assertEquals(ans, res);
    }

    @Ignore
    @Test
    public void testLevelIterator() {
        tree.setLeft(left);
        tree.setRight(right);
        left.setLeft(leftLeft);
        right.setRight(rightRight);
        Iterator<BinTree<String>> it = tree.levelIterator();
        List<BinTree<String>> ans = new ArrayList<>(5);
        ans.add(tree);
        ans.add(left);
        ans.add(right);
        ans.add(leftLeft);
        ans.add(rightRight);
        List<BinTree<String>> res = new ArrayList<>(5);
        while (it.hasNext()) {
            res.add(it.next());
        }
        assertEquals(ans, res);
    }
}