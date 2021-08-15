package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
            return counter < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
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
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
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
