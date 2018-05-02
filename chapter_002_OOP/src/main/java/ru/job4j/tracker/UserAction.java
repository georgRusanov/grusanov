package ru.job4j.tracker;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
