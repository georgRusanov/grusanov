package ru.job4j.tree;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BSTTest {
    @Test(expected = NoSuchElementException.class)
    public void addAndIteratorTest() {
        BST<Integer> bst = new BST<>(50);
        bst.add(2);
        bst.add(3);
        bst.add(60);
        bst.add(4);
        bst.add(70);
        assertThat(bst.iterator().next(), is(50));
        assertThat(bst.iterator().next(), is(2));
        assertThat(bst.iterator().next(), is(60));
        assertThat(bst.iterator().next(), is(3));
        assertThat(bst.iterator().next(), is(70));
        assertThat(bst.iterator().next(), is(4));
        bst.iterator().next();
    }
}