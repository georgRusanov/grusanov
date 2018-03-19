package ru.job4j.list;

public class Cycle {

    boolean hasCycle(Node first) {
        boolean answer = false;
        Node temp = first;
        while (first.next != null) {
            if (first.next == temp) {
                answer = true;
                break;
            }
            first = first.next;
        }
        return answer;
    }
}
class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}