package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SetHashTest {
    private SetHash<Integer> setHash = new SetHash<>();

    @Before
    public void addElement() {
        setHash.add(1);
    }

    @Test
    public void addTest() {
        assertThat(setHash.add(2), is(true));
        assertThat(setHash.contains(1), is(true));
        assertThat(setHash.contains(2), is(true));
        assertThat(setHash.contains(3), is(false));
    }

    @Test
    public void falseWhenAddExistElement() {
        assertThat(setHash.add(1), is(false));
    }

    @Test
    public void removeElement() {
        assertThat(setHash.remove(1), is(true));
        assertThat(setHash.contains(1), is(false));
    }
}