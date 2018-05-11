package ru.job4j.cafe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    CoffeeMachine cm = new CoffeeMachine();

    @Test
    public void test() {
        int[] result = cm.changes(50, 35);
        int[] answer = {10, 5};
        assertThat(result, is(answer));
    }

    @Test
    public void test1() {
        int[] result = cm.changes(53, 35);
        int[] answer = {10, 5, 2, 1};
        assertThat(result, is(answer));
    }

}