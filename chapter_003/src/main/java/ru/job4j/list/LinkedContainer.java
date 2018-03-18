package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainer<T> implements Iterable<T> {
    protected int size;
    protected Node first, last, iteratorNode = null;
    protected int modCount = 0, expectedModCount = 0;

    protected LinkedContainer() {
        this.size = 0;
    }

    public void add(T obj) {
        if (size == 0) {
            first = last = new Node(null, obj, null);
            iteratorNode = new Node(null, null, first);
        } else {
            last = last.next = new Node(this.last, obj, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        T answer = null;
        if (index < size) {
            for (int i = 0; i <=index; i++) {
                answer = iterator().next();
            }
        }
        return answer;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {


            @Override
            public boolean hasNext() {
                checkModification();
                if (iteratorNode == null) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                return iteratorNode.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                iteratorNode = iteratorNode.next;
                return (T) iteratorNode.item;
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

    class Node<T> {
        T item;
        Node next;
        Node prev;

        public Node(Node prev, T item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}

