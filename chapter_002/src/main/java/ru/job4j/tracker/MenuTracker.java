package ru.job4j.tracker;

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
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println(String.format("%s. %s", item.getId(), item.getName()));
        }
    }
}

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];
    private int[] ranges;

    public  MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem(0, "add item");
        this.actions[1] = new MenuTracker.ShowItems(1, "show items");
        this.actions[2] = new EditItem(2, "edit item");
        this.actions[3] = this.new DeleteItem(3, "delete item");
        this.actions[4] = new FindById(4, "find item by id");
        this.actions[5] = new FindByName(5, "find item by name");

        int actionsQuantity = 0;
        for (UserAction action : this.actions) {
            actionsQuantity = action != null ? ++actionsQuantity : actionsQuantity;
        }
        this.ranges = new int[actionsQuantity];
        for (int i = 0; i < actionsQuantity; i++) {
            this.ranges[i] = this.actions[i].key();
        }
    }

    public int[] getRanges() {
        return this.ranges;
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public  void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
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
