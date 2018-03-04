
package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Georg  Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MatrixTest {

    @Test
    public void whenFourAndFourThanSixteen() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(5);
        int expected = 16;
        assertThat(result[3][3], is(expected));
    }
    
    @Test
    public void whenEightAndNinetThanSeventyTwo() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(9);
        int expected = 72;
        assertThat(result[8][7], is(expected));
    }
}
