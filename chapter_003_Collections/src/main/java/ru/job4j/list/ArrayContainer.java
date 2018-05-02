package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class ArrayContainer<T> implements Iterable<T> {
    @GuardedBy("this")
    protected Object[] container;
    protected int index = 0, position = 0, modCount = 0, expectedModCount = 0;

    public ArrayContainer() {
        this.container = new Object[10];
    }

    public ArrayContainer(int size) {
        this.container = new Object[size];
    }

    public synchronized void add(T model) {
        if (index == container.length) {
            Arrays.copyOf(container, container.length * 3 / 2 + 1);
        }
        this.container[this.index++] = model;
        modCount++;
    }

    public synchronized T get(int index) {
        return (T) this.container[index];
    }

    public synchronized boolean contains(T value) {
        for (int i = 0; i < index; i++) {
            if (container[i] != null && container[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {


            @Override
            public boolean hasNext() {
                checkModification();
                return position < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                return (T) container[position++];
            }

            public void checkModification() {
                if (expectedModCount == 0) {
                    expectedModCount = modCount;
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Данные изменены");
                }
            }
        };
    }
}
