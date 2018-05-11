package ru.job4j.stock;

import java.util.Comparator;

class RequestVolumeComparator implements Comparator<Stock.Request> {

    /**
     * Сравнение по цене
     * @param first заявка
     * @param second заявка
     * @return
     */
    @Override
    public int compare(Stock.Request first, Stock.Request second) {
        return Integer.compare(second.getPrice(), first.getPrice());
    }
}
