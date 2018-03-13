package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator{

    private int[] array;
    private int[] sieve;
    int index = 0;

    public PrimeIterator(int[] array) {
        this.array = array;
        this.sieve = new int[maxElement(array) + 1];
        fillSieve(this.sieve);
    }

    @Override
    public boolean hasNext() {
        boolean answer = false;
        for (int i = this.index; i < this.array.length; i++) {
            if (this.sieve[this.array[i]] == 1) {
                answer = true;
                break;
            }
        }
        return answer;
    }

    @Override
    public Object next() {
        if (hasNext()) {
            for (int i = this.index; i < this.array.length; i++) {
                if (this.sieve[this.array[i]] == 1) {
                    this.index = i;
                    break;
                }
            }
        } else {
            throw new NoSuchElementException("Больше нет элементов");
        }
        return array[index++];
    }

    public int maxElement(int[] array) {
        int max = 0;
        for(int i : array) {
            max = i > max ? i : max;
        }
        return max;
    }

    public void fillSieve(int[] array) {
        int size = array.length - 1;
        array[2] = 1;
        for (int i = 3; i <= size; i +=2) {
            array[i] = 1;
        }
        for (int i = 3; i*i <= size; i +=2) {
            if (array[i] == 1) {
                for (int j = i * i; j <= size; j += i) {
                    array[j] = 0;
                }
            }
        }
    }
}
