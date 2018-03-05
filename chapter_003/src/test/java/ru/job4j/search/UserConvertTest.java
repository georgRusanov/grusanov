package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenListOfArrayToList() {
        List<User> list = new ArrayList<>();
        User user1 = new User(15, "asd", "qwe");
        User user2 = new User(12, "adsfd", "qsdfswe");
        list.add(user1);
        list.add(user2);
        HashMap<Integer, User> result = new UserConvert().process(list);
        HashMap<Integer, User>  expected = new HashMap<>();
        expected.put(user1.getId(), user1);
        expected.put(user2.getId(), user2);
        assertThat(result, is(expected));
    }
}