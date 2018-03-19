package ru.job4j.list;

public class SimpleStack<T> {

    LinkedContainer<T> container = new LinkedContainer<>();

    public T pop() {
        T result = container.get(container.size - 1);
        container.delete(container.size - 1);
        container.modCount--;
        return result;
    }

    public void push(T value) {
        container.add(value);
        container.modCount--;
    }
}
