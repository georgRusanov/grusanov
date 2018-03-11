package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator {

    private int[] array;
    int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean answer = false;
        for (int i = index; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                answer = true;
            }
        }
        return answer;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            for (int i = index; i < array.length; i++) {
                if (array[i] % 2 == 0) {
                    this.index = i;
                    break;
                }
            }
        } else {
            throw new NoSuchElementException("Больше нет элементов");
        }
        return array[index++];
    }
}
