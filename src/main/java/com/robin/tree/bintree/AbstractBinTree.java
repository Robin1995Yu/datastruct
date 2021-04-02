package com.robin.tree.bintree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBinTree<T> implements BinTree<T> {

    @Override
    public Iterator<T> dlrIterator() {
        return new DlrIterator();
    }

    @Override
    public Iterator<T> ldrIterator() {
        return new LdrIterator();
    }

    @Override
    public Iterator<T> lrdIterator() {
        return new LrdIterator();
    }

    @Override
    public Iterator<T> levelIterator() {
        return new LevelIterator();
    }

    private abstract class AbstractBinTreeIterator implements Iterator<T> {
        protected BinTree<T> before;
        protected BinTree<T> next;

        protected AbstractBinTreeIterator(BinTree first) {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(next);
        }

        @Override
        public T next() {
            before = next;
            next = null;
            getNext();
            return before.getValue();
        }

        protected abstract void getNext();
    }

    protected class DlrIterator extends AbstractBinTreeIterator {

        protected DlrIterator() {
            super(AbstractBinTree.this);
        }

        @Override
        protected void getNext() {
            if (Objects.nonNull(before)) {
                if (Objects.nonNull(before.getLeft())) {
                    next = before.getLeft();
                } else if (Objects.nonNull(before.getRight())) {
                    next = before.getRight();
                } else {
                    BinTree<T> parent = before.getParent();
                    BinTree<T> ch = this.before;
                    while (Objects.nonNull(parent) && (parent.getRight() == ch || Objects.isNull(parent.getRight()))) {
                        ch = parent;
                        parent = ch.getParent();
                    }
                    if (Objects.nonNull(parent)) {
                        next = parent.getRight();
                    }
                }
            }
        }
    }

    protected class LdrIterator extends AbstractBinTreeIterator {

        protected LdrIterator() {
            super(AbstractBinTree.this.getLeftest());
        }

        @Override
        protected void getNext() {
            if (Objects.nonNull(before)) {
                if (Objects.nonNull(before.getRight())) {
                    next = before.getRight().getLeftest();
                } else {
                    BinTree<T> parent = before.getParent();
                    BinTree<T> ch = this.before;
                    while (Objects.nonNull(parent) && parent.getRight() == ch) {
                        ch = parent;
                        parent = ch.getParent();
                    }
                    next = parent;
                }
            }
        }
    }

    private static <T> BinTree<T> getFirstForLrd(BinTree<T> binTree) {
        BinTree<T> first = binTree;
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

    protected class LrdIterator extends AbstractBinTreeIterator {
        protected LrdIterator() {
            super(AbstractBinTree.getFirstForLrd(AbstractBinTree.this));
        }

        @Override
        protected void getNext() {
            if (Objects.nonNull(before)) {
                BinTree<T> parent = before.getParent();
                if (Objects.nonNull(parent) && parent.getLeft() == before && Objects.nonNull(parent.getRight())) {
                    parent = AbstractBinTree.getFirstForLrd(parent.getRight());
                }
                next = parent;
            }
        }
    }

    protected class LevelIterator extends AbstractBinTreeIterator {
        List<BinTree<T>> queue = new LinkedList<>();
        protected LevelIterator() {
            super(AbstractBinTree.this);
        }

        @Override
        protected void getNext() {
            if (Objects.nonNull(before)) {
                queue.add(before);
                if (!queue.isEmpty()) {
                    BinTree<T> head = queue.get(0);
                    boolean flag = head.getLeft() == before;
                    while (!queue.isEmpty() && Objects.isNull(next)) {
                        if (flag) {
                            next = head.getRight();
                            queue.remove(0);
                            if (!queue.isEmpty()) {
                                head = queue.get(0);
                            }
                        } else {
                            next = head.getLeft();
                        }
                        flag = !flag;
                    }
                }
            }
        }
    }
}
