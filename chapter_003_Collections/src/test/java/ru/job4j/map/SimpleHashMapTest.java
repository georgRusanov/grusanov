package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleHashMapTest {
    private SimpleHashMap<String, Integer> mapHash = new SimpleHashMap<>();

    @Before
    public void addElement() {
        mapHash.insert("a", 1);
        mapHash.insert("b", 2);
    }

    @Test
    public void getTest() {
        assertThat(mapHash.get("a"), is(1));
        assertThat(mapHash.get("b"), is(2));
        assertThat(mapHash.get("c"), is((Integer) null));
    }

    @Test
    public void deleteElement() {
        mapHash.delete("a");
        assertThat(mapHash.get("a"), is((Integer) null));
    }

    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        assertThat(mapHash.iterator().next(), is(1));
        assertThat(mapHash.iterator().next(), is(2));
        mapHash.iterator().next();
    }
}