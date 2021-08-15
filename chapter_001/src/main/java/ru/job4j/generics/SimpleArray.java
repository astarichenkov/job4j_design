package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] elements;
    private int size;

    public SimpleArray(int elements) {
        this.elements = new Object[elements];
    }

    public class SimpleArrayIterator implements Iterator<T> {
        int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < elements.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            T value = (T) elements[counter];
            counter++;
            return value;
        }
    }

    public void add(T model) {
        elements[size] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        elements[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public String toString() {
        return "SimpleArray{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }
}