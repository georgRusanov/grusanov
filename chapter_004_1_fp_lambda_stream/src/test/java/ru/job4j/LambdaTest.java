package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Lambda function = new Lambda();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLinearFunctionThenSquareResults() {
        Lambda function = new Lambda();
        List<Double> result = function.diapason(1, 3, x -> x * x + 1);
        List<Double> expected = Arrays.asList(2D, 5D);
        assertThat(result, is(expected));
    }
}