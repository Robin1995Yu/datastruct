package com.robin.tree.bintree;

import java.util.*;

public class BinSearchTreeMap<K, V> implements Map<K, V> {
    private LinkedBinTree<BinSearchTreeMapEntry<K, V>> tree;
    private Comparator<? super K> comparator;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode((K) key);
        return Objects.nonNull(node) && getCompareResult() == 0;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode((K) key);
        return Objects.nonNull(node) && getCompareResult() == 0 ? node.getValue().getValue() : null;
    }

    @Override
    public V put(K key, V value) {
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode(key);
        if (Objects.isNull(node)) {
            tree = newTreeNode(key, value);
            size++;
            return null;
        }
        int c = getCompareResult();
        if (c == 0) {
            return node.getValue().setValue(value);
        }
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> treeNode = newTreeNode(key, value);
        size++;
        if (c < 0) {
            node.setLeft(treeNode);
        } else {
            node.setRight(treeNode);
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode((K) key);
        if (Objects.isNull(node)) {
            return null;
        }
        int c = getCompareResult();
        if (c != 0) {
            return null;
        }
        V result = node.getValue().getValue();
        boolean lNull = node.getLeft() == null;
        boolean rNull = node.getRight() == null;
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> child = null;
        if (lNull || rNull) {
            if (!lNull) {
                child = node.getLeft();
            }
            if (!rNull) {
                child = node.getRight();
            }
        } else {
            child = (LinkedBinTree<BinSearchTreeMapEntry<K, V>>) node.getLeft().getRightest();
            child.setRight(node.getRight());
            if (child != node.getLeft()) {
                child.setLeft(node.getLeft());
                child.getParent().setRight(child.getRight());
                child.setRight(null);
            }
        }
        if (Objects.nonNull(node.getParent())) {
            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(child);
            } else {
                node.getParent().setRight(child);
            }
        } else {
            tree = child;
        }
        size--;
        return result;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        tree = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    private class EntrySet extends AbstractSet<Entry<K,V>> {
        @Override
        public Iterator<Map.Entry<K,V>> iterator() {
            return new EntryIterator();
        }

        @Override
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            K key = (K) ((Entry) o).getKey();
            LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode(key);
            return Objects.nonNull(node) && getCompareResult() == 0;
        }

        @Override
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) o;
            Object value = entry.getValue();
            LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = getNode((K) entry.getKey());
            if (Objects.isNull(node) || getCompareResult() != 0) {
                return false;
            }
            return Objects.equals(value, node.getValue().getValue());

        }

        @Override
        public int size() {
            return BinSearchTreeMap.this.size();
        }

        @Override
        public void clear() {
            BinSearchTreeMap.this.clear();
        }

    }

    private class EntryIterator implements Iterator<Entry<K, V>> {
        private Iterator<BinSearchTreeMapEntry<K, V>> it = BinSearchTreeMap.this.tree.ldrIterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            return it.next();
        }
    }

    private class KeySet extends AbstractSet<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public boolean contains(Object o) {
            return BinSearchTreeMap.this.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            return Objects.nonNull(BinSearchTreeMap.this.remove(o));
        }

        @Override
        public int size() {
            return BinSearchTreeMap.this.size();
        }

        @Override
        public void clear() {
            BinSearchTreeMap.this.clear();
        }

        private class KeyIterator implements Iterator<K> {
            Iterator<Entry<K, V>> it = new EntryIterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public K next() {
                return it.next().getKey();
            }
        }
    }

    private class Values extends AbstractCollection<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return BinSearchTreeMap.this.size();
        }

        private class ValueIterator implements Iterator<V> {
            private Iterator<Entry<K, V>> it = new EntryIterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public V next() {
                return it.next().getValue();
            }
        }
    }

    private static class BinSearchTreeMapEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public BinSearchTreeMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V result = this.value;
            this.value = value;
            return result;
        }
    }

    protected int compare(K k1, K k2) {
        if (Objects.isNull(k1) || Objects.isNull(k2)) {
            throw new NullPointerException();
        }
        return Objects.isNull(comparator) ? ((Comparable<? super K>) k1).compareTo(k2) : comparator.compare(k1, k2);
    }

    private static ThreadLocal<Integer> compareResultThreadLocal = new ThreadLocal<>();

    private static void setCompareResult(int c) {
        compareResultThreadLocal.set(c);
    }

    private static int getCompareResult() {
        int c = compareResultThreadLocal.get();
        compareResultThreadLocal.remove();
        return c;
    }

    protected LinkedBinTree<BinSearchTreeMapEntry<K, V>> getNode(K key) {
        if (Objects.isNull(tree)) {
            return null;
        }
        LinkedBinTree<BinSearchTreeMapEntry<K, V>> node = tree;
        int c;
        while (true) {
            c = compare(key, node.getValue().getKey());
            if (c == 0 || (c < 0 && Objects.isNull(node.getLeft())) || (c > 0 && Objects.isNull(node.getRight()))) {
                setCompareResult(c);
                return node;
            }
            if (c < 0) {
                node = node.getLeft();
                continue;
            }
            if (c > 0) {
                node = node.getRight();
                continue;
            }
        }
    }

    private static <K, V> LinkedBinTree<BinSearchTreeMapEntry<K, V>> newTreeNode(K key, V value) {
        return new LinkedBinTree<>(new BinSearchTreeMapEntry<>(key, value));
    }
}
