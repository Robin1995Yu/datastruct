package com.robin.tree.bintree;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author robin
 */
public interface BinTree<T> {
    T getValue();

    T setValue(T value);

    BinTree<T> getParent();

    BinTree<T> getLeft();

    BinTree<T> setLeft(BinTree<T> left);

    BinTree<T> getRight();

    BinTree<T> setRight(BinTree<T> right);

    Iterator<BinTree<T>> dlrIterator();

    Iterator<BinTree<T>> ldrIterator();

    Iterator<BinTree<T>> lrdIterator();

    Iterator<BinTree<T>> levelIterator();

    default BinTree<T> getLeftest() {
        BinTree<T> result = this;
        while (Objects.nonNull(result.getLeft())) {
            result = result.getLeft();
        }
        return result;
    }

    default BinTree<T> getRightest() {
        BinTree<T> result = this;
        while (Objects.nonNull(result.getRight())) {
            result = result.getRight();
        }
        return result;
    }
}
