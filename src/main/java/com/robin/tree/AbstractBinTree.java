package com.robin.tree;

import sun.jvm.hotspot.oops.BranchData;

import java.io.FileReader;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractBinTree<T> implements BinTree<T> {


    private abstract class AbstractBinTreeIterator<T> implements Iterator<BinTree<T>> {
        protected BinTree<T> before;
        protected BinTree<T> next;

        protected AbstractBinTreeIterator(BinTree first) {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(next);
        }
    }

    protected class DlrIterator<T> extends AbstractBinTreeIterator<T> {

        protected DlrIterator() {
            super(AbstractBinTree.this);
        }

        @Override
        public BinTree<T> next() {
            before = next;
            next = null;
            if (Objects.nonNull(before)) {
                if (Objects.nonNull(next.getLeft())) {
                    next = before.getLeft();
                }
                if (Objects.nonNull(next.getRight())) {
                    next = before.getRight();
                }
                BinTree<T> parent = before.getParent();
                while (Objects.nonNull(parent) && parent.getRight() == before) {
                    before = parent;
                    parent = before.getParent();
                }
                if (Objects.nonNull(parent)) {
                    next = parent.getRight();
                } else {
                    before = null;
                }
            }
            return before;
        }
    }

    protected class LdrIterator<T> extends AbstractBinTreeIterator<T> {

        protected LdrIterator() {
            super(AbstractBinTree.this.getLeftest());
        }

        @Override
        public BinTree<T> next() {
            before = next;
            next = null;
            if (Objects.nonNull(before)) {
                if (Objects.nonNull(before.getRight())) {
                    next = before.getRight().getLeftest();
                } else {
                    BinTree<T> parent = before.getParent();
                    while (Objects.nonNull(parent) && parent.getRight() == before) {
                        before = parent;
                        parent = before.getParent();
                    }
                    next = parent;
                }
            }
            return before;
        }
    }

    protected class LrdIterator<T> extends AbstractBinTreeIterator<T> {

        private static <T> BinTree<T> getFirst(BinTree<T> first) {
            while (true){
                first = first.getLeftest();
                if (Objects.isNull(first.getRight())) {
                    break;
                } else {
                    first = first.getRight();
                }
            }
            return first;
        }

        protected LrdIterator() {
            super(getFirst(AbstractBinTree.this));
        }

        @Override
        public BinTree<T> next() {
            return null;
        }
    }
}
