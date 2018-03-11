package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator{
    private int[][] matrix;
    private int out = 0, in = -1;

    public MatrixIterator(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return !(out + 1 == matrix.length && in + 1 == matrix[out].length);
    }

    @Override
    public Object next() {
        if (matrix.length == 0) {
            throw new NoSuchElementException("Пустой массив");
        }
        if (in + 1 >= matrix[out].length) {
            if (out + 1 >= matrix.length) {
                throw new NoSuchElementException("Больше нет элементов");
            }
            in = 0;
            out++;
        } else {
            in++;
        }
        return matrix[out][in];
    }
}
