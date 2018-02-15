package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {

    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        int[] array = {5, 1, 2, 7, 3};
        BubbleSort bubbleSort = new BubbleSort();
        int[] result = bubbleSort.sort(array);
        int[] expected = {1, 2, 3, 5, 7};
        assertThat(result, is(expected));
    }
}
