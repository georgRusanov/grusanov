package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class ThreadsPool {
    @GuardedBy("queue")
    private final Queue<Work> queue = new LinkedList<>();
    private boolean condition = true;

    public void start() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(new WorkThread()).start();
        }
    }

    void add(Work work) {
        synchronized (queue) {
            queue.add(work);
            queue.notifyAll();
        }
    }

    void finish() {
        synchronized (queue) {
            condition = false;
            queue.notifyAll();
        }
    }

    private final class WorkThread implements Runnable {

        @Override
        public void run() {
            synchronized (queue) {
                while (condition) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                }
            }
        }
    }

    class Work {

    }
}

