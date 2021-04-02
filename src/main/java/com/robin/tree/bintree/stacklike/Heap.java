package com.robin.tree.bintree.stacklike;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Heap<T> implements StackLike<T> {
    private List<T> values = new ArrayList<>();

    private Comparator<T> comparator;

    public Heap() {}

    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public void push(T t) {
        values.add(t);
        up();
    }

    @Override
    public T pop() {
        assertEmpty();
        T result = values.remove(0);
        if (!isEmpty()) {
            values.add(0, values.remove(size() - 1));
            down();
        }
        return result;
    }

    @Override
    public T peek() {
        assertEmpty();
        return get(0);
    }

    private void down() {
        int currIndex = 0;
        T curr = get(currIndex);
        while (true){
            int leftIndex = getLeftIndex(currIndex);
            int rightIndex = getRightIndex(currIndex);
            T left = get(leftIndex);
            T right = get(rightIndex);
            if (Objects.isNull(left) && Objects.isNull(right)) {
                return;
            }
            T min;
            int minIndex;
            if (Objects.isNull(left)) {
                min = right;
                minIndex = rightIndex;
            } else if (Objects.isNull(right)) {
                min = left;
                minIndex = leftIndex;
            } else {
                int compare = compare(left, right);
                min = compare <= 0 ? left : right;
                minIndex = compare <= 0 ? leftIndex : rightIndex;
            }
            int compare = compare(curr, min);
            if (compare <= 0) {
                return;
            }
            values.set(minIndex, curr);
            values.set(currIndex, min);
            currIndex = minIndex;
        }
    }

    private void up() {
        int currIndex = values.size() - 1;
        T curr = get(currIndex);
        while (true) {
            int parentIndex = getParentIndex(currIndex);
            if (parentIndex < 0) {
                return;
            }
            T parent = get(parentIndex);
            int compare = compare(parent, curr);
            if (compare <= 0) {
                return;
            }
            values.set(currIndex, parent);
            values.set(parentIndex, curr);
            currIndex = parentIndex;
        }
    }

    private void assertEmpty() {
        if (size() <= 0) {
            throw new IllegalStateException("heap is empty");
        }
    }


    private static int getLeftIndex(int index) {
        return (index << 1) + 1;
    }

    private static int getRightIndex(int index) {
        return (index << 1) + 2;
    }

    private static int getParentIndex(int index) {
        return ((index + 1) >> 1) - 1;
    }

    private int compare(T t1, T t2) {
        if (Objects.isNull(t1) || Objects.isNull(t2)) {
            throw new IllegalArgumentException("Heap null element");
        }
        return Objects.nonNull(comparator) ? comparator.compare(t1, t2) : ((Comparable<? super T>) t1).compareTo(t2);
    }

    private T get(int index) {
        if (index >= values.size()) {
            return null;
        }
        return values.get(index);
    }

}
