package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int outSize;
    private int inSize;

    public T poll() {
        if (outSize == 0) {
            while (inSize != 0) {
                out.push(in.pop());
                outSize++;
                inSize--;
            }
        }
        outSize--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}