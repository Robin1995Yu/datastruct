package com.robin.tree;

import java.util.Objects;

public class LinkedBinTree<T> extends AbstractBinTree<T> {
    private T value;
    private LinkedBinTree<T> left;
    private LinkedBinTree<T> right;
    private LinkedBinTree<T> parent;

    public LinkedBinTree() {
    }

    public LinkedBinTree(T value) {
        this.value = value;
    }

    public static <T> LinkedBinTree<T> getInstance(BinTree<T> binTree) {
        if (Objects.isNull(binTree)) {
            return null;
        }
        if (binTree instanceof LinkedBinTree) {
            return (LinkedBinTree<T>) binTree;
        }
        LinkedBinTree<T> result = new LinkedBinTree<>(binTree.getValue());
        if (Objects.nonNull(binTree.getLeft())) {
            result.setLeft(getInstance(binTree.getLeft()));
        }
        if (Objects.nonNull(binTree.getRight())) {
            result.setRight(getInstance(binTree.getRight()));
        }
        return result;
    }
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T setValue(T value) {
        T result = this.value;
        this.value = value;
        return result;
    }

    @Override
    public BinTree<T> getParent() {
        return parent;
    }

    @Override
    public BinTree<T> getLeft() {
        return left;
    }

    @Override
    public BinTree<T> setLeft(BinTree<T> left) {
        LinkedBinTree<T> result = this.left;
        this.left = getInstance(left);
        if (Objects.nonNull(this.left)) {
            this.left.parent = this;
        }
        if (Objects.nonNull(result)) {
            result.parent = null;
        }
        return result;
    }

    @Override
    public BinTree<T> getRight() {
        return right;
    }

    @Override
    public BinTree<T> setRight(BinTree<T> right) {
        LinkedBinTree<T> result = this.right;
        this.right = getInstance(right);
        if (Objects.nonNull(this.right)) {
            this.right.parent = this;
        }
        if (Objects.nonNull(result)) {
            result.parent = null;
        }
        return result;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
