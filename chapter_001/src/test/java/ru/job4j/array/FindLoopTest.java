package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

    @Test
    public void indexIsThree() {
        int[] array = {1, 2, 5, 6, 7};
        FindLoop findLoop = new FindLoop();
        int result = findLoop.indexOf(array, 6);
        assertThat(result, is(3));
    }

    @Test
    public void indexIsMinusOne() {
        int[] array = {1, 2, 5, 6, 7};
        FindLoop findLoop = new FindLoop();
        int result = findLoop.indexOf(array, 12);
        assertThat(result, is(-1));
    }
}
