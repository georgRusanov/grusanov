package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {
    private SimpleSet<Integer> set = new SimpleSet<>();

    @Before
    public void addElement() {
        set.add(1);
        set.add(2);
        set.add(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void addTest() {
        assertThat(set.iterator().next(), is(1));
        assertThat(set.iterator().next(), is(2));
        set.iterator().next();
    }
}