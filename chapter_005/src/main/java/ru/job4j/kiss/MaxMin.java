package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max =value.get(0);
        for (T el : value) {
            if (comparator.compare(max, el) < 0) {
                max = el;
            }
        }
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return null;
    }
}