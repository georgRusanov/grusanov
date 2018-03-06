package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */

class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id старой заявки :");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        Item item = new Item(name, desc);
        tracker.replace(id, item);
    }
}

class FindById extends BaseAction {

    public FindById(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id заявки :");
        Item item = tracker.findById(id);
        System.out.println(
                String.format("%s. %s", item.getId(), item.getName()));
    }
}

class FindByName extends BaseAction {

    public FindByName(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки :");
        List<Item> items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println(String.format("%s. %s", item.getId(), item.getName()));
        }
    }
}

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private List<Integer> ranges = new ArrayList<>();

    public  MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions.add(this.new AddItem(0, "add item"));
        this.actions.add(new MenuTracker.ShowItems(1, "show items"));
        this.actions.add(new EditItem(2, "edit item"));
        this.actions.add(this.new DeleteItem(3, "delete item"));
        this.actions.add(new FindById(4, "find item by id"));
        this.actions.add(new FindByName(5, "find item by name"));

        for (UserAction action : actions) {
            this.ranges.add(action.key());
        }
    }

    public List<Integer> getRanges() {
        return this.ranges;
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
                System.out.println(action.info());
        }
    }

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            tracker.add(new Item(name, desc));
        }
    }

    private static class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки :");
            tracker.delete(id);
        }
    }
}
