package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {
    private SimpleStack<Integer> stack = new SimpleStack<>();

    @Before
    public void pushElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void stackTest() {
        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void stackTestWithAddElementAfterPop() {
        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(2));
        stack.push(3);
        assertThat(stack.pop(), is(3));
        assertThat(stack.pop(), is(1));
    }
}