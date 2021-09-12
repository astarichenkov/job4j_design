package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public SimpleMap() {
    }

    @SuppressWarnings({"unchecked"})
    public SimpleMap(int capacity) {
        this.table = new MapEntry[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean put(K key, V value) {
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> entry = new MapEntry<>(key, value);
        if (table[index] != null) {
            return false;
        }
        table[index] = entry;
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        int hash = 31;
        hash = hash * 17 + hashCode;
        return hash % capacity;
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        SimpleMap<K, V> newMap = new SimpleMap<>(table.length * 2);
        Iterator<K> it = iterator();
        while (it.hasNext()) {
            K key = it.next();
            newMap.put(key, get(key));
        }
        table = newMap.table;
        capacity = capacity * 2;
    }

    @Override
    public V get(K key) {
        if (count != 0) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash);
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (count != 0) {
            int hash = hash(key.hashCode());
            int index = indexFor(hash);
            table[index] = null;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            private int point = 0;
            private final MapEntry<K, V>[] data = table;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = point; i < data.length; i++) {
                    if (data[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}