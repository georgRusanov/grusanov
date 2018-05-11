package ru.job4j.stock;

import java.util.Comparator;

class RequestIdComparator implements Comparator<Stock.Request> {

    /**
     * Сравнение по id. Добавил чтобы трисет добавлял заявки с одинаковой ценой
     * @param first заявка
     * @param second заявка
     * @return
     */
    @Override
    public int compare(Stock.Request first, Stock.Request second) {
        return Integer.compare(first.getId(), second.getId());
    }
}
