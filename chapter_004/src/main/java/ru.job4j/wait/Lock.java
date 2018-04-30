package ru.job4j.wait;

public class Lock {
    private boolean locked = true;
    private String threadName = null;

    synchronized void lock() throws InterruptedException {
        if (threadName.equals(null)) {
            Thread.currentThread().getName();
            this.locked = true;
        }
        while (locked) {
            this.wait();
        }
    }

    synchronized void unlock() {
        if (threadName.equals(Thread.currentThread().getName())) {
            threadName = null;
            this.locked = false;
        }
        this.notifyAll();
    }
}
