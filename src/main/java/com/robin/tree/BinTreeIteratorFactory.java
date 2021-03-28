package com.robin.tree;

import java.util.Iterator;

public interface BinTreeIteratorFactory<T> {
    Iterator<BinTree<T>> getIterator(BinTree<T> tree);

}
