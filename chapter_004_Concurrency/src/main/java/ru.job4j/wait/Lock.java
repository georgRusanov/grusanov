package ru.job4j.wait;

public class Lock {
    private boolean locked = true;
    private int threadCode = -1;

    synchronized void lock() throws InterruptedException {
        while (locked) {
            this.wait();
        }
        if (threadCode == -1) {
            threadCode = Math.abs(Thread.currentThread().hashCode());
            this.locked = true;
        }
    }

    synchronized void unlock() {
        if (threadCode == Math.abs(Thread.currentThread().hashCode())) {
            threadCode = -1;
            this.locked = false;
        }
        this.notifyAll();
    }
}
