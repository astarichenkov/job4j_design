package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < data.length; i++) {
            if (data[row].length == 0) {
                column = 0;
                row++;
            }
        }
        return (row < data.length && column < data[row].length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int element = 0;
        if (data[row].length != 0) {
            element = data[row][column];
            column++;
            if (column >= data[row].length) {
                column = 0;
                row++;
            }
        }
        return element;
    }
}