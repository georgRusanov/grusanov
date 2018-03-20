package ru.job4j.set;

import ru.job4j.list.ArrayContainer;

public class SimpleSet<T> extends ArrayContainer<T>{

    @Override
    public void add(T value) {
        boolean repeat = false;
        for (int i = 0; i < index; i++) {
            if (container[i] != null) {
                repeat = container[i].equals(value);
            }
        }
        if (!repeat) {
            super.add(value);
        }
    }
}
