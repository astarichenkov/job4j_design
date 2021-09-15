package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> p = findBy(parent);
        if (p.isPresent()) {
            if (findBy(child).isEmpty()) {
                Node<E> node = new Node<>(child);
                return p.get().children.add(node);
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> condition = el -> el.value.equals(value);
        return findByPredicate(condition);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> condition = el -> el.children.size() > 2;
        Optional<Node<E>> opt = findByPredicate(condition);
        return opt.isEmpty();

    }

    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        System.out.println(tree.findBy(1).isPresent());
        tree.add(1, 3);
        System.out.println(tree.isBinary());
        tree.add(1, 4);
        System.out.println(tree.isBinary());
    }
}