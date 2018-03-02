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

}