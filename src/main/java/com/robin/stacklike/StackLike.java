package com.robin.stacklike;

public interface StackLike<T> {
    void push(T t);

    T pop();

    T peek();

    int size();

    boolean isEmpty();

    void clear();
}
