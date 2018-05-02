package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {
    UserStore userStore = new UserStore();

    @Before
    public void before() {
        userStore.add(new User("first"));
        userStore.add(new User("second"));
    }

    @Test
    public void addTest() {
        assertThat(userStore.array.iterator().next().getId(), is("first"));
        assertThat(userStore.array.iterator().next().getId(), is("second"));
    }

    @Test
    public void replaceTest() {
        userStore.replace("second", new User("third"));
        assertThat(userStore.array.iterator().next().getId(), is("first"));
        assertThat(userStore.array.iterator().next().getId(), is("third"));
    }

    @Test
    public void deleteTest() {
        userStore.delete("first");
        assertThat(userStore.array.iterator().next().getId(), is("second"));
    }
    @Test
    public void findByIdTest() {
        assertThat(userStore.findById("first"), is(userStore.array.iterator().next()));
    }
}