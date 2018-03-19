package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LinkedContainerTest {
    private LinkedContainer<Integer> container = new LinkedContainer<>();

    @Before
    public void addElement() {
        container.add(1);
        container.add(2);
    }

    @Test
    public void addTest() {
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
    }

    @Test
    public void get() {
        container.add(3);
        container.add(4);
        assertThat(container.get(1), is(2));
        assertThat(container.get(1), is(2));
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
        container.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void modificationExceptionTest() {
        assertThat(container.iterator().next(), is(1));
        container.add(3);
        container.iterator().next();
    }

    @Test
    public void deleteLastTest() {
        container.add(3);
        container.delete(2);
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(2));
        assertThat(container.iterator().hasNext(), is(false));
    }

    @Test
    public void deleteMiddleTest() {
        container.add(3);
        container.delete(1);
        assertThat(container.iterator().next(), is(1));
        assertThat(container.iterator().next(), is(3));
        assertThat(container.iterator().hasNext(), is(false));
    }
}