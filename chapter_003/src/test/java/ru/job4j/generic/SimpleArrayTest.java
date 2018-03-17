package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<Integer> array = new SimpleArray<Integer>();

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
    public void setTest() {
        array.set(1, 3);
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().next(), is(3));
    }

    @Test
    public void deleteTest() {
        array.delete(0);
        assertThat(array.iterator().next(), is(2));
    }

    @Test
    public void get() {
        assertThat(array.get(1), is(2));
    }
}