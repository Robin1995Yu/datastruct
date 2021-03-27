package com.robin.list;

import java.io.ObjectOutputStream;
import java.util.*;

public class MyArrayList<E> implements List<E> {
    private int size;
    private Object[] data;

    public MyArrayList() {
        this.size = 0;
        this.data = new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public void resize(int size) {
        int newSize = Integer.max(size, (int) (data.length * 1.5));
        data = Objects.isNull(data) ? new Object[newSize] : Arrays.copyOf(data, newSize);
    }

    @Override
    public boolean add(E e) {
        resize(size + 1);
        data[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                for (int j = i + 1; j < size; j++) {
                    data[j - 1] = data[j];
                }
                data[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        resize(size + c.size());
        for (int i = size - 1; i >= index; i++) {
            data[i + c.size()] = data[i];
        }
        int i = index;
        for (E e : c) {
            data[i++] = e;
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int ptr = 0;
        int count = 0;
        while (ptr - count < size) {
            data[ptr] = data[ptr + count];
            ptr++;
            if (c.contains(data[ptr])) {
                count++;
            }
        }
        size -= count;
        return count > 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int addPtr = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (c.contains(data[i])) {
                data[addPtr++] = data[i];
            }
        }
        int oldSize = size;
        size = count;
        return size == oldSize;
    }

    @Override
    public void clear() {
        size = 0;
        data = null;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheckForAdd(index);
        Object result = data[index];
        data[index] = element;
        size++;
        return (E) result;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(size);
        resize(size + 1);
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Object result = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return (E) result;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i++) {
            if (Objects.equals(o, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size;
    }
}
