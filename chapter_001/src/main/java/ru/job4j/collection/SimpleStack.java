package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        Iterator<T> it = linked.iterator();
        T element = it.next();
        linked.deleteFirst();
        return element;
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}