package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CheckTest {
    @Test
    public void whenWordContainsThenTrue() {
        Check check = new Check();
        boolean result = check.contains("Привет", "иве");
        assertThat(result, is(true));
    }

    @Test
    public void whenWordNotContainsThenFalse() {
        Check check = new Check();
        boolean result = check.contains("Привет", "иде");
        assertThat(result, is(false));
    }
}