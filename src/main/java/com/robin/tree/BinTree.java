package com.robin.tree;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author robin
 */
public interface BinTree<T> {
    T getValue();

    BinTree<T> getParent();

    BinTree<T> getLeft();

    BinTree<T> getRight();

    Iterator<T> dlrIterator();

    Iterator<T> ldrIterator();

    Iterator<T> lrdIterator();

    default BinTree<T> getLeftest() {
        BinTree<T> result = this;
        if (Objects.nonNull(result.getLeft())) {
            result = result.getLeft();
        }
        return result;
    }

    default BinTree<T> getRightest() {
        BinTree<T> result = this;
        if (Objects.nonNull(result.getRight())) {
            result = result.getRight();
        }
        return result;
    }
}
