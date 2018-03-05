package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenHigherPrioritySecondTest() {
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("ivan", 45),
                new User("ifan", 35),
                new User("ijan", 55),
                new User("ioan", 15)
        ));
        Set<User> set = new SortUser().sort(list);
        User[] names = set.toArray(new User[set.size()]);
        assertThat(names[0].getName(), is("ioan"));
    }

    @Test
    public void whenShotNamesFirst() {
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Ива", 25)
        ));
        List<User> sortedList = new SortUser().sortNameLength(list);
        String[] result = new String[list.size()];
        int i = 0;
        for (User user : sortedList) {
            result[i++] = user.toString();
        }
        String[] expected = {"Ива", "Иван", "Сергей", "Сергей"};
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortByNamesAndAge() {
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Иван", 25)
        ));
        List<User> sortedList = new SortUser().sortByAllFields(list);
        int[] result = new int[list.size()];
        int i = 0;
        for (User user : sortedList) {
            result[i++] = user.getAge();
        }
        int[] expected = {25, 30, 20, 25};
        assertThat(result, is(expected));
    }
}