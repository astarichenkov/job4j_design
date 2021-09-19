package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = integer -> integer < 0;
        return findByPredicate(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = integer -> integer > 0;
        return findByPredicate(value, comparator, predicate);
    }

    private <T> T findByPredicate(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T rsl = value.get(0);
        for (T el : value) {
            if (predicate.test(comparator.compare(rsl, el))) {
                rsl = el;
            }
        }
        return rsl;
    }
}