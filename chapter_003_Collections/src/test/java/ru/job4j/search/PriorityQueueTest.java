package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPrioritySecondTest() {
        PriorityQueue queue = new PriorityQueue();
        Task t1 = new Task("1", 1);
        Task t2 = new Task("2", 2);
        Task t3 = new Task("3", 3);
        queue.put(t2);
        queue.put(t1);
        queue.put(t3);
        Task result = queue.take();
        assertThat(result.getDesc(), is("1"));
    }
}