package ru.job4j.list;

public class Cycle {

    boolean hasCycle(Node first) {
        boolean answer = false;
        Node turtle = first;
        Node rabbit = first.next;
        while (rabbit != null) {
            if (turtle == rabbit) {
                answer = true;
                break;
            }
            turtle = turtle.next;
            rabbit = rabbit.next.next;
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