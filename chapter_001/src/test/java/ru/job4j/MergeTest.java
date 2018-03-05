package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeTest {
    @Test
    public void mergeTwoArrays() {
        int[] A = {4, 12, 13, 20};
        int[] B = {-10, 5, 6, 7, 8, 21, 22};
        int[] result = Merge.mergeTwoArrays(A, B);
        int[] expected = {-10, 4, 5, 6, 7, 8, 12, 13, 20, 21, 22};
        assertThat(result, is(expected));
    }
}