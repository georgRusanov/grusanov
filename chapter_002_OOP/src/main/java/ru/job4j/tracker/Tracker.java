package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author Georg Rusanov (rusanovgeorgy@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Необходимо для создания id
     */
    private static final Random RN = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Заменяет заявку на другую.
     * @param id старой заявки
     * @param item новая заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                items.set(index, item);
                item.setId(id);
                break;
            }
        }
    }

    /**
     * удаляет элемент.
     * @param id id удаляемого элемента.
     */
    public void delete(String id) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                break;
            }
        }
    }

    /**
     * Массив всех существующих объектов.
     * @return Массив всех существующих объектов.
     */
    public List<Item> getAll() {
        return items;
    }

    /**
     * Получение массива с заданным именем.
     * @param key имя.
     * @return массив объектов с заданным именем.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Поиск по id.
     * @param id id искомого итема.
     * @return искомый итем.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
