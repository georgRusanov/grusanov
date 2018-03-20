package ru.job4j.set;

import ru.job4j.list.LinkedContainer;

public class SimpleSetLinked<T> extends LinkedContainer<T> {

    @Override
    public void add(T value) {
        boolean repeat = false;
        iteratorNode = new Node(null, null, first);
        for (int i = 0; i < size; i++) {
            repeat = iterator().next() == value;
        }
        if (!repeat) {
            super.add(value);
            modCount--;
        }
    }
}
