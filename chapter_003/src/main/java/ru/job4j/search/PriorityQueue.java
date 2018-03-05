package ru.job4j.search;


import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.size() == 0) {
            this.tasks.add(0, task);
        } else if (task.getPriority() == 1) {
            this.tasks.add(0, task);
        } else {
            int index;
            for (index = 0; index < tasks.size(); index++) {
                if (this.tasks.get(index).getPriority() > task.getPriority()) {
                    this.tasks.add(index, task);
                    break;
                }
            }
            if (index == tasks.size() - 1) {
                this.tasks.addLast(task);
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
