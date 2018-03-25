package ru.job4j.tree;

import java.util.*;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    Node<E> root;
    Queue<Node<E>> dataIterator = new LinkedList<>();

    public Tree(E value) {
        this.root = new Node<>(value);
        dataIterator.offer(this.root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        if (findBy(parent).isPresent()) {
            Node<E> parentNode = findBy(parent).get();
            for (Node<E> element : parentNode.leaves()) {
                if (element.eqValue(child)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                parentNode.add(new Node<>(child));
            }
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean answer = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        int num = 0;
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            num = 0;
            for (Node<E> child : el.leaves()) {
                data.offer(child);
                num++;
                if (num > 2) {
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Optional<Node<E>> rsl = Optional.empty();

            @Override
            public boolean hasNext() {
                return !dataIterator.isEmpty();
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                Node<E> el = dataIterator.poll();
                rsl = Optional.of(el);
                for (Node<E> child : el.leaves()) {
                    dataIterator.offer(child);
                }
                return rsl.get().getValue();
            }
        };
    }
}
