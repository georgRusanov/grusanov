package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArrayContainerTest {
    private ArrayContainer<Integer> array = new ArrayContainer<>();

    @Before
    public void addElement() {
        array.add(1);
        array.add(2);
    }

    @Test
    public void addTest() {
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().next(), is(2));
    }

    @Test
    public void get() {
        assertThat(array.get(1), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().next(), is(2));
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void modificationExceptionTest() {
        assertThat(array.iterator().next(), is(1));
        array.add(3);
        array.iterator().next();
    }

}