package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public Queue<T> getQueue() {
        return queue;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > 9) {
                    queue.wait();
            }
            queue.add(value);
            queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == 0) {
                queue.wait();
            }
            queue.notify();
            return queue.poll();
        }
    }
}

