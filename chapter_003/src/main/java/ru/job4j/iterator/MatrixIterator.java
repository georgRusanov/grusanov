package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator {
    private int[][] matrix;
    private int out = 0, in = 0;

    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        boolean answer = true;
        if (in == matrix[out].length) {
            in = 0;
            if (++out == matrix.length) {
                answer = false;
            }
        }
        return answer;
    }

    @Override
    public Object next() {
        if (matrix.length == 0) {
            throw new NoSuchElementException("Пустой массив");
        }
        if (!hasNext()) {
            throw new NoSuchElementException("Больше нет элементов");
        }
        return matrix[out][in++];
    }
}
