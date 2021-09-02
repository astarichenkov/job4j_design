package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T value;
        try {
            value = out.pop();
        } catch (NoSuchElementException e) {
            try {
                while (true) {
                    out.push(in.pop());
                }
            } catch (NoSuchElementException ee) {
                value = out.pop();
            }
        }
        return value;
    }

    public void push(T value) {
        in.push(value);
    }
}