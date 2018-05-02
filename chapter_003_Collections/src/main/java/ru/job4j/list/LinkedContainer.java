package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
@ThreadSafe
public class LinkedContainer<T> implements Iterable<T> {
    protected int size;
    @GuardedBy("this")
    protected Node first, last, iteratorNode = null;
    protected int modCount = 0, expectedModCount = 0;

    protected LinkedContainer() {
        this.size = 0;
    }

    public synchronized void add(T obj) {
        if (size == 0) {
            first = new Node(null, obj, null);
            last = first;
            iteratorNode = new Node(null, null, first);
        } else {
            last.next = new Node(this.last, obj, null);
            last = last.next;
        }
        size++;
        modCount++;
    }

    public synchronized T get(int index) {
        T answer = null;
        if (index < size) {
            Node temp = iteratorNode;
            iteratorNode = new Node(null, null, first);
            for (int i = 0; i <= index; i++) {
                answer = iterator().next();
            }
            iteratorNode = temp;
        }
        return answer;
    }

    public synchronized void delete(int index) {
        if (index < size) {
            if (size == 1) {
                last = null;
                first = last;
            } else {
                Node deleted = first;
                for (int i = 1; i <= index; i++) {
                    deleted = deleted.next;
                }
                if (index == 0) {
                    deleted.next.prev = null;
                    first = deleted.next;
                    deleted = null;
                } else if (index == size - 1) {
                    deleted.prev.next = null;
                    last = deleted.prev;
                    deleted = null;
                } else {
                    deleted.prev.next = deleted.next;
                    deleted.next.prev = deleted.prev;
                    deleted = null;
                }
            }
            size--;
            modCount++;
        }
    }

    public synchronized boolean contains(T value) {
        Node temp = iteratorNode;
        iteratorNode = new Node(null, null, first);
        for (int i = 0; i < size; i++) {
            if (iterator().next().equals(value)) {
                return true;
            }
        }
        iteratorNode = temp;
        return false;
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

    protected class Node<T> {
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

