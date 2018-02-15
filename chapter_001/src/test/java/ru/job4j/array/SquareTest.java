package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    @Test
    public void whenFirstLessSecond() {
        Square array = new Square();
        int[] result = array.calculate(2);
        int[] expected = {1, 4};
        assertThat(result, is(expected));
    }
}
