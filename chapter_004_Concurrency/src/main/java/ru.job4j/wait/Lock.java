package ru.job4j.wait;

public class Lock {
    private boolean locked = false;
    private int threadCode = -1;

    synchronized void lock() throws InterruptedException {
        while (locked) {
            this.wait();
        }
        threadCode = Math.abs(Thread.currentThread().hashCode());
        this.locked = true;
    }

    synchronized void unlock() {
        if (threadCode == Math.abs(Thread.currentThread().hashCode())) {
            threadCode = -1;
            this.locked = false;
        }
        this.notifyAll();
    }
}
