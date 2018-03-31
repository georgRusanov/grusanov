package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();

    @Test
    public void test() throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    simpleBlockingQueue.offer(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    simpleBlockingQueue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread2.start();
        thread1.start();
        thread2.join();
        thread1.join();
        assertThat(simpleBlockingQueue.getQueue().size(), is(0));
    }

}