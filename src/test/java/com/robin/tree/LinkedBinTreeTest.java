package com.robin.tree;

import junit.framework.TestCase;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedBinTreeTest extends TestCase {
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

    public void testGetInstance() {

    }

    public void testGetValue() {
        assertEquals(tree.getValue(), ROOT);
    }

    public void testSetValue() {
        assertEquals(tree.setValue(CHANGE), ROOT);
        assertEquals(tree.getValue(), CHANGE);
    }

    public void testGetParent() {
        assertNull(tree.getParent());
        tree.setLeft(left);
        assertEquals(tree, left.getParent());
    }

    public void testGetLeft() {
        assertNull(tree.getLeft());
        tree.setLeft(left);
        assertEquals(tree.getLeft(), left);
    }

    public void testSetLeft() {
        assertNull(tree.setLeft(left));
        assertEquals(tree.getLeft(), left);
        assertEquals(tree, left.getParent());
    }

    public void testGetRight() {
        assertNull(tree.getLeft());
        tree.setRight(left);
        assertEquals(tree.getRight(), left);
    }

    public void testSetRight() {
        assertNull(tree.setRight(left));
        assertEquals(tree.getRight(), left);
        assertEquals(tree, left.getParent());
    }

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

//    @Ignore
//    public void testLevelIterator() {
//        tree.setLeft(left);
//        tree.setRight(right);
//        Iterator<BinTree<String>> it = tree.levelIterator();
//        List<BinTree<String>> ans = new ArrayList<>(3);
//        ans.add(tree);
//        ans.add(left);
//        ans.add(right);
//        List<BinTree<String>> res = new ArrayList<>(3);
//        while (it.hasNext()) {
//            res.add(it.next());
//        }
//        assertEquals(ans, res);
//    }
}