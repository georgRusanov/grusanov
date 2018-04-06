package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

public class SimpleSetLinked<T> extends LinkedContainer<T> {

    @Override
    public void add(T value) {
        if (!contains(value)) {
            super.add(value);
            modCount--;
        }
    }
}
