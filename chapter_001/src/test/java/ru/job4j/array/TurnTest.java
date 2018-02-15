package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Turn {

    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        int[] array = {4, 1, 6, 2};
        int result = Turn.back(array);
        int[] expected = {2, 6, 1, 4};
        assertThat(result, is(expected));
    }
    
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int result = Turn.back(array);
        int[] expected = {5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
