package ru.job4j.tracker;

/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */

class EditItem implements UserAction {

    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id старой заявки :");
        String name = input.ask("Введите имя новой заявки :");
        String desc = input.ask("Введите описание новой заявки :");
        Item item = new Item(name, desc);
        tracker.replace(id, item);
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "edit item");
    }
}

class FindById implements UserAction {

    public int key() {
        return 4;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id заявки :");
        Item item = tracker.findById(id);
        System.out.println(
                String.format("%s. %s", item.getId(), item.getName()));
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "find by id item");
    }
}

class FindByName implements UserAction {

    public int key() {
        return 5;
    }

    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя заявки :");
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println(String.format("%s. %s", item.getId(), item.getName()));
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "find by name item");
    }
}

public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public  MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = new FindById();
        this.actions[5] = new FindByName();
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

    private class AddItem implements UserAction {

        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с Id : " + item.getId() + "-----------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "add the new item");
        }
    }

    private static class ShowItems implements UserAction {

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.getAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    private class DeleteItem implements UserAction {

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id заявки :");
            tracker.delete(id);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "delete item");
        }
    }
}
