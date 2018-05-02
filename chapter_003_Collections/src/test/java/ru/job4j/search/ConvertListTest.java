package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void whenListToArray() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (Integer i = 1; i != 8; i++) {
            list.add(i);
        }
        int[][] result = convertList.toArray(list, 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(result, is(expected));
    }

    @Test
    public void whenListOfArrayToList() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(list);
        List<Integer> expected = new ArrayList<>();
        for (Integer i = 1; i != 7; i++) {
            expected.add(i);
        }
        assertThat(result, is(expected));
    }

    @Test
    public void listToTwoDimensionalArray() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2}, {3, 4}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4);
        int[][] result = convert.toArray(origin, 2);
        assertThat(result, is(expected));
    }
}