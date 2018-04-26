package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class ThreadsPool {
    @GuardedBy("this")
    private Queue<Work> queue = new LinkedList<>();
    private boolean condition = true;

    public ThreadsPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new Thread(new WorkThread()).start();
        }
    }

    void add(Work work) {
        queue.add(work);
        synchronized (queue) {
            queue.notify();
        }
    }

    private final class WorkThread implements Runnable {

        @Override
        public void run() {
            while (condition) {
                synchronized (queue) {
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

