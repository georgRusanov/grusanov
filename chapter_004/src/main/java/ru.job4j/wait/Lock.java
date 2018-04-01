package ru.job4j.wait;

public class Lock {
    private boolean locked = true;

    synchronized void lock() throws InterruptedException {
        while (locked) {
            this.wait();
        }
        this.locked = true;
    }

    synchronized void unlock() {
        this.locked = false;
        this.notify();
    }
}
