package ru.job4j.tracker;

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
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

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
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId(){
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Заменяет заявку на другую.
     * @param id старой заявки
     * @param item новая заявка
     */
    public void replace(String id, Item item){
        int index = 0;
        for (int i = 0; i != position; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        items[index] = item;
    }

    /**
     * удаляет элемент.
     * @param id id удаляемого элемента.
     */
    public void delete(String id){
        int index = 0;
        for (int i = 0; i != position; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        System.arraycopy(items, index + 1, items, index, position - index - 1);
        items[position-1] = null;
        position--;
    }

    /**
     * Массив всех существующих объектов.
     * @return Массив всех существующих объектов.
     */
    public Item[] getAll(){
        Item[] result = new Item[position];
        for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Получение массива с заданным именем.
     * @param key имя.
     * @return массив объектов с заданным именем.
     */
    public Item[] findByName(String key){
        int index = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                index++;
            }
        }
        Item[] result = new Item[index];
        index = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                result[index] = item;
                index++;
            }
        }
        return result;
    }

    /**
     * Поиск по id.
     * @param id id искомого итема.
     * @return искомый итем.
     */
    public Item findById(String id){
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
