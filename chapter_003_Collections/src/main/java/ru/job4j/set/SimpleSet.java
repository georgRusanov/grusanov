package ru.job4j.set;

import ru.job4j.list.ArrayContainer;

public class SimpleSet<T> extends ArrayContainer<T> {

    @Override
    public void add(T value) {
        if (!contains(value)) {
            super.add(value);
        }
    }
}
