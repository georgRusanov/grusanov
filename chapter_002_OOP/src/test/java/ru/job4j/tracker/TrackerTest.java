package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {
    /**
     * add test
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll().contains(item), is(true));
    }

    /**
     * replace test
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * delete test
     */
    @Test
    public void whenDeleteSecondThanThirdOnThatPlace() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        Item third = new Item("test2", "testDescription2", 1234L);
        tracker.add(third);
        tracker.delete(second.getId());
        assertThat(tracker.getAll().get(1), is(third));
    }

    /**
     * getAll test
     */
    @Test
    public void whenGetAllThanReceiveArrayOfItems() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        List<Item> result = tracker.getAll();
        List<Item> expected = Arrays.asList(first, second);
        assertThat(result, is(expected));
    }
    /**
     * findByName test
     */
    @Test
    public void receiveFirstAndThird() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        Item third = new Item("test1", "testDescription2", 1234L);
        tracker.add(third);
        List<Item> result = tracker.findByName("test1");
        List<Item> expected = Arrays.asList(first, third);
        assertThat(result, is(expected));
    }

    /**
     * findById test
     */
    @Test
    public void findSecond() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 123L);
        tracker.add(first);
        Item second = new Item("test2", "testDescription2", 1234L);
        tracker.add(second);
        Item result = tracker.findById(second.getId());
        assertThat(result, is(second));
    }
}
