package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {
    final int[] numbers;
    private int position;

    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    private boolean isCurrentNumberEven() {
        return numbers[position] % 2 == 0;
    }

    @Override
    public boolean hasNext() {
        while (!isCurrentNumberEven()) {
            if (position < numbers.length - 1) {
                position++;
            } else {
                return false;
            }
        }
        return (isCurrentNumberEven());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int el = numbers[position];
        position++;
        return el;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
