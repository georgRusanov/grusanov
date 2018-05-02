package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    private SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Before
    public void pushElements() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void queueTest() {
        assertThat(queue.pop(), is(1));
        assertThat(queue.pop(), is(2));
        assertThat(queue.pop(), is(3));
    }

    @Test
    public void queueTestWithAddElementAfterPop() {
        assertThat(queue.pop(), is(1));
        assertThat(queue.pop(), is(2));
        queue.push(3);
        assertThat(queue.pop(), is(3));
        assertThat(queue.pop(), is(3));
    }
}