package ru.job4j.list;

public class SimpleQueue<T> {

    LinkedContainer<T> container = new LinkedContainer<>();

    public T pop() {
        T result = container.get(0);
        container.delete(0);
        container.modCount--;
        return result;
    }

    public void push(T value) {
        container.add(value);
        container.modCount--;
    }
}
