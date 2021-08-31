
package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(E item) {
            this.item = item;
        }
    }

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<E>(value);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> current = first;
            private int point;

            @Override
            public boolean hasNext() {
                return size != 0 && last != null && point < size;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                point++;
                return item;
            }
        };
    }
}