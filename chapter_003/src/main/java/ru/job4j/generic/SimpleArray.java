package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    Object[] objects;
    int index = 0, position = 0;

    public SimpleArray() {
        this.objects = new Object[10];
    }

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public  void add(T model) {
        if (index == objects.length) {
            System.arraycopy(objects, 0, objects, 0, objects.length * 3 / 2 + 1);
        }
        this.objects[this.index++] = model;
    }

    public void set(int index, T model) {
        this.objects[index] = model;
    }

    public void delete(int index) {
        System.arraycopy(objects, index + 1, objects, index, objects.length - index - 1);
    }

    public T get(int index) {
        return (T) this.objects[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return position <= index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                return (T) objects[position++];
            }
        };
    }
}
