package ru.job4j.tree;

import java.util.*;

public class BST<E extends Comparable<E>> implements Iterable<E> {
    Nod<E> root;
    Queue<Nod<E>> dataIterator = new LinkedList<>();

    public BST(E value) {
        this.root = new Nod<>(value);
        dataIterator.offer(this.root);
    }

    boolean add(E value) {
        leftOrRight(value,root);
        return true;
    }

    void leftOrRight(E value, Nod<E> parent) {
        if (value.compareTo(parent.getValue()) > 0) {
            if (parent.getRight() != null) {
                leftOrRight(value, parent.getRight());
            } else {
                parent.addRight(new Nod<>(value));
            }
        } else {
            if (parent.getLeft() != null) {
                leftOrRight(value, parent.getLeft());
            } else {
                parent.addLeft(new Nod<>(value));
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Optional<Nod<E>> rsl = Optional.empty();

            @Override
            public boolean hasNext() {
                return !dataIterator.isEmpty();
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                Nod<E> el = dataIterator.poll();
                rsl = Optional.of(el);
                if (rsl.get().getLeft() != null) {
                    dataIterator.offer(rsl.get().getLeft());
                }
                if (rsl.get().getRight() != null) {
                    dataIterator.offer(rsl.get().getRight());
                }
                return rsl.get().getValue();
            }
        };
    }
}

class Nod<E extends Comparable<E>> {
    private Nod<E> left, right;
    private final E value;

    public Nod(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public Nod<E> getLeft() {
        return left;
    }

    public Nod<E> getRight() {
        return right;
    }

    public void addLeft(Nod<E> child) {
        this.left = child;
    }

    public void addRight(Nod<E> child) {
        this.right = child;
    }
}